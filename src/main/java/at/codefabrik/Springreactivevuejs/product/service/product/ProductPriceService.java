package at.codefabrik.Springreactivevuejs.product.service.product;

import at.codefabrik.Springreactivevuejs.product.entity.MoneySymbol;
import at.codefabrik.Springreactivevuejs.product.entity.Product;
import at.codefabrik.Springreactivevuejs.product.model.product.PriceUnit;
import at.codefabrik.Springreactivevuejs.product.model.product.ProductBookingRequest;
import at.codefabrik.Springreactivevuejs.product.model.product.ProductPricingResponse;
import at.codefabrik.Springreactivevuejs.product.repo.mongo.ProductBookingRepository;
import at.codefabrik.Springreactivevuejs.product.repo.mongo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class ProductPriceService {
    private final ProductRepository productRepository;
    private final ProductBookingRepository productBookingRepository;

    public BigDecimal getByMoneyType(String id, MoneySymbol moneySymbol) {
        return BigDecimal.TEN;
    }

    public Mono<ProductPricingResponse> calculatePrice(String productId, ProductBookingRequest request){
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")))
                .map(product -> mapToDto(product, request));
    }

    public ProductPricingResponse mapToDto(Product product, ProductBookingRequest request) {
            // 1️⃣ Dauer berechnen
            long hours = Duration.between(request.getPickupDate(), request.getReturnDate()).toHours();

            if(hours <= 0){
                throw new RuntimeException("Hours must be greater than zero. invalid Duration");
            }

            // 2️⃣ Preis-Einheit bestimmen
            PriceUnit priceUnit;
            long duration;

            if(hours < 24){
                priceUnit = PriceUnit.HOUR;
                duration = hours;
            } else if (hours < 24 * 7) {
                priceUnit = PriceUnit.DAY;
                duration = hours / 24;
            }else {
                priceUnit = PriceUnit.WEEK;
                duration = hours  / (24 * 7);
            }

            // 3️⃣ Basispreis holen
            BigDecimal basePrice= product.getPriceMap().get(request.getMoneySymbol());
            // 4️⃣ Gesamtpreis berechnen
            BigDecimal total = basePrice.multiply(BigDecimal.valueOf(duration));

            return ProductPricingResponse.builder()
                    .productId(product.getId())
                    .totalPrice(total)
                    .moneySymbol(request.getMoneySymbol())
                    .duration(duration)
                    .priceUnit(priceUnit)
                    .build();
    }
}
