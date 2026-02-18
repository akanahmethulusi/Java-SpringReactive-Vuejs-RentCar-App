package at.codefabrik.Springreactivevuejs.product.repo.elastic;

import at.codefabrik.Springreactivevuejs.product.entity.ProductEs;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface ProductEsRepository extends ReactiveMongoRepository<ProductEs, String> {
public interface ProductEsRepository extends ReactiveElasticsearchRepository<ProductEs, String> {
}