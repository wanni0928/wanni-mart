package com.wannistudio.wannimart.controller.item;

import com.wannistudio.wannimart.controller.api.ApiResult;
import com.wannistudio.wannimart.domain.category.Category;
import com.wannistudio.wannimart.domain.connect.ItemCategoryQueryDto;
import com.wannistudio.wannimart.domain.item.Item;
import com.wannistudio.wannimart.service.CategoryService;
import com.wannistudio.wannimart.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.wannistudio.wannimart.controller.api.ApiResult.*;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toSet;

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

    return OK(
      new ItemResult(
              Optional.of(category).map(CategoryInfo::new)
                      .orElseThrow(NullPointerException::new),
              item
      )
    );
  }

  @GetMapping("/item/v1/list")
  public ApiResult<Page<Item>> items(Pageable pageable) {
    return OK(
            itemService.findAll(pageable)
    );
  }

  @GetMapping("/item/v2/list")
  public ApiResult<List<ItemDto>> itemsV2(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                       @RequestParam(value = "limit", defaultValue = "20") int limit) {
    List<Item> items = itemService.findAll(offset, limit);
    return OK(items.stream()
            .map(ItemDto::new)
            .collect(toList()));
  }

  @GetMapping("/item/v3/list")
  public ApiResult<List<ItemCategoryQueryDto>> itemsV3(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                        @RequestParam(value = "limit", defaultValue = "20") int limit) {
    return OK(itemService.findAllItemCategory(offset, limit));
  }
}