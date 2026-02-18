package at.codefabrik.Springreactivevuejs.product.service.product;

import at.codefabrik.Springreactivevuejs.product.entity.Booking;
import at.codefabrik.Springreactivevuejs.product.repo.mongo.ProductBookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductAvailableService {
    private final ProductBookingRepository productBookingRepository;

    public int getByProductId(String productId){
        return 10;
    }

    //DB-Check, ob es eine Überschneidung(Overlaps) gibt, wenn ja, dann ist das Produkt nicht verfügbar
    public boolean isAvailable(String productId, LocalDate pickupDate, LocalDate returnDate) {
        List<Booking> bookings = productBookingRepository.findByProductIdAndStatusIn(
                        productId, List.of(
                                Booking.BookingStatus.RESERVED,
                                Booking.BookingStatus.CONFIRMED
                        )
                )
                .collectList()
                .block();
        for(Booking booking: bookings){
            boolean overlaps = booking.getPickupDate().isBefore(returnDate) &&
                               booking.getReturnDate().isAfter(pickupDate);
            if(overlaps){       //Wenn es eine Überschneidung(Overlaps) gibt, ist das Produkt nicht verfügbar
                return false;
            }
        }
        return true;
    }
}
