package at.codefabrik.Springreactivevuejs.product.service.product;

import at.codefabrik.Springreactivevuejs.product.entity.*;
import at.codefabrik.Springreactivevuejs.product.model.product.ProductSearchRequest;
import at.codefabrik.Springreactivevuejs.product.repo.elastic.ProductEsRepository;
import at.codefabrik.Springreactivevuejs.product.service.category.CategoryService;
import co.elastic.clients.elasticsearch._types.GeoLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductEsService {
    private final ProductEsRepository productEsRepository;
    private final CategoryService categoryService;
    private final ReactiveElasticsearchOperations reactiveElasticsearchOperations;


    /**
     * @Async
     * Wenn wir im ProductService.java product=productRepository.save(product).block(); aufrufen,
     * dann blockieren wir den aktuellen Thread bis die Operation abgeschlossen ist.
     */

    public Mono<ProductEs> saveNewProduct(Product product){
        ProductEs productEs = ProductEs.builder()
                .id(product.getId())
                .name(product.getName())
                .code(product.getCode())
                .description(product.getDescription())
                //.companyId(product.getCompanyId())
                .seller(CompanyEs.builder().id(product.getCompanyId()).name(product.getName() + " Seller Name").build())
                //.categoryId(product.getCategoryId())
                //.category(CategoryEs.builder().id(product.getCategoryId()).name("Category Name").build())
                .category(getProductCategory(product.getCategoryId()))
                .features(product.getFeatures())
                .active(product.isActive())
                .priceMapEs(product.getPriceMap())
                // TODO: Hier muss man noch eine Ordersystem integrieren, um die preis umzuwandeln.
                .images(product.getProductImage().stream().map(img->img.getUrl()).toList())
                /*.images(product.getProductImage().stream()
                        .map(ProductImage::getUrl)
                        .collect(Collectors.toList()))
                 */
                .build();
        return productEsRepository.save(productEs);
    }

    public CategoryEs getProductCategory(String categoryId){
        Category category = categoryService.getById(categoryId);
        return CategoryEs.builder()
                .name(category.getName())
                .id(category.getId())
                .code(category.getCode())
                .build();
    }

    public Flux<ProductEs> findAll(){
        return productEsRepository.findAll();
    }

    public Mono<ProductEs> findById(String id){
        return productEsRepository.findById(id);
    }

    public Flux<ProductEs> search(ProductSearchRequest request) {
        NativeQuery query = NativeQuery.builder()
                .withQuery(q -> q
                        .geoDistance(g -> g
                                .field("location")
                                .distance(request.getRadiusKm() + "km")
                                .location(
                                        GeoLocation.of(gl -> gl
                                                .latlon(ll -> ll
                                                        .lat(request.getLat())
                                                        .lon(request.getLon())
                                                )
                                        )
                                )
                        )
                )
                .build();

        return reactiveElasticsearchOperations
                .search(query, ProductEs.class)
                .map(SearchHit::getContent);
    }

}
