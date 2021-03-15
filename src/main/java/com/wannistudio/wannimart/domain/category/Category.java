package com.wannistudio.wannimart.domain.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wannistudio.wannimart.domain.connect.ItemCategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Category {
  @Id
  @GeneratedValue
  @Column(name = "category_id")
  private Long id;

  private String name;

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
  @JsonIgnore
  private Set<ItemCategory> categoryItems = new HashSet<>();

  /*셀프 연관관계*/
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private Category parent;

  @OneToMany(mappedBy = "parent")
  @JsonIgnore
  private List<Category> children = new ArrayList<>();

  /*연관관계 메소드드*/
  public void addChildCategory(Category child) {
    this.children.add(child);
    child.setParent(this);
  }

  public void addItemCategory(ItemCategory itemCategory) {
    this.getCategoryItems().add(itemCategory);
    itemCategory.setCategory(this);
  }
}
