package com.giveu.test.enums;

/**
 * @title：告警类型
 * @author：xuan
 * @date：2018/10/10
 */
public enum WebApi {
	EXCHANGE_LIST(1, "exchanges"),
	;

	private int code;
	private String value;

	WebApi(int code, String value) {
		this.code = code;
		this.value = value;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
