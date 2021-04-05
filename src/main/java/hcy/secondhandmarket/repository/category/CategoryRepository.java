package hcy.secondhandmarket.repository.category;

import hcy.secondhandmarket.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
