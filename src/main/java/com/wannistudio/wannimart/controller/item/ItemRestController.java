package com.wannistudio.wannimart.controller.item;

import com.wannistudio.wannimart.controller.api.ApiResult;
import com.wannistudio.wannimart.controller.member.JoinRequest;
import com.wannistudio.wannimart.domain.category.Category;
import com.wannistudio.wannimart.domain.common.Id;
import com.wannistudio.wannimart.domain.item.Item;
import com.wannistudio.wannimart.service.CategoryService;
import com.wannistudio.wannimart.service.ItemService;
import com.wannistudio.wannimart.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ItemRestController {
  private final ItemService itemService;
  private final CategoryService categoryService;

  @PostMapping("/item/create")
  public ApiResult<ItemResult> createItem(@RequestBody ItemRequest itemRequest) {

    System.out.println("item request : " + itemRequest);

    Item item = itemService.saveItem(itemRequest);
    System.out.println("아이템 : ");
    System.out.println(item.getId());
    System.out.println(item.getName());
    Category category = categoryService.findByCategoryName(itemRequest.getCategoryName());
    System.out.println("카테고리 : ");
    System.out.println(category.getId());
    System.out.println(category.getName());
    itemService.addCategory(item.getId(), category.getId());

    return ApiResult.OK(
      new ItemResult(category, item)
    );
  }
}
