/*
@k@n
Das brauchen wir für frontend-ProductBooking
 */
package at.codefabrik.Springreactivevuejs.product.model.product;

import at.codefabrik.Springreactivevuejs.product.entity.MoneySymbol;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductBookingRequest {
    private String productId;
    private LocalDateTime pickupDate;
    private LocalDateTime returnDate;
    private MoneySymbol moneySymbol;
}
