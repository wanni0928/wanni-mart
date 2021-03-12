package com.wannistudio.wannimart.service;

import com.wannistudio.wannimart.domain.category.Category;
import com.wannistudio.wannimart.domain.connect.ItemCategory;
import com.wannistudio.wannimart.domain.item.Item;
import com.wannistudio.wannimart.repository.category.CategoryRepository;
import com.wannistudio.wannimart.repository.categoryitem.CategoryItemRepository;
import com.wannistudio.wannimart.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  public void addCategory(Long itemId, Long categoryId, String desc) {
    Item item = itemRepository.findById(itemId).orElseThrow(NullPointerException::new);
    Category category = categoryRepository.findById(categoryId).orElseThrow(NullPointerException::new);

    ItemCategory itemCategory = new ItemCategory();
    itemCategory.setItem(item);
    itemCategory.setCategory(category);
    itemCategory.setDescription(desc);
    item.addItemCategory(itemCategory);
    category.addItemCategory(itemCategory);

    categoryItemRepository.save(itemCategory);
  }

  public void updateItem(Long itemId, String name, int price, int stockQuantity) {
    Item findItem = itemRepository.findById(itemId).orElseThrow(NullPointerException::new);
  }
}
