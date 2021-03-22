package com.wannistudio.wannimart.controller.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wannistudio.wannimart.domain.connect.OrderItem;
import com.wannistudio.wannimart.domain.item.Item;
import com.wannistudio.wannimart.domain.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemDto {
  private Long id;
  private Item item;
  private int orderPrice; // 주문 가격
  private int count; // 주문 수량

  public OrderItemDto(OrderItem source) {
    copyProperties(source, this);
  }
}
