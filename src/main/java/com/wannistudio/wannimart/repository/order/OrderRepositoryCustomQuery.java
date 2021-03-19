package com.wannistudio.wannimart.repository.order;

import com.wannistudio.wannimart.controller.order.OrderQueryDto;
import com.wannistudio.wannimart.domain.order.Order;

import java.util.List;

public interface OrderRepositoryCustomQuery {
  List<Order> findAll(OrderSearch orderSearch);
  List<OrderQueryDto> findOrderQueryDtos();
}
