package com.wannistudio.wannimart.service;

import com.wannistudio.wannimart.domain.category.Category;
import com.wannistudio.wannimart.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
  private final CategoryRepository categoryRepository;

  public Category findById(Long id) {
    return categoryRepository.findById(id).orElseThrow(NullPointerException::new);
  }

  public Category findByCategoryName(String categoryName) {
    return categoryRepository.findCategoryByName(categoryName);
  }

  @Transactional
  public Category saveParentCategory(Category category) {
    return categoryRepository.save(category);
  }

  @Transactional
  public void saveChildCategory(Long parentId, Category child) {
    final Optional<Category> parentCategory = categoryRepository.findById(parentId);

    parentCategory.ifPresent(parent -> {
      final Category savedChild = categoryRepository.save(child);
      parent.addChildCategory(savedChild);
    });
  }
}
