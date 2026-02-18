package at.codefabrik.Springreactivevuejs.product.model.product;

import at.codefabrik.Springreactivevuejs.product.entity.MoneySymbol;
import at.codefabrik.Springreactivevuejs.product.model.ProductSellerResponse;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ProductDetailsResponse {
    private String id;
    private String name;
    private String description;
    private ProductSellerResponse seller;
    private String categoryId; // hier muss man noch CategoryName sein
    private String features;
    private boolean freeDelivery;
    private String deliveryIn;
    private List<String> images;
    private BigDecimal price;
    private MoneySymbol moneySymbol;
    private String moneyType;
    private int available;
}
//TODO: Wir können eine Red Cramp werden.
//Hier soll man manche Felder geändert werden.