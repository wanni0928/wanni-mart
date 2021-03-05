package com.wannistudio.wannimart.domain.member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
  USER("ROLE_USER");

  private final String value;

  public String value() {return value;}

  public static Role of(String name) {
    for (Role role : Role.values()) {
      if(role.name().equals(name)) {
        return role;
      }
    }
    return null;
  }
}
