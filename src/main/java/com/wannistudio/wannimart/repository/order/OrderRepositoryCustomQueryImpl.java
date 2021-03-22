package com.wannistudio.wannimart.repository.order;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wannistudio.wannimart.controller.order.OrderItemQueryDto;
import com.wannistudio.wannimart.controller.order.OrderQueryDto;
import com.wannistudio.wannimart.controller.order.QOrderItemQueryDto;
import com.wannistudio.wannimart.controller.order.QOrderQueryDto;
import com.wannistudio.wannimart.domain.connect.QOrderItem;
import com.wannistudio.wannimart.domain.delivery.QDelivery;
import com.wannistudio.wannimart.domain.item.QItem;
import com.wannistudio.wannimart.domain.member.QMember;
import com.wannistudio.wannimart.domain.order.Order;
import com.wannistudio.wannimart.domain.order.OrderStatus;
import com.wannistudio.wannimart.domain.order.QOrder;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.wannistudio.wannimart.domain.connect.QOrderItem.*;
import static com.wannistudio.wannimart.domain.delivery.QDelivery.*;
import static com.wannistudio.wannimart.domain.item.QItem.*;
import static com.wannistudio.wannimart.domain.member.QMember.*;
import static com.wannistudio.wannimart.domain.order.QOrder.*;
import static java.util.stream.Collectors.*;

public class OrderRepositoryCustomQueryImpl implements OrderRepositoryCustomQuery {

  private final JPAQueryFactory queryFactory;

  public OrderRepositoryCustomQueryImpl(EntityManager entityManager) {
    this.queryFactory = new JPAQueryFactory(entityManager);
  }

  @Override
  public List<Order> findAll(OrderSearch orderSearch) {
    return queryFactory.select(order)
            .from(order)
            .join(order.member, member)
            .where(statusEq(orderSearch.getOrderStatus()), nameLike(orderSearch.getMemberName()))
            .fetch()
            ;
  }

  @Override
  public List<OrderQueryDto> findOrderQueryDtos(OrderSearch orderSearch) {

    List<OrderQueryDto> result = findOrders(orderSearch);

    List<Long> orderIds = result.stream().map(
            OrderQueryDto::getId
    ).collect(toList());

    List<OrderItemQueryDto> orderItems = findOrderItems(orderIds);

    Map<Long, List<OrderItemQueryDto>> orderItemMap = orderItems
            .stream()
            .collect(
                    groupingBy(OrderItemQueryDto::getOrderId)
            );

    result.forEach(orderQueryDto -> orderQueryDto.setOrderItems(orderItemMap.get(orderQueryDto.getId())));

    return result;
  }

  private List<OrderItemQueryDto> findOrderItems(List<Long> orderIds) {
    return queryFactory.select(
            new QOrderItemQueryDto(orderItem.order.id, item.name, orderItem.orderPrice, orderItem.count)
    ).from(orderItem)
            .join(orderItem.item, item)
            .where(orderItem.order.id.in(orderIds))
            .fetch();
  }

  private List<OrderQueryDto> findOrders(OrderSearch orderSearch) {
    return queryFactory.select(
            new QOrderQueryDto(order.id, member.name, order.orderDate, order.status, delivery.address)
    ).from(order)
            .where(pkLike(orderSearch.getOrderId()))
            .join(order.member, member)
            .join(order.delivery, delivery)
            .fetch();
  }

  private Predicate pkLike(Long orderId) {
    if(orderId == null) return null;
    return order.id.eq(orderId);
  }

  private BooleanExpression nameLike(String nameCond) {
    if (!StringUtils.hasText(nameCond)) {
      return null;
    }
    return member.name.like(nameCond);
  }

  private BooleanExpression statusEq(OrderStatus statusCond) {
    if (statusCond == null) {
      return null;
    }
    return order.status.eq(statusCond);
  }
}
