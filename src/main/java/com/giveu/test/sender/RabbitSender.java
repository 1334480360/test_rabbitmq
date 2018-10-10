package com.giveu.test.sender;

import com.giveu.test.consts.AmqpConsts;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.UUID;

/**
 * @title：rabbitmq生产者
 * @author：xuan
 * @date：2018/10/5
 */
@Component
public class RabbitSender implements RabbitTemplate.ConfirmCallback {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@PostConstruct
	public void RabbitHandler() {
		rabbitTemplate.setConfirmCallback(this);
	}

	public void send() {
		String context = "hello " + new Date();
		System.out.println("Sender---- " + context);

		//消息回调ID
		CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
		rabbitTemplate.convertAndSend(AmqpConsts.EXCHANGE, AmqpConsts.ROUTINGKEY, context, correlationId);
	}

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		System.out.println("callback id ---- " + correlationData);
		if (ack) {
			System.out.println("消息成功消费");
		} else {
			System.out.println("消息消费失败：" + cause);
		}

	}
}
