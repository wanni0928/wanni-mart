package com.wannistudio.wannimart.domain.item;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("F")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Food extends Item {
  private String importFrom;
  private String allergyInformation;
  private String expiration;
}
