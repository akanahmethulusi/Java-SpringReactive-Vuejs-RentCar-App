package at.codefabrik.Springreactivevuejs.product.repo.mongo;

import at.codefabrik.Springreactivevuejs.product.entity.ProductPrice;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductPriceRepository extends ReactiveMongoRepository<ProductPrice,String> {
}
