package hcy.secondhandmarket.service.category;

import hcy.secondhandmarket.domain.Category;
import hcy.secondhandmarket.dto.category.CategoryResponseDTO;
import hcy.secondhandmarket.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDTO> findAll() {
        log.info("Find All Category");

        // 이름 순으로 정렬해서 가져옴.
        List<Category> result = categoryRepository.findAll(Sort.by(Sort.Direction.ASC));

        return result.stream().map(this::entityToDTO).collect(Collectors.toList());
    }
}
