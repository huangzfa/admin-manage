package com.duobei.core.sys.domain;

import java.io.Serializable;

public class OssUploadResult implements Serializable {

  private static final long serialVersionUID = 5787592792055471287L;

  private boolean success;
  private String url;
  private String msg;
  private String fileMd5;
  private int width;
  private int height;

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getFileMd5() {
    return fileMd5;
  }

  public void setFileMd5(String fileMd5) {
    this.fileMd5 = fileMd5;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

}
