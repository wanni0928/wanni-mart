package com.wannistudio.wannimart.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("F")
@Getter
public class Food extends Item {
  private String importFrom;
  private String allergyInformation;
  private String expiration;
}
