package com.giveu.test.rabbitmq;

import com.giveu.test.rabbitmq.consts.AmqpConsts;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @title：rabbitmq消费者
 * @author：xuan
 * @date：2018/10/5
 */
@Component
@RabbitListener(queues = AmqpConsts.QUEUE)
public class RabbitConsumer {

	@RabbitHandler
	public void process(String context) {
		System.out.println("Receiver----- " + context);
	}
}
