package com.wannistudio.wannimart.service;

import com.wannistudio.wannimart.controller.item.ItemSearch;
import com.wannistudio.wannimart.domain.connect.ItemCategoryQueryDto;
import com.wannistudio.wannimart.repository.item.ItemCacheRepository;
import com.wannistudio.wannimart.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemCacheService {
  private final ItemRepository itemRepository;
  private final ItemCacheRepository itemCacheRepository;
  private final Pageable pageable = PageRequest.of(0, 20).first();

  @Scheduled(cron = "* * * * * *")
  public void updateFirstItemPage() {
    itemCacheRepository.save(itemRepository.findFirstPageItemCategory(pageable));
  }

  public Page<ItemCategoryQueryDto> getFirstItemPage() {
    return itemCacheRepository.getFirstPage(pageable);
  }
}
