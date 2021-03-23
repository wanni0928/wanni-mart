package com.wannistudio.wannimart.controller.order;

import com.querydsl.core.annotations.QueryProjection;
import com.wannistudio.wannimart.domain.member.Address;
import com.wannistudio.wannimart.domain.order.OrderStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class OrderQueryDto {

  @ApiModelProperty(value = "주문번호", required = true)
  private Long id;

  @ApiModelProperty(value = "주문자 이름", required = true)
  private String name;

  @ApiModelProperty(value = "주문날짜", required = true)
  private LocalDateTime orderDate;

  @ApiModelProperty(value = "주문 상태", required = true)
  private OrderStatus orderStatus;

  @ApiModelProperty(value = "배송지", required = true)
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
