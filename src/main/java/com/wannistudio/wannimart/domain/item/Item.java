package com.wannistudio.wannimart.domain.item;

import com.wannistudio.wannimart.domain.connect.ItemCategory;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public abstract class Item {
  @Id
  @GeneratedValue
  @Column(name = "item_id")
  private Long id;

  private String name;

  private String summary;

  private int price;

  private int stockQuantity;

  private String unit;

  private String volume;

  private String description;

  @Enumerated(EnumType.STRING)
  private DeliveryType deliveryType;

  @Enumerated(EnumType.STRING)
  private PackageType packageType;

  @OneToMany(mappedBy = "item")
  private Set<ItemCategory> categoryItems = new HashSet<>();

  public void addItemCategory(ItemCategory categoryItem) {
    categoryItems.add(categoryItem);
    categoryItem.setItem(this);
  }

  public void removeStock(int count) {

  }
}
