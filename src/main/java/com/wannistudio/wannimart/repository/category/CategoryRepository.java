package com.wannistudio.wannimart.repository.category;

import com.wannistudio.wannimart.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  Category findCategoryByName(String name);
}
