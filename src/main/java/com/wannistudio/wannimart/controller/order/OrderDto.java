package com.wannistudio.wannimart.controller.order;

import com.wannistudio.wannimart.domain.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@NoArgsConstructor
public class OrderDto {
  private Long orderId;
  private String memberName;
  private List<OrderItemDto> orderItemDtos;

  public OrderDto(Order source) {
    this.orderId = source.getId();
    this.memberName = source.getMember().getName();
    this.orderItemDtos = source.getOrderItems()
            .stream()
            .map(OrderItemDto::new)
            .collect(toList());
  }
}