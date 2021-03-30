package com.wannistudio.wannimart.service;

import com.wannistudio.wannimart.controller.member.JoinRequest;
import com.wannistudio.wannimart.domain.connect.OrderItem;
import com.wannistudio.wannimart.domain.delivery.Delivery;
import com.wannistudio.wannimart.domain.item.Item;
import com.wannistudio.wannimart.domain.member.Gender;
import com.wannistudio.wannimart.domain.member.Member;
import com.wannistudio.wannimart.domain.order.Order;
import com.wannistudio.wannimart.domain.order.OrderStatus;
import com.wannistudio.wannimart.repository.item.ItemRepository;
import com.wannistudio.wannimart.repository.member.MemberRepository;
import com.wannistudio.wannimart.repository.order.OrderRepository;
import com.wannistudio.wannimart.repository.order.OrderSearch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private OrderService orderService;
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private MemberService memberService;
  @Autowired
  private ItemRepository itemRepository;
//
//  @Test
//  void createOrderTest() {
//    // given
//    Member member = memberRepository.findById(5L).orElseThrow(NullPointerException::new);
//    Item item = itemRepository.findById(5L).orElseThrow(NullPointerException::new);
//    Delivery delivery = new Delivery();
//    delivery.setAddress(member.getAddress());
//    final int beforeOrderStockQuantity = item.getStockQuantity();
//
//    // when
//    int orderCnt = 1;
//    OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), orderCnt);
//    Order order = Order.createOrder(member, delivery, orderItem);
//    final Order newOrder = orderRepository.save(order);
//
//    // then
//    assertThat(newOrder.getMember()).isEqualTo(member);
//    assertThat(item.getStockQuantity()).isEqualTo(beforeOrderStockQuantity - orderCnt);
//    assertThat(newOrder.getOrderItems()).contains(orderItem);
//    assertThat(orderItem.getItem()).isEqualTo(item);
//    assertThat(orderItem.getOrder()).isEqualTo(order);
//    assertThat(orderItem.getCount()).isEqualTo(orderCnt);
//    assertThat(orderItem.getOrderPrice()).isEqualTo(item.getPrice() * orderCnt);
//    assertThat(orderItem.getTotalPrice()).isEqualTo(orderItem.getOrderPrice() * orderItem.getCount());
//  }
//
//  @Test
//  void createOrderAndCancelOrder() {
//    // given
//    Member member = memberRepository.findById(5L).orElseThrow(NullPointerException::new);
//    Item item = itemRepository.findById(5L).orElseThrow(NullPointerException::new);
//    Delivery delivery = new Delivery();
//    delivery.setAddress(member.getAddress());
//    final int beforeOrderStockQuantity = item.getStockQuantity();
//
//    // when
//    int orderCnt = 1;
//    OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), orderCnt);
//    Order order = Order.createOrder(member, delivery, orderItem);
//    final Order newOrder = orderRepository.save(order);
//    orderService.cancelOrder(newOrder.getId());
//
//    // then
//    assertThat(item.getStockQuantity()).isEqualTo(beforeOrderStockQuantity);
//  }
//
//  @Test
//  void findAllOrders() {
//    // given
//    final Member testMember0 = memberService.join(createTestMemberJoinRequest("testMember0"));
//    final Member testMember1 = memberService.join(createTestMemberJoinRequest("testMember1"));
//    final Member testMember2 = memberService.join(createTestMemberJoinRequest("testMember2"));
//    final Member testMember3 = memberService.join(createTestMemberJoinRequest("testMember3"));
//
//
//    Item item1 = itemRepository.findById(5L).orElseThrow(NullPointerException::new);
//    Item item2 = itemRepository.findById(24L).orElseThrow(NullPointerException::new);
//    Item item3 = itemRepository.findById(7L).orElseThrow(NullPointerException::new);
//    Item item4 = itemRepository.findById(8L).orElseThrow(NullPointerException::new);
//
//    Delivery delivery1 = new Delivery();
//    delivery1.setAddress(testMember0.getAddress());
//
//    Delivery delivery2 = new Delivery();
//    delivery2.setAddress(testMember1.getAddress());
//
//    Delivery delivery3 = new Delivery();
//    delivery3.setAddress(testMember2.getAddress());
//
//    Delivery delivery4 = new Delivery();
//    delivery4.setAddress(testMember3.getAddress());
//
//    final int beforeOrder1StockQuantity = item1.getStockQuantity();
//    final int beforeOrder2StockQuantity = item2.getStockQuantity();
//    final int beforeOrder3StockQuantity = item3.getStockQuantity();
//    final int beforeOrder4StockQuantity = item4.getStockQuantity();
//
//
//    // when
//    int orderCnt1 = 1;
//    OrderItem orderItem1 = OrderItem.createOrderItem(item1, item1.getPrice(), orderCnt1);
//    Order order1 = Order.createOrder(testMember0, delivery1, orderItem1);
//    orderRepository.save(order1);
//
//    int orderCnt2 = 2;
//    OrderItem orderItem2 = OrderItem.createOrderItem(item2, item2.getPrice(), orderCnt2);
//    Order order2 = Order.createOrder(testMember0, delivery1, orderItem2);
//    orderRepository.save(order2);
//
//    int orderCnt3 = 3;
//    OrderItem orderItem3 = OrderItem.createOrderItem(item3, item3.getPrice(), orderCnt3);
//    Order order3 = Order.createOrder(testMember0, delivery1, orderItem3);
//    orderRepository.save(order3);
//
//    int orderCnt4 = 4;
//    OrderItem orderItem4 = OrderItem.createOrderItem(item4, item4.getPrice(), orderCnt4);
//    Order order4 = Order.createOrder(testMember0, delivery1, orderItem4);
//    orderRepository.save(order4);
//    final List<Order> pureListQuery = orderService.findAll(new OrderSearch());
//
//    orderService.cancelOrder(order3.getId());
//
//
//    OrderSearch orderSearch = new OrderSearch();
//    orderSearch.setOrderStatus(OrderStatus.ORDER);
//    assertThat(orderService.findAll(orderSearch).size()).isEqualTo(pureListQuery.size() - 1);
//  }
//
//  private JoinRequest createTestMemberJoinRequest(String principal) {
//    return JoinRequest.builder()
//            .principal(principal)
//            .credential("12345")
//            .name("와니")
//            .email(principal + "@gmail.com")
//            .phoneNumber("010-1111-2222")
//            .cityStreetAddress("서울")
//            .residentAddress("어딘가")
//            .gender(Gender.MEN.name())
//            .year(2016)
//            .month(4)
//            .day(13)
//            .termsOfUse(true)
//            .personalInfoUsage(true)
//            .marketingUsage(true)
//            .isAdult(true)
//            .build();
//  }
}