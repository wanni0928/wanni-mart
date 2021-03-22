package com.wannistudio.wannimart;

import com.wannistudio.wannimart.controller.member.JoinRequest;
import com.wannistudio.wannimart.domain.category.Category;
import com.wannistudio.wannimart.domain.member.Gender;
import com.wannistudio.wannimart.domain.member.Member;
import com.wannistudio.wannimart.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Profile(value = "local")
@Component
@AllArgsConstructor
public class InitDb {
  private final InitService initService;

  @PostConstruct
  public void init() {
    initService.establishSampleMember();
    initService.establishCategory();
  }

  @Component
  @Transactional
  @RequiredArgsConstructor
  static class InitService {
    private final EntityManager em;
    private final PasswordEncoder passwordEncoder;
    private final CategoryService categoryService;

    public void establishSampleMember() {
      Member member1 = Member.of(passwordEncoder, JoinRequest.builder()
              .principal("wanni123")
              .credential("12345")
              .name("와니")
              .email("wanni@gmail.com")
              .phoneNumber("010-1111-2222")
              .cityStreetAddress("서울")
              .residentAddress("어딘가")
              .gender(Gender.MEN.name())
              .year(2016)
              .month(4)
              .day(13)
              .termsOfUse(true)
              .personalInfoUsage(true)
              .marketingUsage(true)
              .isAdult(true)
              .build()
      );
      em.persist(member1);

      Member member2 = Member.of(passwordEncoder, JoinRequest.builder()
              .principal("curry123")
              .credential("12345")
              .name("커리")
              .email("curry@gmail.com")
              .phoneNumber("010-1111-2222")
              .cityStreetAddress("서울")
              .residentAddress("어딘가")
              .gender(Gender.MEN.name())
              .year(2016)
              .month(8)
              .day(13)
              .termsOfUse(true)
              .personalInfoUsage(true)
              .marketingUsage(true)
              .isAdult(false)
              .build()
      );
      em.persist(member2);
    }

    public void establishCategory() {
      // set parent category
      final Category vegetable = categoryService.saveParentCategory(new Category("채소"));
      final Category fruit = categoryService.saveParentCategory(new Category("과일"));
      final Category agricultural = categoryService.saveParentCategory(new Category("축/농산물/수산물"));
      final Category simpleMeal = categoryService.saveParentCategory(new Category("간편식"));
      final Category spicy = categoryService.saveParentCategory(new Category("양념"));
      final Category beverage = categoryService.saveParentCategory(new Category("음료"));
      final Category candy = categoryService.saveParentCategory(new Category("과자"));
      final Category bakery = categoryService.saveParentCategory(new Category("베이커리"));
      final Category health = categoryService.saveParentCategory(new Category("헬스"));
      final Category lifeStyle = categoryService.saveParentCategory(new Category("생활"));
      final Category electronics = categoryService.saveParentCategory(new Category("전자"));
      final Category pet = categoryService.saveParentCategory(new Category("반려동물"));

      // 채소
      categoryService.saveChildCategory(vegetable.getId(), new Category("고구마/감자/당근"));
      categoryService.saveChildCategory(vegetable.getId(), new Category("오신채"));
      categoryService.saveChildCategory(vegetable.getId(), new Category("푸른채소"));
      categoryService.saveChildCategory(vegetable.getId(), new Category("냉동, 간편채소"));
      categoryService.saveChildCategory(vegetable.getId(), new Category("버섯"));

      // 과일
      categoryService.saveChildCategory(fruit.getId(), new Category("국산과일"));
      categoryService.saveChildCategory(fruit.getId(), new Category("수입과일"));
      categoryService.saveChildCategory(fruit.getId(), new Category("간편과일"));
      categoryService.saveChildCategory(fruit.getId(), new Category("냉동/건과일"));
      categoryService.saveChildCategory(fruit.getId(), new Category("견과류"));

      // 농축수산물
      categoryService.saveChildCategory(agricultural.getId(), new Category("쌀/잡곡"));
      categoryService.saveChildCategory(agricultural.getId(), new Category("생선류"));
      categoryService.saveChildCategory(agricultural.getId(), new Category("조개류"));
      categoryService.saveChildCategory(agricultural.getId(), new Category("수산가공품"));
      categoryService.saveChildCategory(agricultural.getId(), new Category("해조류"));
      categoryService.saveChildCategory(agricultural.getId(), new Category("국내산 고기"));
      categoryService.saveChildCategory(agricultural.getId(), new Category("해외산 고기"));
      categoryService.saveChildCategory(agricultural.getId(), new Category("계란류"));
      categoryService.saveChildCategory(agricultural.getId(), new Category("양념육"));

      // 간편식
      categoryService.saveChildCategory(simpleMeal.getId(), new Category("샐러드"));
      categoryService.saveChildCategory(simpleMeal.getId(), new Category("도시락"));
      categoryService.saveChildCategory(simpleMeal.getId(), new Category("면류"));
      categoryService.saveChildCategory(simpleMeal.getId(), new Category("분식"));
      categoryService.saveChildCategory(simpleMeal.getId(), new Category("안주"));
      categoryService.saveChildCategory(simpleMeal.getId(), new Category("선식/시리얼"));

      // 양념
      categoryService.saveChildCategory(spicy.getId(), new Category("가루 양념"));
      categoryService.saveChildCategory(spicy.getId(), new Category("액체 양념"));
      categoryService.saveChildCategory(spicy.getId(), new Category("기름 양념"));
      categoryService.saveChildCategory(spicy.getId(), new Category("양념 세트"));

      // 음료
      categoryService.saveChildCategory(beverage.getId(), new Category("생수"));
      categoryService.saveChildCategory(beverage.getId(), new Category("탄산"));
      categoryService.saveChildCategory(beverage.getId(), new Category("유제품"));
      categoryService.saveChildCategory(beverage.getId(), new Category("커피"));
      categoryService.saveChildCategory(beverage.getId(), new Category("차"));

      // 과자
      categoryService.saveChildCategory(candy.getId(), new Category("초콜릿/캔디류"));
      categoryService.saveChildCategory(candy.getId(), new Category("떡/한과"));
      categoryService.saveChildCategory(candy.getId(), new Category("아이스크림"));

      // 베이커리
      categoryService.saveChildCategory(bakery.getId(), new Category("빵류"));
      categoryService.saveChildCategory(bakery.getId(), new Category("잼/버터"));
      categoryService.saveChildCategory(bakery.getId(), new Category("케이크"));
      categoryService.saveChildCategory(bakery.getId(), new Category("치즈"));

      // 헬스
      categoryService.saveChildCategory(health.getId(), new Category("영양제"));
      categoryService.saveChildCategory(health.getId(), new Category("유산균"));
      categoryService.saveChildCategory(health.getId(), new Category("건강음료"));
      categoryService.saveChildCategory(health.getId(), new Category("건강환"));
      categoryService.saveChildCategory(health.getId(), new Category("다이어트"));
      categoryService.saveChildCategory(health.getId(), new Category("유아동"));

      // 생활
      categoryService.saveChildCategory(lifeStyle.getId(), new Category("휴지"));
      categoryService.saveChildCategory(lifeStyle.getId(), new Category("여성 위생용품"));
      categoryService.saveChildCategory(lifeStyle.getId(), new Category("청소"));
      categoryService.saveChildCategory(lifeStyle.getId(), new Category("조리도구"));
      categoryService.saveChildCategory(lifeStyle.getId(), new Category("문구"));
      categoryService.saveChildCategory(lifeStyle.getId(), new Category("의약품/마스크"));
      categoryService.saveChildCategory(lifeStyle.getId(), new Category("미용"));
      categoryService.saveChildCategory(lifeStyle.getId(), new Category("식기"));
      categoryService.saveChildCategory(lifeStyle.getId(), new Category("커피도구"));

      // 전자
      categoryService.saveChildCategory(electronics.getId(), new Category("주방"));
      categoryService.saveChildCategory(electronics.getId(), new Category("생활"));
      categoryService.saveChildCategory(electronics.getId(), new Category("취미"));

      // 반려동물
      categoryService.saveChildCategory(pet.getId(), new Category("강아지 주식"));
      categoryService.saveChildCategory(pet.getId(), new Category("강아지 간식"));
      categoryService.saveChildCategory(pet.getId(), new Category("고양이 주식"));
      categoryService.saveChildCategory(pet.getId(), new Category("고양이 간식"));
      categoryService.saveChildCategory(pet.getId(), new Category("반려동물 생활용품"));
      categoryService.saveChildCategory(pet.getId(), new Category("배변/위생"));
      categoryService.saveChildCategory(pet.getId(), new Category("반려동물 샘플"));

    }
  }
}