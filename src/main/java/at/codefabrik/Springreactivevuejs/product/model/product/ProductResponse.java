package at.codefabrik.Springreactivevuejs.product.model.product;

import at.codefabrik.Springreactivevuejs.product.entity.MoneySymbol;
import at.codefabrik.Springreactivevuejs.product.model.ProductSellerResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class ProductResponse {
    private String id;
    private String name;
    private String code;
    private String description;
    private ProductSellerResponse seller;   //private String companyId;
    private String categoryId;
    private String features;
    private String image;
    private int available;
    private Boolean freeDelivery;
    private String deliveryIn;
    private BigDecimal price;
    private MoneySymbol moneySymbol;
    private String moneyType;               //es wird hier nur sysmbol ang
    private boolean active;
}
