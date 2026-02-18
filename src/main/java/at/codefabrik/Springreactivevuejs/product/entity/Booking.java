/*
@k@n
ohne die kannst du keine Verfügbarkeit prüfen
 */
package at.codefabrik.Springreactivevuejs.product.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "booking")
@Getter
@Setter
@Builder
public class Booking {

    @Id
    private String id;
    private String productId;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private BookingStatus status;       //RESERVED, CONFIRMED, CANCELLED

    public enum BookingStatus {
        RESERVED,
        CONFIRMED,
        CANCELLED
    }
}
