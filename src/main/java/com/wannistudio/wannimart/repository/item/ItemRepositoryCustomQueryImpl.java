package com.wannistudio.wannimart.repository.item;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wannistudio.wannimart.controller.item.ItemDto;
import com.wannistudio.wannimart.controller.item.QItemDto;
import com.wannistudio.wannimart.domain.category.QCategory;
import com.wannistudio.wannimart.domain.connect.ItemCategoryQueryDto;
import com.wannistudio.wannimart.domain.connect.QItemCategory;
import com.wannistudio.wannimart.domain.connect.QItemCategoryQueryDto;
import com.wannistudio.wannimart.domain.item.Item;
import com.wannistudio.wannimart.domain.item.QFood;
import com.wannistudio.wannimart.domain.item.QGoods;
import com.wannistudio.wannimart.domain.item.QItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Set;

import static com.wannistudio.wannimart.domain.category.QCategory.*;
import static com.wannistudio.wannimart.domain.connect.QItemCategory.*;
import static com.wannistudio.wannimart.domain.item.QFood.*;
import static com.wannistudio.wannimart.domain.item.QGoods.*;
import static com.wannistudio.wannimart.domain.item.QItem.*;

public class ItemRepositoryCustomQueryImpl implements ItemRepositoryCustomQuery {

  private final JPAQueryFactory queryFactory;

  private final QItem item = new QItem("item");
  private final QFood food = item.as(QFood.class);
  private final QGoods goods = item.as(QGoods.class);

  public ItemRepositoryCustomQueryImpl(EntityManager entityManager) {
    this.queryFactory = new JPAQueryFactory(entityManager);
  }


  @Override
  public Page<Item> findAllSimple(Pageable pageable) {
    final QueryResults<Item> itemDtoQueryResults = queryFactory
            .selectFrom(item)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();
    final List<Item> contents = itemDtoQueryResults.getResults();
    final long total = itemDtoQueryResults.getTotal();
    return new PageImpl<>(contents, pageable, total);
  }

  @Override
  public Page<ItemCategoryQueryDto> findAllAdvanced(Pageable pageable) {
    return null;
  }

  @Override
  public List<Item> findAllWithCategory(int offset, int limit) {
    return queryFactory
            .select(item)
            .from(item)
            .offset(offset)
            .limit(limit)
            .fetchResults()
            .getResults();
  }

  @Override
  public List<ItemCategoryQueryDto> findAllWithItemCategory(int offset, int limit) {
    return queryFactory
            .select(Projections.constructor(ItemCategoryQueryDto.class,
                    itemCategory.id,
                    item.name,
                    item.deliveryType,
                    item.packageType,
                    item.price,
                    item.stockQuantity,
                    item.summary,
                    item.unit,
                    item.volume,
                    food.allergyInformation,
                    food.expiration,
                    food.importFrom,
                    goods.material,
                    goods.size,
                    goods.name)).from(itemCategory)
            .join(item).on(itemCategory.item.id.eq(item.id))
            .join(category).on(itemCategory.category.id.eq(category.id))
            .offset(offset)
            .limit(limit)
            .orderBy(itemCategory.id.desc())
            .fetchResults()
            .getResults();
  }

}
