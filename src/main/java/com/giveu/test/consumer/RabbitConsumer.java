package com.giveu.test.consumer;

import com.giveu.test.consts.AmqpConsts;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @title：rabbitmq消费者
 * @author：xuan
 * @date：2018/10/5
 */
@Component
public class RabbitConsumer {

	@RabbitHandler
	@RabbitListener(queues = AmqpConsts.QUEUE)
	public void process(String context) {
		System.out.println("Receiver----- " + context);
	}
}
