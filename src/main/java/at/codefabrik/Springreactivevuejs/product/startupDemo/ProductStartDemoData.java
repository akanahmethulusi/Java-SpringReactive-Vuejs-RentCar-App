package at.codefabrik.Springreactivevuejs.product.startupDemo;

import at.codefabrik.Springreactivevuejs.filestore.service.FileStoreService;
import at.codefabrik.Springreactivevuejs.product.entity.MoneySymbol;
import at.codefabrik.Springreactivevuejs.product.model.category.CategoryResponse;
import at.codefabrik.Springreactivevuejs.product.model.category.CategorySaveRequest;
import at.codefabrik.Springreactivevuejs.product.model.product.ProductResponse;
import at.codefabrik.Springreactivevuejs.product.model.product.ProductSaveRequest;
import at.codefabrik.Springreactivevuejs.product.repo.elastic.ProductEsRepository;
import at.codefabrik.Springreactivevuejs.product.service.product.ProductService;
import at.codefabrik.Springreactivevuejs.product.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.IntStream;
import java.io.InputStream;

import static java.util.UUID.randomUUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductStartDemoData {
    private final ProductService productService;
    private final ProductEsRepository productEsRepository;
    private final CategoryService categoryService;
    private final FileStoreService fileStoreService;

    @EventListener(ApplicationReadyEvent.class)
    public void migrate() {
        Long countData = productService.count().block();
        assert  countData != null;
        if(countData.equals(0L)){ //Ensure ES cleared synchonously
            try {
                productEsRepository.deleteAll();
                //Wenn ich hier Daten anlege, dann muss ich die alle Daten löschen, falls ich die App neu starte.
            }catch (Exception e){
                log.warn("Could not clear Product ES data: {}", e.getMessage());
            }
            log.info("===>>> No Product Data found. Inserting Demo Data...");

            // 1. Datenstruktur für realistische Demo-Fahrzeuge
            record DemoCar(
                    String brand,
                    String model,
                    String name,
                    String description,
                    String imageFilename,
                    int basePriceChf
            ) {}

            List<DemoCar> demoCars = List.of(
                    new DemoCar("Honda", "CR-V", "Honda CR-V 2.0 Hybrid Executive",
                            "Sehr gepflegter Hybrid-SUV mit Allrad, erst 48.000 km, Panorama-Dach, Navi, Sitzheizung vorne & hinten, Rückfahrkamera, Apple CarPlay & Android Auto.",
                            "honda.jpg", 130),

                    new DemoCar("Volvo", "XC60", "Volvo XC60 B5 AWD Momentum",
                            "1. Hand, Sensus Navi, 360° Kamera, adaptiver Tempomat, LED-Scheinwerfer, Leder-Innenausstattung, Standheizung, sehr guter Zustand.",
                            "volvo.jpg", 120),

                    new DemoCar("Kia", "Ceed", "Kia Ceed 1.5 T-GDI GT-Line",
                            "Topausstattung: LED-Matrix-Scheinwerfer, Bose Soundsystem, Head-up Display, Navi, Sitzheizung + Lenkradheizung, 18″ Felgen, erst 18 Monate alt.",
                            "kia_ceed.jpg", 90),

                    new DemoCar("Kia", "EV6", "Kia EV6 GT-Line AWD 77,4 kWh",
                            "Elektro-SUV mit Allrad, extrem schnelles Laden (800V), 360° Kamera, Head-up Display, Relax-Sitze, Panorama-Dach, nur 22.000 km.",
                            "kia_ev4.jpg", 80),  // ← dein Dateiname ist kia_ev4.jpg

                    new DemoCar("Renault", "Clio", "Renault Clio 1.0 TCe 90 RS Line",
                            "Sehr sparsamer Benziner, Voll-LED, Rückfahrkamera, Klimaautomatik, Apple CarPlay/Android Auto, erst 9.800 km, wie neu.",
                            "clio.jpg", 70)
            );

            // Optional: falls du mehr als 5 willst → einfach wiederholen oder erweitern
            // IntStream.range(0, 10).forEach(i -> { DemoCar car = demoCars.get(i % demoCars.size()); ... }

            CategoryResponse pkw = categoryService.save(CategorySaveRequest.builder().name("pkw").build());
            CategoryResponse bus = categoryService.save(CategorySaveRequest.builder().name("bus").build());

            //IntStream.range(0,10).forEach(item-> {
            IntStream.range(0, demoCars.size()).forEach(idx -> {
                DemoCar car = demoCars.get(idx);

                // Preis-Map erstellen
                Map<MoneySymbol, BigDecimal> priceMap = new HashMap<>();
                priceMap.put(MoneySymbol.CHF, BigDecimal.valueOf(car.basePriceChf));
                priceMap.put(MoneySymbol.EUR,  // grobe Umrechnung – oder nimm aktuelle Kurse
                        BigDecimal.valueOf(car.basePriceChf * 1.04)); // Beispielkurs 1 CHF ≈ 1.04 EUR

                String imgUUID = randomUUID().toString();


                File file = null;
                try {
                    //file = ResourceUtils.getFile( "classpath:static/car/car"+ (item % 5 +1) +".jpg");
                    file = ResourceUtils.getFile( "classpath:static/car/car"+ (idx % 5 +1) +".jpg");
                } catch (FileNotFoundException e) {
                    log.warn("Could not find image file: {}", e.getMessage());
                }
                //File file = ResourceUtils.getFile( "classpath:static/car/car12.jpg");
                byte[] byte_file = null;
                try {
                    byte_file = Files.readAllBytes(file.toPath());
                } catch (IOException e) {
                    log.warn("Could not read image file: {}", e.getMessage());
                }

                //Hier könnte man eine 5 GB eine Datei geuplodet werden, dann hat alle Last auf RAM(byte[]) geladet. Das ist als Performance nicht optimal.
                // Optimal wäre, wenn man ohne RAM Last direkt den File Stream an S3 uploadet.
                fileStoreService.saveImage(imgUUID, new ByteArrayInputStream(byte_file));

                productService.save(
                        ProductSaveRequest.builder()
                                .id(UUID.randomUUID().toString())
                                .name(car.name)
                                .description(car.description)
                                .categoryId(pkw.getId())
                                .sellerId(UUID.randomUUID().toString()) // oder fester Demo-Verkäufer
                                .features("- " + car.brand + " Originalfelgen\n- Klimaautomatik\n- Multifunktionslenkrad\n- Bluetooth\n- LED-Scheinwerfer")
                                .priceMap((HashMap<MoneySymbol, BigDecimal>) priceMap)
                                .images(List.of(imgUUID))
                                .build());
            });
        }

    }
}
