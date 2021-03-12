package com.wannistudio.wannimart.service;

import com.wannistudio.wannimart.domain.category.Category;
import com.wannistudio.wannimart.domain.item.DeliveryType;
import com.wannistudio.wannimart.domain.item.Food;
import com.wannistudio.wannimart.domain.item.Item;
import com.wannistudio.wannimart.domain.item.PackageType;
import com.wannistudio.wannimart.repository.category.CategoryRepository;
import com.wannistudio.wannimart.repository.item.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ItemServiceTest {
  @Autowired
  ItemService itemService;
  @Autowired
  ItemRepository itemRepository;
  @Autowired
  CategoryService categoryService;
  @Autowired
  CategoryRepository categoryRepository;

//  @BeforeEach
//  void setBaseCategory() {
//    // given
//    Category category = new Category();
//    category.setName("식품");
//    Category parent = categoryService.saveParentCategory(category);
//
//    Category subCategory1 = new Category();
//    Category subCategory2 = new Category();
//    Category subCategory3 = new Category();
//
//    subCategory1.setName("정육");
//    subCategory2.setName("해산물");
//    subCategory3.setName("채소");
//
//    final Category child1 = categoryRepository.save(subCategory1);
//    final Category child2 = categoryRepository.save(subCategory2);
//    final Category child3 = categoryRepository.save(subCategory3);
//
//    // when
//    parent.addChildCategory(child1);
//    parent.addChildCategory(child2);
//    parent.addChildCategory(child3);
//  }

  @Test
  void createItem() {

        // given
    Category category = new Category();
    category.setName("식품");
    Category parent = categoryService.saveParentCategory(category);

    Category subCategory1 = new Category();
    Category subCategory2 = new Category();
    Category subCategory3 = new Category();

    subCategory1.setName("정육");
    subCategory2.setName("해산물");
    subCategory3.setName("채소");

    final Category child1 = categoryRepository.save(subCategory1);
    final Category child2 = categoryRepository.save(subCategory2);
    final Category child3 = categoryRepository.save(subCategory3);

    // when
    parent.addChildCategory(child1);
    parent.addChildCategory(child2);
    parent.addChildCategory(child3);

    Food food = new Food();
    food.setName("양고기");
    food.setSummary("맛있는 양고기");
    food.setPrice(10000);
    food.setStockQuantity(10);
    food.setDescription("맛있고 싸다 좀 사라");
    food.setDeliveryType(DeliveryType.EARLY);
    food.setPackageType(PackageType.ECO_PACKAGE);
    food.setImportFrom("국내산");
    food.setAllergyInformation("없음");
    food.setExpiration("사실 이미 좀 상함...");

    Item saveItem = itemService.saveItem(food);

    itemService.addCategory(saveItem.getId(), child1.getId(), "ㅈㅈ");
  }

}