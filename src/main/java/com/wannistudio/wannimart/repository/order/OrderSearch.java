package com.wannistudio.wannimart.repository.order;

import com.wannistudio.wannimart.domain.order.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus; // 주문상태 (ORDER, CANCEL)

}
