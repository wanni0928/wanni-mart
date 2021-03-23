package com.wannistudio.wannimart.service;

import com.wannistudio.wannimart.controller.item.ItemRequest;
import com.wannistudio.wannimart.domain.category.Category;
import com.wannistudio.wannimart.domain.item.DeliveryType;
import com.wannistudio.wannimart.domain.item.Food;
import com.wannistudio.wannimart.domain.item.Item;
import com.wannistudio.wannimart.domain.item.PackageType;
import com.wannistudio.wannimart.repository.category.CategoryRepository;
import com.wannistudio.wannimart.repository.item.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
@Transactional
class ItemServiceTest {
  @Autowired
  ItemService itemService;
  @Autowired
  ItemRepository itemRepository;
  @Autowired
  CategoryService categoryService;
  @Autowired
  CategoryRepository categoryRepository;

  @BeforeEach
  void setBaseCategory() {
    // set parent category
    categoryService.saveParentCategory(new Category("채소"));

    Category parent = categoryRepository.findCategoryByName("채소");

    Category child1 = new Category("고구마,감자,당근");
    Category child2 = new Category("오신채");
    Category child3 = new Category("푸른채소");
    Category child4 = new Category("냉동, 간편채소");
    Category child5 = new Category("버섯");

    categoryService.saveChildCategory(parent.getId(), child1);
    categoryService.saveChildCategory(parent.getId(), child2);
    categoryService.saveChildCategory(parent.getId(), child3);
    categoryService.saveChildCategory(parent.getId(), child4);
    categoryService.saveChildCategory(parent.getId(), child5);
  }

  @Test
  @DisplayName("일반적인 아이템 생성.")
  void createItem() {
    ItemRequest itemRequest = ItemRequest.builder()
            .parentCategoryName("채소")
            .categoryName("고구마/감자,당근")
            .itemName("호박고구마")
            .summary("호.박.고.구.마")
            .price(10000)
            .stockQuantity(2320)
            .unit("kg")
            .volume("4kg(개당 평균 200g)")
            .description("호.박.고구마, 호박!고!구!마!")
            .deliveryType(DeliveryType.EARLY)
            .packageType(PackageType.ECO_PACKAGE)
            .importFrom("국내산")
            .allergyInformation("해당사항 없음")
            .expiration("배송일로부터 약 한달")
            .build();

    Item saveItem = itemService.saveItem(itemRequest);

    assertThat(saveItem).isNotNull().isSameAs(itemRepository.findById(saveItem.getId()).orElseThrow(NullPointerException::new));
  }

  @Test
  @DisplayName("존재하지 않는 카테고리를 입력하여, 상품을 등록 했으므로, 오류가 발생한다.")
  void createItemWithWrongCategory() {
    ItemRequest itemRequest = ItemRequest.builder()
            .parentCategoryName("방사능")
            .categoryName("핵폐기물")
            .itemName("우라늄")
            .summary("영롱하게 빛나는 우라늄")
            .price(10000)
            .stockQuantity(32320)
            .unit("t")
            .volume("2t")
            .description("어떻게 구했는지는 모르겠지만, 일단 팔아본다.")
            .deliveryType(DeliveryType.EARLY)
            .packageType(PackageType.ECO_PACKAGE)
            .importFrom("일본산")
            .allergyInformation("해당사항 없음")
            .expiration("배송일로부터 약 수천년")
            .build();

    assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() -> itemService.saveItem(itemRequest));
  }
}