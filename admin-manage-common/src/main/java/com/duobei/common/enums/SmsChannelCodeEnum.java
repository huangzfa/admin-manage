package com.duobei.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SmsChannelCodeEnum {

  CHUANGLAN("chuanglan", "创蓝",1),
  NORMAL("dahan", "大汉",2);
  ;
  public static String findEnvByCode(String code) {
    for (SmsChannelCodeEnum environmentType : SmsChannelCodeEnum.values()) {
      if (environmentType.getCode().equals(code)) {
        return environmentType.getMsg();
      }
    }
    return null;
  }

  public static Integer findEnvType(String code) {
    for (SmsChannelCodeEnum environmentType : SmsChannelCodeEnum.values()) {
      if (environmentType.getCode().equals(code)) {
        return environmentType.getType();
      }
    }
    return null;
  }

  @Getter
  private String code;

  @Getter
  private String msg;

  @Getter
  private Integer type;


}