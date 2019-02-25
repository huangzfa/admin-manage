package com.duobei.common.enums;

public enum ZfbAccountStateEnums {
	  USE(new Byte("1"), "启用"),
    FORBID(new Byte("0"),"禁用")
    ;

    private Byte code;
    private String msg;


    ZfbAccountStateEnums(Byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }


	public Byte getCode() {
		return code;
	}

	public void setCode(Byte code) {
		this.code = code;
	}


  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
