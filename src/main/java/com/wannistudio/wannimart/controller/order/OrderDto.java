package com.wannistudio.wannimart.controller.order;

import com.wannistudio.wannimart.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@NoArgsConstructor
public class OrderDto {

  private Long orderId;

  private String ItemName;

  public OrderDto(Order source) {
    copyProperties(source, this);
  }
}
