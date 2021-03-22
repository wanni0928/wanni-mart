package com.wannistudio.wannimart.service;

import com.wannistudio.wannimart.domain.category.Category;
import com.wannistudio.wannimart.repository.category.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CategoryServiceTest {
  @Autowired
  CategoryService categoryService;
  @Autowired
  CategoryRepository categoryRepository;

  @BeforeEach
  void setBaseCategory() {
    // set parent category
    categoryService.saveParentCategory(new Category("채소"));
    categoryService.saveParentCategory(new Category("과일"));
    categoryService.saveParentCategory(new Category("축/농산물/수산물"));
    categoryService.saveParentCategory(new Category("간편식"));
    categoryService.saveParentCategory(new Category("양념"));
    categoryService.saveParentCategory(new Category("음료"));
    categoryService.saveParentCategory(new Category("과자"));
    categoryService.saveParentCategory(new Category("베이커리"));
    categoryService.saveParentCategory(new Category("헬스"));
    categoryService.saveParentCategory(new Category("생활"));
    categoryService.saveParentCategory(new Category("전자"));
    categoryService.saveParentCategory(new Category("반려동물"));
  }

  @Test
  @DisplayName("채소 부모카테고리와 자식 카테고리")
  void createVegetableCategories() {
    // given
    Category parent = categoryRepository.findCategoryByName("채소");
    Category child1 = new Category("고구마/감자/당근");
    Category child2 = new Category("오신채");
    Category child3 = new Category("푸른채소");
    Category child4 = new Category("냉동, 간편채소");
    Category child5 = new Category("버섯");

    // when
    categoryService.saveChildCategory(parent.getId(), child1);
    categoryService.saveChildCategory(parent.getId(), child2);
    categoryService.saveChildCategory(parent.getId(), child3);
    categoryService.saveChildCategory(parent.getId(), child4);
    categoryService.saveChildCategory(parent.getId(), child5);

    final List<Category> children = categoryService.findById(parent.getId()).getChildren();

    // then
    assertThat(children).allMatch(category -> category.getParent().getName().equals("채소"));
    assertThat(children)
            .anyMatch(category -> category.getName().contains("고구마/감자/당근"))
            .anyMatch(category -> category.getName().contains("오신채"))
            .anyMatch(category -> category.getName().contains("푸른채소"))
            .anyMatch(category -> category.getName().contains("냉동, 간편채소"))
            .anyMatch(category -> category.getName().contains("버섯"))
    ;
  }

  @Test
  @DisplayName("과일 부모카테고리와 자식 카테고리")
  void createFruitsCategories() {
    // given
    Category parent = categoryRepository.findCategoryByName("과일");
    Category child1 = new Category("국산과일");
    Category child2 = new Category("수입과일");
    Category child3 = new Category("간편과일");
    Category child4 = new Category("냉동/건과일");
    Category child5 = new Category("견과류");

    // when
    categoryService.saveChildCategory(parent.getId(), child1);
    categoryService.saveChildCategory(parent.getId(), child2);
    categoryService.saveChildCategory(parent.getId(), child3);
    categoryService.saveChildCategory(parent.getId(), child4);
    categoryService.saveChildCategory(parent.getId(), child5);

    final List<Category> children = categoryService.findById(parent.getId()).getChildren();

    // then
    assertThat(children).allMatch(category -> category.getParent().getName().equals("과일"));
    assertThat(children)
            .anyMatch(category -> category.getName().contains("국산과일"))
            .anyMatch(category -> category.getName().contains("수입과일"))
            .anyMatch(category -> category.getName().contains("간편과일"))
            .anyMatch(category -> category.getName().contains("냉동/건과일"))
            .anyMatch(category -> category.getName().contains("견과류"))
    ;
  }

  @Test
  @DisplayName("축/농산물/수산물 부모카테고리와 자식 카테고리")
  void createAgriculturalCategories() {
    // given
    Category parent = categoryRepository.findCategoryByName("축/농산물/수산물");
    Category child1 = new Category("쌀/잡곡");
    Category child2 = new Category("생선류");
    Category child3 = new Category("조개류");
    Category child4 = new Category("수산가공품");
    Category child5 = new Category("해조류");
    Category child6 = new Category("국내산 고기");
    Category child7 = new Category("해외산 고기");
    Category child8 = new Category("계란류");
    Category child9 = new Category("양념육");


    // when
    categoryService.saveChildCategory(parent.getId(), child1);
    categoryService.saveChildCategory(parent.getId(), child2);
    categoryService.saveChildCategory(parent.getId(), child3);
    categoryService.saveChildCategory(parent.getId(), child4);
    categoryService.saveChildCategory(parent.getId(), child5);
    categoryService.saveChildCategory(parent.getId(), child6);
    categoryService.saveChildCategory(parent.getId(), child7);
    categoryService.saveChildCategory(parent.getId(), child8);
    categoryService.saveChildCategory(parent.getId(), child9);

    final List<Category> children = categoryService.findById(parent.getId()).getChildren();

    // then
    assertThat(children).allMatch(category -> category.getParent().getName().equals("축/농산물/수산물"));
    assertThat(children)
            .anyMatch(category -> category.getName().contains("쌀/잡곡"))
            .anyMatch(category -> category.getName().contains("생선류"))
            .anyMatch(category -> category.getName().contains("조개류"))
            .anyMatch(category -> category.getName().contains("수산가공품"))
            .anyMatch(category -> category.getName().contains("해조류"))
            .anyMatch(category -> category.getName().contains("국내산 고기"))
            .anyMatch(category -> category.getName().contains("해외산 고기"))
            .anyMatch(category -> category.getName().contains("계란류"))
            .anyMatch(category -> category.getName().contains("양념육"))
    ;
  }

  @Test
  @DisplayName("간편식 부모카테고리와 자식 카테고리")
  void createConvenienceMealCategories() {
    // given
    Category parent = categoryRepository.findCategoryByName("간편식");
    Category child1 = new Category("샐러드");
    Category child2 = new Category("도시락");
    Category child3 = new Category("면류");
    Category child4 = new Category("분식");
    Category child5 = new Category("안주");
    Category child6 = new Category("선식/시리얼");

    // when
    categoryService.saveChildCategory(parent.getId(), child1);
    categoryService.saveChildCategory(parent.getId(), child2);
    categoryService.saveChildCategory(parent.getId(), child3);
    categoryService.saveChildCategory(parent.getId(), child4);
    categoryService.saveChildCategory(parent.getId(), child5);
    categoryService.saveChildCategory(parent.getId(), child6);

    final List<Category> children = categoryService.findById(parent.getId()).getChildren();

    // then
    assertThat(children).allMatch(category -> category.getParent().getName().equals("간편식"));
    assertThat(children)
            .anyMatch(category -> category.getName().contains("샐러드"))
            .anyMatch(category -> category.getName().contains("도시락"))
            .anyMatch(category -> category.getName().contains("면류"))
            .anyMatch(category -> category.getName().contains("분식"))
            .anyMatch(category -> category.getName().contains("안주"))
            .anyMatch(category -> category.getName().contains("선식/시리얼"))
    ;
  }

  @Test
  @DisplayName("양념 부모카테고리와 자식 카테고리")
  void createSpicyCategories() {
    // given
    Category parent = categoryRepository.findCategoryByName("양념");
    Category child1 = new Category("가루 양념");
    Category child2 = new Category("액체 양념");
    Category child3 = new Category("기름 양념");
    Category child4 = new Category("양념 세트");

    // when
    categoryService.saveChildCategory(parent.getId(), child1);
    categoryService.saveChildCategory(parent.getId(), child2);
    categoryService.saveChildCategory(parent.getId(), child3);
    categoryService.saveChildCategory(parent.getId(), child4);

    final List<Category> children = categoryService.findById(parent.getId()).getChildren();

    // then
    assertThat(children).allMatch(category -> category.getParent().getName().equals("양념"));
    assertThat(children)
            .anyMatch(category -> category.getName().contains("가루 양념"))
            .anyMatch(category -> category.getName().contains("액체 양념"))
            .anyMatch(category -> category.getName().contains("기름 양념"))
            .anyMatch(category -> category.getName().contains("양념 세트"))
    ;
  }

  @Test
  @DisplayName("음료 부모카테고리와 자식 카테고리")
  void createDrinkCategories() {
    // given
    Category parent = categoryRepository.findCategoryByName("음료");
    Category child1 = new Category("생수");
    Category child2 = new Category("탄산");
    Category child3 = new Category("유제품");
    Category child4 = new Category("커피");
    Category child5 = new Category("차");

    // when
    categoryService.saveChildCategory(parent.getId(), child1);
    categoryService.saveChildCategory(parent.getId(), child2);
    categoryService.saveChildCategory(parent.getId(), child3);
    categoryService.saveChildCategory(parent.getId(), child4);
    categoryService.saveChildCategory(parent.getId(), child5);

    final List<Category> children = categoryService.findById(parent.getId()).getChildren();

    // then
    assertThat(children).allMatch(category -> category.getParent().getName().equals("음료"));
    assertThat(children)
            .anyMatch(category -> category.getName().contains("생수"))
            .anyMatch(category -> category.getName().contains("탄산"))
            .anyMatch(category -> category.getName().contains("유제품"))
            .anyMatch(category -> category.getName().contains("커피"))
            .anyMatch(category -> category.getName().contains("차"))
    ;
  }

  @Test
  @DisplayName("과자 부모카테고리와 자식 카테고리")
  void createCandyCategories() {
    // given
    Category parent = categoryRepository.findCategoryByName("과자");
    Category child1 = new Category("초콜릿/캔디류");
    Category child2 = new Category("떡/한과");
    Category child3 = new Category("아이스크림");

    // when
    categoryService.saveChildCategory(parent.getId(), child1);
    categoryService.saveChildCategory(parent.getId(), child2);
    categoryService.saveChildCategory(parent.getId(), child3);

    final List<Category> children = categoryService.findById(parent.getId()).getChildren();

    // then
    assertThat(children).allMatch(category -> category.getParent().getName().equals("과자"));
    assertThat(children)
            .anyMatch(category -> category.getName().contains("초콜릿/캔디류"))
            .anyMatch(category -> category.getName().contains("떡/한과"))
            .anyMatch(category -> category.getName().contains("아이스크림"))
    ;
  }

  @Test
  @DisplayName("베이커리 부모카테고리와 자식 카테고리")
  void createBakeryCategories() {
    // given
    Category parent = categoryRepository.findCategoryByName("베이커리");
    Category child1 = new Category("빵류");
    Category child2 = new Category("잼/버터");
    Category child3 = new Category("케이크");
    Category child4 = new Category("치즈");


    // when
    categoryService.saveChildCategory(parent.getId(), child1);
    categoryService.saveChildCategory(parent.getId(), child2);
    categoryService.saveChildCategory(parent.getId(), child3);
    categoryService.saveChildCategory(parent.getId(), child4);

    final List<Category> children = categoryService.findById(parent.getId()).getChildren();

    // then
    assertThat(children).allMatch(category -> category.getParent().getName().equals("베이커리"));
    assertThat(children)
            .anyMatch(category -> category.getName().contains("빵류"))
            .anyMatch(category -> category.getName().contains("잼/버터"))
            .anyMatch(category -> category.getName().contains("케이크"))
            .anyMatch(category -> category.getName().contains("치즈"))

    ;
  }

  @Test
  @DisplayName("헬스 부모카테고리와 자식 카테고리")
  void createHealthCategories() {
    // given
    Category parent = categoryRepository.findCategoryByName("헬스");
    Category child1 = new Category("영양제");
    Category child2 = new Category("유산균");
    Category child3 = new Category("건강음료");
    Category child4 = new Category("건강환");
    Category child5 = new Category("다이어트");
    Category child6 = new Category("유아동");

    // when
    categoryService.saveChildCategory(parent.getId(), child1);
    categoryService.saveChildCategory(parent.getId(), child2);
    categoryService.saveChildCategory(parent.getId(), child3);
    categoryService.saveChildCategory(parent.getId(), child4);
    categoryService.saveChildCategory(parent.getId(), child5);
    categoryService.saveChildCategory(parent.getId(), child6);

    final List<Category> children = categoryService.findById(parent.getId()).getChildren();

    // then
    assertThat(children).allMatch(category -> category.getParent().getName().equals("헬스"));
    assertThat(children)
            .anyMatch(category -> category.getName().contains("영양제"))
            .anyMatch(category -> category.getName().contains("유산균"))
            .anyMatch(category -> category.getName().contains("건강음료"))
            .anyMatch(category -> category.getName().contains("건강환"))
            .anyMatch(category -> category.getName().contains("다이어트"))
            .anyMatch(category -> category.getName().contains("유아동"))
    ;
  }

  @Test
  @DisplayName("생활 부모카테고리와 자식 카테고리")
  void createLifeStyleCategories() {
    // given
    Category parent = categoryRepository.findCategoryByName("생활");
    Category child1 = new Category("주방");
    Category child2 = new Category("생활");
    Category child3 = new Category("취미");

    // when
    categoryService.saveChildCategory(parent.getId(), child1);
    categoryService.saveChildCategory(parent.getId(), child2);
    categoryService.saveChildCategory(parent.getId(), child3);

    final List<Category> children = categoryService.findById(parent.getId()).getChildren();

    // then
    assertThat(children).allMatch(category -> category.getParent().getName().equals("생활"));
    assertThat(children)
            .anyMatch(category -> category.getName().contains("주방"))
            .anyMatch(category -> category.getName().contains("생활"))
            .anyMatch(category -> category.getName().contains("취미"))
    ;
  }

  @Test
  @DisplayName("반려동물 부모카테고리와 자식 카테고리")
  void createPetCategories() {
    // given
    Category parent = categoryRepository.findCategoryByName("반려동물");
    Category child1 = new Category("강아지 주식");
    Category child2 = new Category("강아지 간식");
    Category child3 = new Category("고양이 주식");
    Category child4 = new Category("고양이 간식");
    Category child5 = new Category("반려동물 생활용품");
    Category child6 = new Category("배변/위생");
    Category child7 = new Category("반려동물 샘플");



    // when
    categoryService.saveChildCategory(parent.getId(), child1);
    categoryService.saveChildCategory(parent.getId(), child2);
    categoryService.saveChildCategory(parent.getId(), child3);
    categoryService.saveChildCategory(parent.getId(), child4);
    categoryService.saveChildCategory(parent.getId(), child5);
    categoryService.saveChildCategory(parent.getId(), child6);
    categoryService.saveChildCategory(parent.getId(), child7);

    final List<Category> children = categoryService.findById(parent.getId()).getChildren();

    // then
    assertThat(children).allMatch(category -> category.getParent().getName().equals("반려동물"));
    assertThat(children)
            .anyMatch(category -> category.getName().contains("강아지 주식"))
            .anyMatch(category -> category.getName().contains("강아지 간식"))
            .anyMatch(category -> category.getName().contains("고양이 주식"))
            .anyMatch(category -> category.getName().contains("고양이 간식"))
            .anyMatch(category -> category.getName().contains("반려동물 생활용품"))
            .anyMatch(category -> category.getName().contains("배변/위생"))
            .anyMatch(category -> category.getName().contains("반려동물 샘플"))
    ;
  }
}