package com.wannistudio.wannimart.controller.item;

import com.wannistudio.wannimart.domain.connect.ItemCategory;
import com.wannistudio.wannimart.domain.item.DeliveryType;
import com.wannistudio.wannimart.domain.item.PackageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {
  private Long id;

  private String name;

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
}
