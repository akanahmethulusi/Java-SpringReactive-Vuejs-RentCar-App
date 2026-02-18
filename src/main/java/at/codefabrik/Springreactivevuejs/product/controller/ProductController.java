package at.codefabrik.Springreactivevuejs.product.controller;

import at.codefabrik.Springreactivevuejs.product.model.product.*;
import at.codefabrik.Springreactivevuejs.product.service.product.ProductPriceService;
import at.codefabrik.Springreactivevuejs.product.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;
    private final ProductPriceService productPriceService;

    @GetMapping
    public Flux<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductDetailsResponse> getProductDetails(@PathVariable("id") String id) {
        return productService.getProductDetails(id);
    }

    @PostMapping("/{id}/price")
    public Mono<ProductPricingResponse> calculatePrice(@PathVariable String id, @RequestBody ProductBookingRequest request) {
        return productPriceService.calculatePrice(id, request);
    }

    @PostMapping("/search")
    public Flux<ProductResponse> searchProduct(@RequestBody ProductSearchRequest request) {
        System.out.println(request);
        return productService.searchProducts(request);
    }
}
