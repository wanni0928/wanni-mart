package com.wannistudio.wannimart.controller.order;

import com.wannistudio.wannimart.domain.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@NoArgsConstructor
public class OrderDto {
  private Long orderId;
  private String memberName;

  public OrderDto(Order source) {
    this.orderId = source.getId();
    this.memberName = source.getMember().getName();
  }
}
