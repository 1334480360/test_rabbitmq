package com.giveu.test.consts;

public class AmqpConsts {
	/**
	 * direct topic练习
	 */
	public static final String QUEUE = "spring-boot-queue";
	public static final String EXCHANGE = "spring-boot-exchange";
	public static final String ROUTINGKEY = "spring-boot-routingKey";

	/**
	 * rabbitmq日志监控
	 */
	public static final String QUEUE_LOG_INFO = "queue-log-info";
	public static final String QUEUE_LOG_WARNING = "queue-log-warning";
	public static final String QUEUE_LOG_ERROR = "queue-log-error";
	public static final String EXCHANGE_LOG = "amq.rabbitmq.log";
	public static final String ROUTINGKEY_LOG_INFO = "info";
	public static final String ROUTINGKEY_LOG_WARNING = "warning";
	public static final String ROUTINGKEY_LOG_ERROR = "error";

	/**
	 * 告警
	 */
	public static final String QUEUE_ALARM_INFO = "apm-alarm-info";
	public static final String QUEUE_ALARM_WARNING = "apm-alarm-warning";
	public static final String QUEUE_ALARM_ERROR = "apm-alarm--error";
	public static final String EXCHANGE_ALARM = "apm-alarm-exchange";
	public static final String ROUTINGKEY_ALARM_INFO = "info";
	public static final String ROUTINGKEY_ALARM_WARNING = "warning";
	public static final String ROUTINGKEY_ALARM_ERROR = "error";

	/**
	 * 上传图片
	 */
	public static final String QUEUE_UPLOAD_RESIZE_PICTURE = "upload-resize-picture";
	public static final String QUEUE_UPLOAD_NOTIFY_FRIENDS = "upload-notify-friends";
	public static final String QUEUE_UPLOAD_ADD_POINTS = "upload-add-points";
	public static final String EXCHANGE_UPLOAD = "upload-exchange";

}
