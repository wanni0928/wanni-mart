package com.wannistudio.wannimart.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("G")
@Getter
public class Goods extends Item {
  private String size;
  private String material;
}

