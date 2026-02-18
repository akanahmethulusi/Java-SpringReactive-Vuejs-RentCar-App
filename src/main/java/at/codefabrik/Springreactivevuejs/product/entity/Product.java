package at.codefabrik.Springreactivevuejs.product.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Document(collection = "product")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
public class Product {

    @Id
    private String id;
    private String name;
    private String code;
    private String description;
    private String companyId;
    private String categoryId;
    private String features;
    private List<ProductImage> productImage;
    private HashMap<MoneySymbol, BigDecimal> priceMap; // sorgt dafür, dass extra für die preistypen eine Collection erstellt wird
    private boolean active;
    /**
     * Hier wird Für eine Preis ein Feld definiert, welches den Preis in der jeweiligen Währung speichert.
     * Deswegen braucht man nicht mehr ProductPrice.java, ProductPriceRepository.java und ProductPriceService.java.
     *
     */

}
