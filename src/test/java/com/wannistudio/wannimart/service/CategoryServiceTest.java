package com.wannistudio.wannimart.service;

import com.wannistudio.wannimart.domain.category.Category;
import com.wannistudio.wannimart.repository.category.CategoryRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CategoryServiceTest {
  @Autowired
  CategoryService categoryService;
  @Autowired
  CategoryRepository categoryRepository;

  @BeforeEach
  void setBaseCategory() {
    // given
    Category category = new Category();
    category.setName("식품");
    Category parent = categoryService.saveParentCategory(category);

    Category subCategory1 = new Category();
    Category subCategory2 = new Category();
    Category subCategory3 = new Category();

    subCategory1.setName("정육");
    subCategory2.setName("해산물");
    subCategory3.setName("채소");

    final Category child1 = categoryRepository.save(subCategory1);
    final Category child2 = categoryRepository.save(subCategory2);
    final Category child3 = categoryRepository.save(subCategory3);

    // when
    parent.addChildCategory(child1);
    parent.addChildCategory(child2);
    parent.addChildCategory(child3);
  }

  @Test
  void createCategory() {
    // given
    Category parent = categoryService.findById(1L);

    Category child = new Category();
    child.setName("유제품");

    // when
    categoryService.saveChildCategory(parent.getId(), child);

    final List<Category> children = categoryService.findById(parent.getId()).getChildren();

    final Category byId = categoryService.findById(2L);

    // then
    assertThat(children.size()).isEqualTo(4);
    assertThat(categoryRepository.findCategoryByName("정육"));
    assertThat(categoryRepository.findCategoryByName("유제품"));
  }
}