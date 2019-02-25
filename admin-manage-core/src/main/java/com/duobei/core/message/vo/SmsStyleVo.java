package com.duobei.core.message.vo;

import java.io.Serializable;

/**
 * Description:
 *
 * @author wm
 * @create 2018/12/26
 * @since 1.0.0
 */
public class SmsStyleVo implements Serializable {

  /**
   * 短信类型编码
   */
  private String userfulCode;

  /**
   * 短信类型名称
   */
  private String userfulName;


  public String getUserfulCode() {
    return userfulCode;
  }

  public void setUserfulCode(String userfulCode) {
    this.userfulCode = userfulCode;
  }

  public String getUserfulName() {
    return userfulName;
  }

  public void setUserfulName(String userfulName) {
    this.userfulName = userfulName;
  }
}