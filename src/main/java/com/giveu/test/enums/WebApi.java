package com.giveu.test.enums;

/**
 * @title：告警类型
 * @author：xuan
 * @date：2018/10/10
 */
public enum WebApi {
	EXCHANGE_LIST(1, "exchanges", "交换器列表"),
	USER_LIST(2, "users", ""),
	NODE_LIST(3, "nodes", ""),
	;

	private int code;
	private String value;
	private String desc;

	WebApi(int code, String value, String desc) {
		this.code = code;
		this.value = value;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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
