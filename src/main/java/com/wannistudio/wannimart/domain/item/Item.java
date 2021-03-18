package com.wannistudio.wannimart.domain.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wannistudio.wannimart.controller.item.ItemRequest;
import com.wannistudio.wannimart.domain.connect.ItemCategory;
import com.wannistudio.wannimart.exception.NotEnoughStockException;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
@NoArgsConstructor
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

  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
  @JsonIgnore
  private Set<ItemCategory> categoryItems = new HashSet<>();

  public Item(Long id, String name, String summary, int price, int stockQuantity, String unit, String volume, String description, DeliveryType deliveryType, PackageType packageType) {
    this.id = id;
    this.name = name;
    this.summary = summary;
    this.price = price;
    this.stockQuantity = stockQuantity;
    this.unit = unit;
    this.volume = volume;
    this.description = description;
    this.deliveryType = deliveryType;
    this.packageType = packageType;
  }

  public static Item from(ItemRequest itemRequest) {
    switch (itemRequest.getCategoryName()) {
      case "식품" :
      case "정육" :
        return createFood(itemRequest);
      case "상품" :
        return createGoods(itemRequest);
      default:
        throw new IllegalStateException("Unexpected value: " + itemRequest.getCategoryName());
    }
  }

  private static Food createFood(ItemRequest itemRequest) {
    return Food.builder()
            .name(itemRequest.getItemName())
            .summary(itemRequest.getSummary())
            .price(itemRequest.getPrice())
            .stockQuantity(itemRequest.getStockQuantity())
            .unit(itemRequest.getUnit())
            .volume(itemRequest.getVolume())
            .description(itemRequest.getDescription())
            .deliveryType(itemRequest.getDeliveryType())
            .packageType(itemRequest.getPackageType())
            .importFrom(itemRequest.getImportFrom())
            .allergyInformation(itemRequest.getAllergyInformation())
            .expiration(itemRequest.getExpiration())
            .build();
  }

  private static Goods createGoods(ItemRequest itemRequest) {
    return Goods.builder()
            .name(itemRequest.getItemName())
            .summary(itemRequest.getSummary())
            .price(itemRequest.getPrice())
            .stockQuantity(itemRequest.getStockQuantity())
            .unit(itemRequest.getUnit())
            .volume(itemRequest.getVolume())
            .description(itemRequest.getDescription())
            .deliveryType(itemRequest.getDeliveryType())
            .packageType(itemRequest.getPackageType())
            .size(itemRequest.getSize())
            .material(itemRequest.getMaterial())
            .build();
  }

  public void addItemCategory(ItemCategory categoryItem) {
    categoryItems.add(categoryItem);
    categoryItem.setItem(this);
  }

  public void removeStock(int quantity) {
    int restStock = this.stockQuantity - quantity;
    if(restStock < 0) {
      throw new NotEnoughStockException("need more stock");
    }
    this.stockQuantity = restStock;
  }

  public void addStock(int quantity) {
    this.stockQuantity += quantity;
  };
}
