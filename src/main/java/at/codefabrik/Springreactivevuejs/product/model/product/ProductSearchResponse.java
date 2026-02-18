package at.codefabrik.Springreactivevuejs.product.model.product;

import at.codefabrik.Springreactivevuejs.product.entity.MoneySymbol;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ProductSearchResponse {
    private String productId;
    private String name;
    private List<String> images;
    private BigDecimal estimatedPrice; //geschätzter preis für die angefragte Zeitspanne, basierend auf Suchzeitraum
    private MoneySymbol moneySymbol;
    private double distanceKm;
}
