package com.wannistudio.wannimart.domain.item;

import com.wannistudio.wannimart.domain.connect.ItemCategory;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Set;

@Entity
@DiscriminatorValue("F")
@Getter
@Setter
@NoArgsConstructor
public class Food extends Item {

  private String importFrom;

  private String allergyInformation;

  private String expiration;

  @Builder
  public Food(Long id, String name, String summary, int price, int stockQuantity, String unit, String volume, String description, DeliveryType deliveryType, PackageType packageType, String importFrom, String allergyInformation, String expiration) {
    super(id, name, summary, price, stockQuantity, unit, volume, description, deliveryType, packageType);
    this.importFrom = importFrom;
    this.allergyInformation = allergyInformation;
    this.expiration = expiration;
  }
}
