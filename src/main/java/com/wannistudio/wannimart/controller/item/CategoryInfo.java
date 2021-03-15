package com.wannistudio.wannimart.controller.item;

import com.wannistudio.wannimart.domain.category.Category;
import lombok.*;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@NoArgsConstructor
public class CategoryInfo {
  private Long id;

  private String name;

  private String parentCategoryName;

  public CategoryInfo(Category category) {
    copyProperties(category, this);
    this.parentCategoryName = category.getParent() == null ? "none" : category.getParent().getName();
  }
}
