package at.codefabrik.Springreactivevuejs.product.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collection = "categorie")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Category {

    @Id
    private String id;
    private String name;
    private String code;
}
