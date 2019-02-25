package com.duobei.common.enums;

public enum SmsChannelStatusEnums {

  USE(1, "启用"),
  FORBID(2, "禁用")
  ;

  SmsChannelStatusEnums(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  private int code;

  private String msg;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}