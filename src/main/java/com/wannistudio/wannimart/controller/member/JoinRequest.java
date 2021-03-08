package com.wannistudio.wannimart.controller.member;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class JoinRequest {
  private String principal;
  private String credential;
  private String name;
  private String email;
  private String phoneNumber;
  private String cityStreetAddress;
  private String residentAddress;
  private String gender;
  private int year;
  private int month;
  private int day;
  private boolean termsOfUse;
  private boolean personalInfoUsage;
  private boolean marketingUsage;
  private boolean isAdult;
}
