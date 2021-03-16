package com.wannistudio.wannimart.repository.item;

import com.wannistudio.wannimart.controller.item.ItemDto;
import com.wannistudio.wannimart.domain.connect.ItemCategoryQueryDto;
import com.wannistudio.wannimart.domain.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface ItemRepositoryCustomQuery {
  Page<Item> findAllSimple(Pageable pageable);
  Page<ItemCategoryQueryDto> findAllAdvanced(Pageable pageable);
  List<Item> findAllWithCategory(int offset, int limit);

  List<ItemCategoryQueryDto> findAllWithItemCategory(int offset, int limit);
}
