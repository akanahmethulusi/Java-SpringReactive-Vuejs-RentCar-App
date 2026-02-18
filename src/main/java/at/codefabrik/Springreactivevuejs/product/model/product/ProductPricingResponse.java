/*
@k@n
Das ist das Ergebnis unsere Berechnung
Wird nicht gespeichert.
 */
package at.codefabrik.Springreactivevuejs.product.model.product;

import at.codefabrik.Springreactivevuejs.product.entity.MoneySymbol;
import at.codefabrik.Springreactivevuejs.product.model.product.PriceUnit;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductPricingResponse {
    private String productId;
    private BigDecimal totalPrice;
    private MoneySymbol moneySymbol;
    private long duration;
    private PriceUnit priceUnit;
}
