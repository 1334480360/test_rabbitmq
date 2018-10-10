package com.giveu.test.sender;

import com.giveu.test.consts.AmqpConsts;
import com.giveu.test.entity.ApmAlarm;
import com.giveu.test.enums.AlarmType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @title：apm告警生产者
 * @author：xuan
 * @date：2018/10/10
 */
@Component
public class ApmAlarmSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendInfo() {
		String msg = "info alarm";
		ApmAlarm apmAlarm = new ApmAlarm(AlarmType.INFO, msg);

		rabbitTemplate.convertAndSend(AmqpConsts.EXCHANGE_ALARM, AmqpConsts.ROUTINGKEY_ALARM_INFO, apmAlarm);
	}
	public void sendWarning() {
		String msg = "warning alarm";
		ApmAlarm apmAlarm = new ApmAlarm(AlarmType.WARNING, msg);

		rabbitTemplate.convertAndSend(AmqpConsts.EXCHANGE_ALARM, AmqpConsts.ROUTINGKEY_ALARM_WARNING, apmAlarm);
	}
	public void sendError() {
		String msg = "error alarm";
		ApmAlarm apmAlarm = new ApmAlarm(AlarmType.ERROR, msg);

		rabbitTemplate.convertAndSend(AmqpConsts.EXCHANGE_ALARM, AmqpConsts.ROUTINGKEY_ALARM_ERROR, apmAlarm);
	}

}
