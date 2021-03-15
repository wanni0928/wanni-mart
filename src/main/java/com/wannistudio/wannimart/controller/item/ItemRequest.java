package com.wannistudio.wannimart.controller.item;

import com.wannistudio.wannimart.domain.common.Id;
import com.wannistudio.wannimart.domain.item.DeliveryType;
import com.wannistudio.wannimart.domain.item.Food;
import com.wannistudio.wannimart.domain.item.Item;
import com.wannistudio.wannimart.domain.item.PackageType;
import com.wannistudio.wannimart.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemRequest {
  private Long id;

  private String itemName;

  private String categoryName;

  private String summary;

  private int price;

  private int stockQuantity;

  private String unit;

  private String volume;

  private String description;

  private DeliveryType deliveryType;

  private PackageType packageType;

  // food
  private String importFrom;

  private String allergyInformation;

  private String expiration;

  // goods
  private String size;

  private String material;

  @Override
  public String toString() {
    return "ItemRequest{" +
            "id=" + id +
            ", itemName='" + itemName + '\'' +
            ", categoryName='" + categoryName + '\'' +
            ", summary='" + summary + '\'' +
            ", price=" + price +
            ", stockQuantity=" + stockQuantity +
            ", unit='" + unit + '\'' +
            ", volume='" + volume + '\'' +
            ", description='" + description + '\'' +
            ", deliveryType=" + deliveryType +
            ", packageType=" + packageType +
            ", importFrom='" + importFrom + '\'' +
            ", allergyInformation='" + allergyInformation + '\'' +
            ", expiration='" + expiration + '\'' +
            ", size='" + size + '\'' +
            ", material='" + material + '\'' +
            '}';
  }
}
