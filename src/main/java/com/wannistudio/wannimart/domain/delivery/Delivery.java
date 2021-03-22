package com.wannistudio.wannimart.domain.delivery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wannistudio.wannimart.domain.order.Order;
import com.wannistudio.wannimart.domain.member.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "delivery_id", nullable = false)
  private Long id;

  @JsonIgnore
  @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
  private Order order;

  @Embedded
  private Address address;

  @Enumerated(EnumType.STRING)
  private DeliveryStatus status; // READY, COMP
}
