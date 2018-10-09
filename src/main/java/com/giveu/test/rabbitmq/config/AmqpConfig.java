package com.giveu.test.rabbitmq.config;

import com.giveu.test.rabbitmq.consts.AmqpConsts;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @title：配置
 * @author：xuan
 * @date：2018/10/5
 */
@Configuration
public class AmqpConfig {

	/**
	 * @title：交换机配置
	 *  FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
	 *  HeadersExchange ：通过添加属性key-value匹配
	 *  DirectExchange:按照routingkey分发到指定队列
	 *  TopicExchange:多关键字匹配
	 * @author：xuan
	 * @date：2018/10/5
	 */
	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(AmqpConsts.EXCHANGE, true, false);
	}

	/**
	 * @title：定义一个队列
	 * durable：持久化到硬盘，保证rabbitmq重启之后消息不会消失
	 * @author：xuan
	 * @date：2018/10/5
	 */
	@Bean
	public Queue queue() {
		return new Queue(AmqpConsts.QUEUE, true);
	}

	/**
	 * @title：将队列绑定到交换机
	 * @author：xuan
	 * @date：2018/10/5
	 */
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(directExchange()).with(AmqpConsts.ROUTINGKEY);
	}


	/**
	 * @title：rabbitmq日志监听队列
	 */
	@Bean
	public Queue infoQueue() {
		return new Queue(AmqpConsts.QUEUE_LOG_INFO);
	}
	@Bean
	public Queue warningQueue() {
		return new Queue(AmqpConsts.QUEUE_LOG_WARNING);
	}
	@Bean
	public Queue errorQueue() {
		return new Queue(AmqpConsts.QUEUE_LOG_ERROR);
	}

	@Bean
	public Binding infoBinding() {
		return BindingBuilder.bind(infoQueue()).to(new TopicExchange(AmqpConsts.EXCHANGE_LOG)).with(AmqpConsts.ROUTINGKEY_LOG_INFO);
	}
	@Bean
	public Binding warningBinding() {
		return BindingBuilder.bind(infoQueue()).to(new TopicExchange(AmqpConsts.EXCHANGE_LOG)).with(AmqpConsts.ROUTINGKEY_LOG_WARNING);
	}
	@Bean
	public Binding errorBinding() {
		return BindingBuilder.bind(infoQueue()).to(new TopicExchange(AmqpConsts.EXCHANGE_LOG)).with(AmqpConsts.ROUTINGKEY_LOG_ERROR);
	}


}
