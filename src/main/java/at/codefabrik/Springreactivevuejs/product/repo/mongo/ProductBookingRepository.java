package at.codefabrik.Springreactivevuejs.product.repo.mongo;

import at.codefabrik.Springreactivevuejs.product.entity.Booking;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ProductBookingRepository extends ReactiveMongoRepository<Booking, String> {
    Flux<Booking> findByProductIdAndStatusIn(String productId, List<Booking.BookingStatus> statuses);
}
