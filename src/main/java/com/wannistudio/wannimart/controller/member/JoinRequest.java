package com.wannistudio.wannimart.controller.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class JoinRequest {

  @ApiModelProperty(value = "로그인 계정", required = true, example = "testUserAccount")
  private String principal;

  @ApiModelProperty(value = "로그인 비밀번호", required = true, example = "1234")
  private String credential;

  @ApiModelProperty(value = "이름", required = true, example = "testUserName")
  private String name;

  @ApiModelProperty(value = "사용 이메일", required = true, example = "testUserEmail@gmail.com")
  private String email;

  @ApiModelProperty(value = "연락처", required = true, example = "010-1233-4567")
  private String phoneNumber;

  @ApiModelProperty(value = "주소", required = true, example = "서울시 어딘가")
  private String cityStreetAddress;

  @ApiModelProperty(value = "나머지 주소", required = true, example = "어느 아파트 몇동 몇호")
  private String residentAddress;

  @ApiModelProperty(value = "성별", required = true, example = "남")
  private String gender;

  @ApiModelProperty(value = "태어난 년도", required = true, example = "2021")
  private int year;

  @ApiModelProperty(value = "태어난 월", required = true, example = "03")
  private int month;

  @ApiModelProperty(value = "태어난 일", required = true, example = "23")
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
