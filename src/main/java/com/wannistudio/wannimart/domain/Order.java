package com.wannistudio.wannimart.domain;

import com.wannistudio.wannimart.domain.connect.OrderItem;
import com.wannistudio.wannimart.domain.delivery.Delivery;
import com.wannistudio.wannimart.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
  @Id @GeneratedValue
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

  // 연관관계 편의 메서드 (양방향 연관관계 시 필요.)
  public void setMember(Member member) {
    this.member = member;
    member.getOrders().add(this);
  }
}
