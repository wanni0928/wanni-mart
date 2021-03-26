package com.wannistudio.wannimart.domain.connect;

import com.wannistudio.wannimart.domain.category.Category;
import com.wannistudio.wannimart.domain.item.Item;
import lombok.*;

import javax.persistence.*;

import static com.google.common.base.Preconditions.checkNotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ItemCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "item_category_id", nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  private Item item;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;

  public static ItemCategory createItemCategory(Category category) {
    checkNotNull(category, "category must not be null");

    ItemCategory categoryItem = new ItemCategory();
    categoryItem.setCategory(category);
    return categoryItem;
  }
}
