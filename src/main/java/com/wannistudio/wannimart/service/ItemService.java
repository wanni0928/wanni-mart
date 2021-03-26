package com.wannistudio.wannimart.service;

import com.wannistudio.wannimart.controller.item.ItemRequest;
import com.wannistudio.wannimart.controller.item.ItemSearch;
import com.wannistudio.wannimart.domain.category.Category;
import com.wannistudio.wannimart.domain.connect.ItemCategory;
import com.wannistudio.wannimart.domain.connect.ItemCategoryQueryDto;
import com.wannistudio.wannimart.domain.item.Item;
import com.wannistudio.wannimart.exception.NotFoundException;
import com.wannistudio.wannimart.repository.category.CategoryRepository;
import com.wannistudio.wannimart.repository.categoryitem.CategoryItemRepository;
import com.wannistudio.wannimart.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
  private final ItemRepository itemRepository;
  private final CategoryItemRepository categoryItemRepository;
  private final CategoryService categoryService;
  private final CategoryRepository categoryRepository;

  @Transactional
  public Item saveItem(Item item) {
    // 아이템 저장
    return itemRepository.save(item);
  }

  @Transactional
  public Item saveItem(ItemRequest itemRequest) {
    Item item = Item.from(itemRequest);
    return itemRepository.save(item);
  }


  @Transactional
  public void addCategory(Long itemId, Long categoryId) {
    Item item = itemRepository.findById(itemId).orElseThrow(NullPointerException::new);
    Category category = categoryRepository.findById(categoryId).orElseThrow(NullPointerException::new);

    ItemCategory itemCategory = ItemCategory.createItemCategory(category);

    item.addItemCategory(itemCategory);
    category.addItemCategory(itemCategory);

    categoryItemRepository.save(itemCategory);

  }

  @Transactional
  public void addItemWithCategory(ItemRequest itemRequest) {
    Item item = saveItem(itemRequest);
    Category category = categoryService.findByCategoryName(itemRequest.getCategoryName());
    addCategory(item.getId(), category.getId());
  }

  public void updateItem(Long itemId, String name, int price, int stockQuantity) {
    Item findItem = itemRepository.findById(itemId).orElseThrow(NullPointerException::new);
  }

  public Page<Item> findAll(Pageable pageable) {
    return itemRepository.findAllSimple(pageable);
  }

  public List<Item> findAll(int offset, int limit) {
    return itemRepository.findAllWithCategory(offset, limit);
  }

  public List<ItemCategoryQueryDto> findAllItemCategory(int offset, int limit , ItemSearch itemSearch) {
    return itemRepository.findAllWithItemCategory(offset, limit, itemSearch);
  }

  public Page<ItemCategoryQueryDto> findAllItemCategoryWithPage(Pageable pageable, ItemSearch itemSearch) {
    return itemRepository.findAllWithPageableItemCategory(pageable, itemSearch);
  }
}
