package at.codefabrik.Springreactivevuejs.product.model.product;

import at.codefabrik.Springreactivevuejs.product.entity.MoneySymbol;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Data
@Builder
public class ProductSaveRequest {
    private String id;
    private String name;
    //private String code;
    private String description;
    //private String companyId;
    private String sellerId;
    private String categoryId;
    private String features;
    //private Boolean freeDelivery;
    //private String deliveryIn;
    private List<String> images;
    //private BigDecimal price;
    private HashMap<MoneySymbol, BigDecimal> priceMap;
    //private MoneySymbol moneySymbol;  //Das braucht man nicht mehr, weil man ja eine priceMap hat.
    //private boolean active;
    //private int available;
    private BigDecimal available;

}
