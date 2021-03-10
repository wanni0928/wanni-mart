package com.wannistudio.wannimart.domain.category;

import com.wannistudio.wannimart.domain.connect.CategoryItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {
  @Id
  @GeneratedValue
  @Column(name = "category_id")
  private Long id;

  private String name;

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
  private List<CategoryItem> categoryItems = new ArrayList<>();

  /*셀프 연관관계*/
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private Category parent;

  @OneToMany(mappedBy = "parent")
  private List<Category> child = new ArrayList<>();
}
