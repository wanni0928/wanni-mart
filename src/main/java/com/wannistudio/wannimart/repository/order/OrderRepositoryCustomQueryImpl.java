package com.wannistudio.wannimart.repository.order;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wannistudio.wannimart.domain.member.QMember;
import com.wannistudio.wannimart.domain.order.Order;
import com.wannistudio.wannimart.domain.order.OrderStatus;
import com.wannistudio.wannimart.domain.order.QOrder;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.wannistudio.wannimart.domain.member.QMember.*;
import static com.wannistudio.wannimart.domain.order.QOrder.*;

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

  private BooleanExpression nameLike(String nameCond) {
    if (!StringUtils.hasText(nameCond)) {
      return null;
    }
    return member.name.like(nameCond);
  }

  private BooleanExpression statusEq(OrderStatus statusCond) {
    if(statusCond == null) {
      return null;
    }
    return order.status.eq(statusCond);
  }
}
