package com.wannistudio.wannimart.repository.order;

import com.wannistudio.wannimart.domain.order.OrderStatus;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderSearch {
    private Long orderId;
    private String memberName;
    private OrderStatus orderStatus; // 주문상태 (ORDER, CANCEL)

}
