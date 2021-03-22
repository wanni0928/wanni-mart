package com.wannistudio.wannimart.controller.order;

import com.wannistudio.wannimart.controller.api.ApiResult;
import com.wannistudio.wannimart.domain.connect.OrderItem;
import com.wannistudio.wannimart.domain.order.Order;
import com.wannistudio.wannimart.repository.order.OrderRepository;
import com.wannistudio.wannimart.repository.order.OrderSearch;
import com.wannistudio.wannimart.service.ItemService;
import com.wannistudio.wannimart.service.MemberService;
import com.wannistudio.wannimart.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wannistudio.wannimart.controller.api.ApiResult.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;
  private final OrderRepository orderRepository;
  private final ItemService itemService;
  private final MemberService memberService;

  @PostMapping(path = "/order/create")
  public ApiResult<OrderDto> order(@RequestBody OrderRequest orderRequest) {
    return OK(new OrderDto(orderService.order(orderRequest.getMemberId(), orderRequest.getItemId(), orderRequest.getCount())));
  }

  @GetMapping(path = "/order/v1/list")
  public ApiResult<List<OrderQueryDto>> orders() {
    return OK(orderService.findAllOrderQueryDto(new OrderSearch()));
  }

  @PostMapping(path = "/orders/{orderId}/cancel")
  public ApiResult<OrderQueryDto> cancelOrder(@PathVariable("orderId") Long orderId) {
    final Order order = orderService.cancelOrder(orderId);
    return OK(new OrderQueryDto(order.getId(), order.getMember().getName(), order.getOrderDate(), order.getStatus(), order.getDelivery().getAddress()));
  }
}
