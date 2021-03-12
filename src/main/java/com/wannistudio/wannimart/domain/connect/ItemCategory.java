package com.wannistudio.wannimart.domain.connect;

import com.wannistudio.wannimart.domain.category.Category;
import com.wannistudio.wannimart.domain.item.Item;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ItemCategory {
  @Id
  @GeneratedValue
  @Column(name = "item_category_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  private Item item;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;

  private String description;

  public static ItemCategory createItemCategory(Category category, String desc) {
    ItemCategory categoryItem = new ItemCategory();
    categoryItem.setCategory(category);
//    categoryItem.setDesc(desc);
    return categoryItem;
  }
}
