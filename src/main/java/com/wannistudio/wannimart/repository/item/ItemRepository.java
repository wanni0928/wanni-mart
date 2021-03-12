package com.wannistudio.wannimart.repository.item;

import com.wannistudio.wannimart.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
