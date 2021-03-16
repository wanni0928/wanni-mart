package com.wannistudio.wannimart.domain.connect;

import com.querydsl.core.annotations.QueryProjection;
import com.wannistudio.wannimart.domain.item.DeliveryType;
import com.wannistudio.wannimart.domain.item.PackageType;

public class ItemCategoryQueryDto {
  private Long id;
  private String itemName;
  private DeliveryType deliveryType;
  private PackageType packageType;
  private int price;
  private int stockQuantity;
  private String summary;
  private String unit;
  private String volume;
  private String allergyInformation;
  private String expiration;
  private String importFrom;
  private String africa;
  private String material;
  private String categoryName;

  @QueryProjection
  public ItemCategoryQueryDto(Long id, String itemName, DeliveryType deliveryType, PackageType packageType, int price, int stockQuantity, String summary, String unit, String volume, String allergyInformation, String expiration, String importFrom, String africa, String material, String categoryName) {
    this.id = id;
    this.itemName = itemName;
    this.deliveryType = deliveryType;
    this.packageType = packageType;
    this.price = price;
    this.stockQuantity = stockQuantity;
    this.summary = summary;
    this.unit = unit;
    this.volume = volume;
    this.allergyInformation = allergyInformation;
    this.expiration = expiration;
    this.importFrom = importFrom;
    this.africa = africa;
    this.material = material;
    this.categoryName = categoryName;
  }
}
