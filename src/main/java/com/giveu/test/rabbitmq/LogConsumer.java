package com.giveu.test.rabbitmq;

import com.giveu.test.rabbitmq.consts.AmqpConsts;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @title：rabbitmq日志监听消费者
 * @author：xuan
 * @date：2018/10/9
 */
@Component
public class LogConsumer {

	@RabbitListener(queues = AmqpConsts.QUEUE_LOG_INFO)
	@RabbitHandler
	public void info(String context) {
		System.out.println("Receiver RabbitMQ info message ----- " + context);
	}

	@RabbitListener(queues = AmqpConsts.QUEUE_LOG_WARNING)
	@RabbitHandler
	public void warning(String context) {
		System.out.println("Receiver RabbitMQ warning message ----- " + context);

	}
	@RabbitListener(queues = AmqpConsts.QUEUE_LOG_ERROR)
	@RabbitHandler
	public void error(String context) {
		System.out.println("Receiver RabbitMQ error message ----- " + context);
	}
}
