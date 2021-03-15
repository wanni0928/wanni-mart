package com.wannistudio.wannimart.controller.item;

import com.wannistudio.wannimart.domain.category.Category;
import com.wannistudio.wannimart.domain.item.Item;
import lombok.Getter;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
public class ItemResult {
  private final Category category;
  private final Item item;

  public ItemResult(Category category, Item item) {
    checkNotNull(category, "category must be provided.");
    checkNotNull(item, "item must be provided.");

    this.category = category;
    this.item = item;
  }
}
