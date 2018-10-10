package com.giveu.test.rabbitmq;

import com.giveu.test.rabbitmq.consts.AmqpConsts;
import com.giveu.test.rabbitmq.entity.ApmAlarm;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @title：rabbitmq消费者
 * @author：xuan
 * @date：2018/10/5
 */
@Component
public class ApmAlarmConsumer {

	@RabbitHandler
	@RabbitListener(queues = AmqpConsts.QUEUE_ALARM_INFO)
	public void info(ApmAlarm alarm) {
		System.out.println("info receiver----- " + alarm);
	}

	@RabbitHandler
	@RabbitListener(queues = AmqpConsts.QUEUE_ALARM_WARNING)
	public void warning(ApmAlarm alarm) {
		System.out.println("warning receiver----- " + alarm);
	}

	@RabbitHandler
	@RabbitListener(queues = AmqpConsts.QUEUE_ALARM_ERROR)
	public void error(ApmAlarm alarm) {
		System.out.println("error receiver----- " + alarm);
	}
}
