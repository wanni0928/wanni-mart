package com.wannistudio.wannimart.repository.categoryitem;

import com.wannistudio.wannimart.domain.connect.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryItemRepository extends JpaRepository<ItemCategory, Long> {
}
