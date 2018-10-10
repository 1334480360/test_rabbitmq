package com.giveu.test.enums;

/**
 * @title：告警类型
 * @author：xuan
 * @date：2018/10/10
 */
public enum AlarmType {
	INFO(1, "INFO"),
	WARNING(1, "WARNING"),
	ERROR(1, "ERROR"),
	;

	private int code;
	private String type;

	AlarmType(int code, String type) {
		this.code = code;
		this.type = type;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
