package at.codefabrik.Springreactivevuejs.product.service.product;

import at.codefabrik.Springreactivevuejs.product.entity.Product;
import at.codefabrik.Springreactivevuejs.product.entity.ProductImage;
import at.codefabrik.Springreactivevuejs.product.repo.mongo.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductImageService {
    private final ProductRepository productRepository;

    public String getProductMainImage(String productId) {
        if(productId == null || productId.trim().isEmpty()) {
            log.debug("ProductId war null/empty");
            return getPlaceholder();
        }
        try{
            Product product = productRepository.findById(productId).block(Duration.ofMillis(1200));
            if(product == null) {
                log.debug("Product not found {}", productId);
                return getPlaceholder();
            }

            List<ProductImage> images = product.getProductImage();
            if(images == null || images.isEmpty()) {
                log.debug("Images war empty {}", productId);
                return getPlaceholder();
            }

            String url = images.get(0).getUrl();
            if(url == null || url.trim().isEmpty()) {
                log.debug("Erstes Bild-URL ist leer bei Produkt {}", productId);
                return getPlaceholder();
            }
            return url;
        }catch (Exception e){
            log.warn("Fehler beim Holen des Hauptbilds für {} → {}", productId, e.getMessage());
            return getPlaceholder();
        }
    }

    private String getPlaceholder() {
        return "https://placehold.co/300x300";
    }
}
    /*
    public String getProductMainImage(String productId) { // Wir wählen deterministisch eines der vorhandenen Bilder car1..car5
        if (productId == null || productId.isBlank()) {
            System.out.println("/car/car1.jpg");
            return "/car/car1.jpg";
        }
        int bucket = Math.abs(productId.hashCode()) % 5 + 1; // 1..5
        System.out.println("/car/car" + bucket + ".jpg");
        return "/car/car" + bucket + ".jpg";
    }
     */

