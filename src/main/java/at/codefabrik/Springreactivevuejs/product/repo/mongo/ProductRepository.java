package at.codefabrik.Springreactivevuejs.product.repo.mongo;

import at.codefabrik.Springreactivevuejs.product.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product,String> {
}
