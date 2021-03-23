package com.wannistudio.wannimart.controller.item;

import com.wannistudio.wannimart.domain.item.Item;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
public class ItemResult {

  @ApiModelProperty(value = "카테고리 정보", required = true)
  private final CategoryInfo categoryInfo;

  @ApiModelProperty(value = "상품 정보", required = true)
  private final Item item;

  public ItemResult(CategoryInfo categoryInfo, Item item) {
    checkNotNull(categoryInfo, "category must be provided.");
    checkNotNull(item, "item must be provided.");

    this.categoryInfo = categoryInfo;
    this.item = item;
  }
}
