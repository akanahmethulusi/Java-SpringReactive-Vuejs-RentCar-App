package at.codefabrik.Springreactivevuejs.product.model.product;

import at.codefabrik.Springreactivevuejs.product.entity.MoneySymbol;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class ProductSearchRequest {
    private double lat;
    private double lon;
    private double radiusKm;
    //@JsonFormat(pattern = "yyyy-MM-dd") // Specify the date format for JSON serialization/deserialization
    //private LocalDateTime pickupDate;
    private LocalDate pickupDate;
    //@JsonFormat(pattern = "yyyy-MM-dd") // Specify the date format for JSON serialization/deserialization
    //private LocalDateTime returnDate;
    private LocalDate returnDate;
    private MoneySymbol moneySymbol;
}
