package com.wannistudio.wannimart.repository.order;

import com.wannistudio.wannimart.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustomQuery {

}
