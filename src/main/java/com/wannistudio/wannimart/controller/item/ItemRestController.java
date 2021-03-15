package com.wannistudio.wannimart.controller.item;

import com.wannistudio.wannimart.controller.api.ApiResult;
import com.wannistudio.wannimart.domain.category.Category;
import com.wannistudio.wannimart.domain.item.Item;
import com.wannistudio.wannimart.service.CategoryService;
import com.wannistudio.wannimart.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ItemRestController {
  private final ItemService itemService;
  private final CategoryService categoryService;

  @PostMapping("/item/create")
  public ApiResult<ItemResult> createItem(@RequestBody ItemRequest itemRequest) {

    Item item = itemService.saveItem(itemRequest);
    Category category = categoryService.findByCategoryName(itemRequest.getCategoryName());
    itemService.addCategory(item.getId(), category.getId());

    return ApiResult.OK(
      new ItemResult(
              Optional.of(category).map(CategoryInfo::new)
                      .orElseThrow(NullPointerException::new),
              item
      )
    );
  }
}
