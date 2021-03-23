package com.wannistudio.wannimart.repository.order;

import com.wannistudio.wannimart.controller.order.OrderItemQueryDto;
import com.wannistudio.wannimart.controller.order.OrderQueryDto;
import com.wannistudio.wannimart.domain.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderRepositoryCustomQuery {
  List<Order> findAll(OrderSearch orderSearch);
  List<OrderQueryDto> findOrderQueryDtos(OrderSearch orderSearch);
  Page<OrderItemQueryDto> findOrderItemQueryDtos(OrderSearch orderSearch, Pageable pageable);
}
