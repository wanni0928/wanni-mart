package com.wannistudio.wannimart.controller.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class JoinRequest {

  @ApiModelProperty(value = "로그인 계정", required = true)
  private String principal;

  @ApiModelProperty(value = "로그인 비밀번호", required = true)
  private String credential;

  @ApiModelProperty(value = "이름", required = true)
  private String name;

  @ApiModelProperty(value = "사용 이메일", required = true)
  private String email;

  @ApiModelProperty(value = "연락처", required = true)
  private String phoneNumber;

  @ApiModelProperty(value = "주소", required = true)
  private String cityStreetAddress;

  @ApiModelProperty(value = "나머지 주소", required = true)
  private String residentAddress;

  @ApiModelProperty(value = "성별", required = true)
  private String gender;

  @ApiModelProperty(value = "태어난 년도", required = true)
  private int year;

  @ApiModelProperty(value = "태어난 월", required = true)
  private int month;

  @ApiModelProperty(value = "태어난 일", required = true)
  private int day;

  @ApiModelProperty(value = "이용약관 동의 여부", required = true)
  private boolean termsOfUse;

  @ApiModelProperty(value = "개인정보이용 동의 여부", required = true)
  private boolean personalInfoUsage;

  @ApiModelProperty(value = "마케팅활용 동의 여부", required = true)
  private boolean marketingUsage;

  @ApiModelProperty(value = "성인 여부", required = true)
  private boolean isAdult;
}
