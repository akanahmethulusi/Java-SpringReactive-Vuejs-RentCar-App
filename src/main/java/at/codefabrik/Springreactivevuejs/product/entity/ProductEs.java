package at.codefabrik.Springreactivevuejs.product.entity;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Builder
//@Document(indexName = "products", shards = 1, replicas = 0) //Does come from spring-data-mongodb
@Document(indexName = "product") //Does come from spring-data-elasticsearch
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ProductEs {
    private String id;
    private String name;
    private String code;
    private String description;
    //private String companyId;
    private CompanyEs seller;
    //private String categoryId;
    private CategoryEs category;
    private String features;
    private List<String> images;
    private HashMap<MoneySymbol, BigDecimal> priceMapEs;
    private boolean active;
    @GeoPointField
    private GeoPoint location;      //oder private double lat; private double lon;
}
