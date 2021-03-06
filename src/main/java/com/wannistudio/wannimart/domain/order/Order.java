package com.wannistudio.wannimart.domain.order;

import com.wannistudio.wannimart.domain.connect.OrderItem;
import com.wannistudio.wannimart.domain.delivery.Delivery;
import com.wannistudio.wannimart.domain.delivery.DeliveryStatus;
import com.wannistudio.wannimart.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderItem> orderItems = new ArrayList<>();

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "delivery_id")
  private Delivery delivery;


  private LocalDateTime orderDate; //주문시간

  @Enumerated(EnumType.STRING)
  private OrderStatus status; //주문상태 [ORDER, CANCEL]

  public void setMember(Member member) {
    this.member = member;
    member.getOrders().add(this);
  }

  public void addOrderItem(OrderItem orderItem) {
    orderItems.add(orderItem);
    orderItem.setOrder(this);
  }

  public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
    checkNotNull(member, "member must be provided");
    checkNotNull(delivery, "delivery must be provided");

    Order order = new Order();
    order.setMember(member);
    order.setDelivery(delivery);
    Arrays.stream(orderItems).forEach(order::addOrderItem);
    order.setStatus(OrderStatus.ORDER);
    order.setOrderDate(LocalDateTime.now());
    return order;
  }

  public void cancel() {
    checkArgument(delivery.getStatus() == DeliveryStatus.COMP, "delivery status must not be COMP");

    if(delivery.getStatus() == DeliveryStatus.COMP) {
      throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능 합니다.");
    }
    this.setStatus(OrderStatus.CANCEL);
    orderItems.forEach(OrderItem::cancel);
  }

  public int getTotalPrice() {
    return orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
  }
}
