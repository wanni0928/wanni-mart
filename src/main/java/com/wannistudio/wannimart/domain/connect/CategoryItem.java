package com.wannistudio.wannimart.domain.connect;

import com.wannistudio.wannimart.domain.category.Category;
import com.wannistudio.wannimart.domain.item.Item;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CategoryItem {
  @Id
  @GeneratedValue
  @Column(name = "category_item_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  private Item item;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;
}
