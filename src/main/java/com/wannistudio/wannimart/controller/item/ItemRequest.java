package com.wannistudio.wannimart.controller.item;

import com.wannistudio.wannimart.domain.common.Id;
import com.wannistudio.wannimart.domain.item.DeliveryType;
import com.wannistudio.wannimart.domain.item.Food;
import com.wannistudio.wannimart.domain.item.Item;
import com.wannistudio.wannimart.domain.item.PackageType;
import com.wannistudio.wannimart.domain.member.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemRequest {

  @ApiModelProperty(value = "상품 이름", required = true, example = "당근")
  private String itemName;

  @ApiModelProperty(value = "상품 대카테고리", required = true, example = "채소")
  private String parentCategoryName;

  @ApiModelProperty(value = "상품 세부 카테고리", required = true, example = "고구마/감자/당근")
  private String categoryName;

  @ApiModelProperty(value = "상품 간단설명", required = true, example = "기본적으로 식용이지만, 무언가 신호를 보낼 때, 종종쓰는 채소")
  private String summary;

  @ApiModelProperty(value = "가격", required = true, example = "9999")
  private int price;

  @ApiModelProperty(value = "상품 재고수량", required = true, example = "2314")
  private int stockQuantity;

  @ApiModelProperty(value = "상품 단위", required = true, example = "kg")
  private String unit;

  @ApiModelProperty(value = "상품 용량", required = true, example = "6kg(개당 평균 120g)")
  private String volume;

  @ApiModelProperty(value = "상품 세부설명", required = true, example = "유기농 농산물이라, 가끔 먹다가 벌레가 씹힐 수 있습니다. 벌레도 무해하니, 꼭꼭 씹어 드시기 바랍니다.")
  private String description;

  @ApiModelProperty(value = "배송타입", required = true, example = "EARLY")
  private DeliveryType deliveryType;

  @ApiModelProperty(value = "포장타입", required = true, example = "ECO_PACKAGE")
  private PackageType packageType;

  // food
  @ApiModelProperty(value = "원산지", required = true, example = "국내산")
  private String importFrom;

  @ApiModelProperty(value = "알레르기 정보", required = true, example = "당근 함유")
  private String allergyInformation;

  @ApiModelProperty(value = "유통기한", required = true, example = "구매일로 부터 약 한달(자세한 날짜는 포장지에 표기)")
  private String expiration;

  // goods
  @ApiModelProperty(value = "상품크기", required = true, example = "250 x 10(mm)")
  private String size;

  @ApiModelProperty(value = "재질", required = true, example = "종이(포장재질)")
  private String material;
}
