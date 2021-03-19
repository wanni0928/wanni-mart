package com.wannistudio.wannimart.controller.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
  private Long memberId;
  private Long itemId;
  private int count;
}
