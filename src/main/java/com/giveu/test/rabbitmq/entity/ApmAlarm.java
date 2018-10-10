package com.giveu.test.rabbitmq.entity;

import com.giveu.test.rabbitmq.enums.AlarmType;

import java.io.Serializable;

/**
 * @title：告警消息
 * @author：xuan
 * @date：2018/10/10
 */
public class ApmAlarm extends BaseEntity implements Serializable {
	private Long time;
	private String type;
	private String content;

	public ApmAlarm(){}

	public ApmAlarm(AlarmType alarmType, String content) {
		this.time = System.currentTimeMillis();
		this.type = alarmType.getType();
		this.content = content;
	}

	public ApmAlarm(String type, String content) {
		this.time = System.currentTimeMillis();
		this.type = type;
		this.content = content;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
