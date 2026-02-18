package at.codefabrik.Springreactivevuejs.product.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "productPrice")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ProductPrice {
    private String id;
    private String productId;
    private String price;
    private MoneySymbol moneySymbol;

}
