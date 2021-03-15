package com.wannistudio.wannimart.controller.item;

import com.wannistudio.wannimart.domain.item.Item;
import lombok.Getter;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
public class ItemResult {
  private final CategoryInfo categoryInfo;
  private final Item item;

  public ItemResult(CategoryInfo categoryInfo, Item item) {
    checkNotNull(categoryInfo, "category must be provided.");
    checkNotNull(item, "item must be provided.");

    this.categoryInfo = categoryInfo;
    this.item = item;
  }
}
