package com.wannistudio.wannimart.domain.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wannistudio.wannimart.domain.connect.ItemCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id", nullable = false)
  private Long id;

  @Column(nullable = false)
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

  public Category(String name) {
    checkNotNull(name, "name must be provided");
    this.name = name;
  }

  public void setParent(Category parent) {
    this.parent = parent;
  }

  /*연관관계 메소드드*/
  public void addChildCategory(Category child) {
    this.children.add(child);
    child.setParent(this);
  }

  public void addItemCategory(ItemCategory itemCategory) {
    this.getCategoryItems().add(itemCategory);
    itemCategory.setCategory(this);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id", id)
            .append("name", name)
            .toString();
  }
}
