package com.wannistudio.wannimart.controller.order;

import com.wannistudio.wannimart.domain.order.Order;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@NoArgsConstructor
public class OrderDto {

  @ApiModelProperty(value = "주문 번호")
  private Long orderId;

  @ApiModelProperty(value = "주문자 이름")
  private String memberName;

  @ApiModelProperty(value = "주문상품 정보")
  private List<OrderItemDto> orderItemDtos;

  @ApiModelProperty(value = "총 가격")
  private int totalPrice;

  public OrderDto(Order source) {
    this.orderId = source.getId();
    this.memberName = source.getMember().getName();
    this.orderItemDtos = source.getOrderItems()
            .stream()
            .map(OrderItemDto::new)
            .collect(toList());
    this.totalPrice = source.getTotalPrice();
  }
}