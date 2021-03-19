package com.wannistudio.wannimart.controller.order;

import com.querydsl.core.annotations.QueryProjection;
import com.wannistudio.wannimart.domain.member.Address;
import com.wannistudio.wannimart.domain.order.OrderStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class OrderQueryDto {
  private Long id;
  private String name;
  private LocalDateTime orderDate;
  private OrderStatus orderStatus;
  private Address address;
  private List<OrderItemQueryDto> orderItems;

  @QueryProjection
  public OrderQueryDto(Long id, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
    this.id = id;
    this.name = name;
    this.orderDate = orderDate;
    this.orderStatus = orderStatus;
    this.address = address;
  }

  @QueryProjection
  public OrderQueryDto(Long id, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address, List<OrderItemQueryDto> orderItems) {
    this.id = id;
    this.name = name;
    this.orderDate = orderDate;
    this.orderStatus = orderStatus;
    this.address = address;
    this.orderItems = orderItems;
  }
}
