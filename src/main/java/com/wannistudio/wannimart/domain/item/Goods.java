package com.wannistudio.wannimart.domain.item;

import com.wannistudio.wannimart.domain.connect.ItemCategory;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Set;

@Entity
@DiscriminatorValue("G")
@Getter
@Setter
@NoArgsConstructor
public class Goods extends Item {
  private String size;
  private String material;

  @Builder
  public Goods(Long id, String name, String summary, int price, int stockQuantity, String unit, String volume, String description, DeliveryType deliveryType, PackageType packageType, String size, String material) {
    super(id, name, summary, price, stockQuantity, unit, volume, description, deliveryType, packageType);
    this.size = size;
    this.material = material;
  }
}

