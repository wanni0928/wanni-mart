package com.wannistudio.wannimart.controller.item;

import com.wannistudio.wannimart.domain.connect.ItemCategory;
import lombok.Getter;

@Getter
public class ItemCategoryDto {
  private String categoryName;

  public ItemCategoryDto(ItemCategory itemCategory) {
    categoryName = itemCategory.getCategory().getName();
  }
}
