package com.wannistudio.wannimart.domain.item;

import com.wannistudio.wannimart.domain.connect.CategoryItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
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
  private List<CategoryItem> categoryItems = new ArrayList<>();

  public void removeStock(int count) {

  }
}

