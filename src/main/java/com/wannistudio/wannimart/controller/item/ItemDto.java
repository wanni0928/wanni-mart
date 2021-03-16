package com.wannistudio.wannimart.controller.item;

import com.querydsl.core.annotations.QueryProjection;
import com.wannistudio.wannimart.domain.connect.ItemCategory;
import com.wannistudio.wannimart.domain.item.DeliveryType;
import com.wannistudio.wannimart.domain.item.Item;
import com.wannistudio.wannimart.domain.item.PackageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ItemDto {
  private Item item;
  private Set<ItemCategoryDto> categoryItems;

  public ItemDto(Item item) {
    this.item = item;
    this.categoryItems = item.getCategoryItems()
            .stream()
            .map(ItemCategoryDto::new)
            .collect(Collectors.toSet());
  }
}
