package hcy.secondhandmarket.service.category;

import hcy.secondhandmarket.domain.Category;
import hcy.secondhandmarket.dto.category.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryResponseDTO> findAll();

    default CategoryResponseDTO entityToDTO(Category category) {
        return CategoryResponseDTO.builder().name(category.getName()).build();
    }

}
