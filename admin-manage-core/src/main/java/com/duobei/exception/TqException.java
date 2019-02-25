package com.duobei.exception;

public class TqException extends Exception {

	private static final long serialVersionUID = -2423053008420551172L;

	private Integer code;

	public TqException() {
		// TODO Auto-generated constructor stub
	}

	public TqException(int code, String message) {
		super(message);
		this.code = code;
	}

	public TqException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public TqException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public TqException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public TqException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
