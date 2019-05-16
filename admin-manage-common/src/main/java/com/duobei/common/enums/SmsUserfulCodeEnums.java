package com.duobei.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SmsUserfulCodeEnums {

  COLLECTION("COLLECTION", "催收短信"),
  NORMAL("NORMAL", "常规短信"),
  MARKETING("MARKETING", "营销短信"),
  ;
  public static String findEnvByCode(String code) {
    for (SmsUserfulCodeEnums environmentType : SmsUserfulCodeEnums.values()) {
      if (environmentType.getCode().equals(code)) {
        return environmentType.getMsg();
      }
    }
    return null;
  }

  @Getter
  private String code;

  @Getter
  private String msg;


}