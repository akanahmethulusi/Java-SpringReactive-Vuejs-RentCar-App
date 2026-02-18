package at.codefabrik.Springreactivevuejs.product.service.category;

import at.codefabrik.Springreactivevuejs.product.entity.Category;
import at.codefabrik.Springreactivevuejs.product.model.category.CategoryResponse;
import at.codefabrik.Springreactivevuejs.product.model.category.CategorySaveRequest;
import at.codefabrik.Springreactivevuejs.product.repo.mongo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Flux<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().map(this::mapToDto);
    }

    public Category getById(String id) {
        return categoryRepository.findById(id).block();
    }

    public CategoryResponse save(CategorySaveRequest request) {
        Category category = Category.builder()
                .name(request.getName())
                .code("C" + request.getName().charAt(0) + System.currentTimeMillis())
                .build();
        category = categoryRepository.save(category).block();
        return mapToDto(category);
    }

    private CategoryResponse mapToDto(Category category) {
        if(category == null) {
            return null;
        }
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
