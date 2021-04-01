package com.wannistudio.wannimart.repository.item;

import com.wannistudio.wannimart.domain.connect.ItemCategoryQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemCacheRepository {
  private final RedisTemplate<String, Object> redisTemplate;
  private final ValueOperations<String, Object> valueOperations;

  public static final String ITEM_FIRST_PAGE = "itemFirstPage";

  public ItemCacheRepository(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
    this.valueOperations = redisTemplate.opsForValue();
  }

  public void save(List<ItemCategoryQueryDto> firstItemPage) {
    valueOperations.set(ITEM_FIRST_PAGE, firstItemPage);
  }

  public Page<ItemCategoryQueryDto> getFirstPage(Pageable pageable) {
    List<ItemCategoryQueryDto> list = (List<ItemCategoryQueryDto>) valueOperations.get(ITEM_FIRST_PAGE);
    return new PageImpl<>(list, pageable, list.size());
  }

  public void delete() {
    redisTemplate.delete(ITEM_FIRST_PAGE);
  }
}
