package at.codefabrik.Springreactivevuejs.product.service.product;

import at.codefabrik.Springreactivevuejs.product.entity.MoneySymbol;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductFreeDeliveryService {

    public String getDeliveryInfo(String productId) {
        // TODO implement logic for delivery info based on productId
        return "überemorgen";
    }

    public boolean getAvailable(String productId, BigDecimal price, MoneySymbol moneySymbol) {
        // TODO implement logic for free delivery based on productId and price
        // Null-safe: if price missing, assume not eligible for free delivery
        if (price == null) return false;
        return price.compareTo(BigDecimal.ZERO) >= 0;
    }
}
