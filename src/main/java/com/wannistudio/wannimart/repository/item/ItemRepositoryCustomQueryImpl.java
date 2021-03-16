package com.wannistudio.wannimart.repository.item;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wannistudio.wannimart.controller.item.ItemDto;
import com.wannistudio.wannimart.controller.item.QItemDto;
import com.wannistudio.wannimart.domain.item.Item;
import com.wannistudio.wannimart.domain.item.QFood;
import com.wannistudio.wannimart.domain.item.QGoods;
import com.wannistudio.wannimart.domain.item.QItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import java.util.List;

import static com.wannistudio.wannimart.domain.item.QFood.*;
import static com.wannistudio.wannimart.domain.item.QGoods.*;
import static com.wannistudio.wannimart.domain.item.QItem.*;

public class ItemRepositroyImpl implements ItemRepositoryCustomQuery {

  private final JPAQueryFactory queryFactory;

  public ItemRepositroyImpl(EntityManager entityManager) {
    this.queryFactory = new JPAQueryFactory(entityManager);
  }


  @Override
  public Page<Item> findAllSimple(Pageable pageable) {
    final QueryResults<Item> results = queryFactory
            .selectFrom(item)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();

    final List<Item> contents = results.getResults();
    final long total = results.getTotal();

    return new PageImpl<>(contents, pageable, total);
  }
}
