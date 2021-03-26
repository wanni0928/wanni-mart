package com.wannistudio.wannimart.domain.connect;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wannistudio.wannimart.domain.order.Order;
import com.wannistudio.wannimart.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_item_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  private Item item;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  private int orderPrice; // 주문 가격
  private int count; // 주문 수량

  // 생성 매서드
  public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
    checkNotNull(item, "item must not be null");
    checkArgument(count > 0, "count must not be 0");
    checkArgument(item.getStockQuantity() >= count, "item stock must greater than count");

    OrderItem orderItem = new OrderItem();
    orderItem.setItem(item);
    orderItem.setOrderPrice(orderPrice);
    orderItem.setCount(count);

    item.removeStock(count);
    return orderItem;
  }

  // 비즈니스 로직
  public void cancel() {
    getItem().addStock(count); // 취소된 상품의 재고를 원복 해준다.
  }

  public int getTotalPrice() {
    return getOrderPrice() * getCount();
  }
}
