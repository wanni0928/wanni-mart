package com.wannistudio.wannimart.service;

import com.wannistudio.wannimart.controller.order.OrderItemQueryDto;
import com.wannistudio.wannimart.controller.order.OrderQueryDto;
import com.wannistudio.wannimart.domain.order.Order;
import com.wannistudio.wannimart.domain.connect.OrderItem;
import com.wannistudio.wannimart.domain.delivery.Delivery;
import com.wannistudio.wannimart.domain.item.Item;
import com.wannistudio.wannimart.domain.member.Member;
import com.wannistudio.wannimart.repository.item.ItemRepository;
import com.wannistudio.wannimart.repository.member.MemberRepository;
import com.wannistudio.wannimart.repository.order.OrderRepository;
import com.wannistudio.wannimart.repository.order.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final MemberRepository memberRepository;
  private final ItemRepository itemRepository;

  public List<OrderQueryDto> findAllOrderQueryDto(OrderSearch orderSearch) {
    return orderRepository.findOrderQueryDtos(orderSearch);
  }

  public Page<OrderItemQueryDto> findAllOrderItemQueryDto(OrderSearch orderSearch, Pageable pageable) {
    return orderRepository.findOrderItemQueryDtos(orderSearch, pageable);
  }

  //주문
  @Transactional
  public Order order(Long memberId, Long itemId, int count) {

    // 엔티티 조회
    Member member = memberRepository.findById(memberId).orElseThrow(NullPointerException::new);
    Item item = itemRepository.findById(itemId).orElseThrow(NullPointerException::new);

    // 배송
    Delivery delivery = new Delivery();
    delivery.setAddress(member.getAddress());

    // 주문상품 생성
    OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

    // 주문 생성
    Order order = Order.createOrder(member, delivery, orderItem);

    // 주문 저장
    orderRepository.save(order);

    return order;
  }

  //취소
  @Transactional
  public Order cancelOrder(Long orderId) {
    // 주문 엔티티 조회
    Order order = orderRepository.findById(orderId).orElseThrow(NullPointerException::new);
    // 주문 취소
    order.cancel();
    return order;
  }

  //주문 검색
  public List<Order> findAll(OrderSearch orderSearch){
    return orderRepository.findAll(orderSearch);
  }
}
