package com.wannistudio.wannimart.controller.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

  @ApiModelProperty(value = "구매할 아이템 번호", required = true, example = "1")
  private Long itemId;

  @ApiModelProperty(value = "구매할 아이템 갯수", required = true, example = "2")
  private int count;
}
