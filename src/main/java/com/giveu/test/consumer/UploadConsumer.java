package com.giveu.test.consumer;

import com.giveu.test.consts.AmqpConsts;
import com.giveu.test.entity.ApmAlarm;
import com.giveu.test.entity.ImageInfo;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @title：rabbitmq消费者
 * @author：xuan
 * @date：2018/10/5
 */
@Component
public class UploadConsumer {

	@RabbitHandler
	@RabbitListener(queues = AmqpConsts.QUEUE_UPLOAD_RESIZE_PICTURE)
	public void resizePicture(ImageInfo image) {
		System.out.println("resize picture ----- " + image);
	}

	@RabbitHandler
	@RabbitListener(queues = AmqpConsts.QUEUE_UPLOAD_NOTIFY_FRIENDS)
	public void notifyFriends(ImageInfo image) {
		System.out.println("notify friends ----- " + image);
	}

	@RabbitHandler
	@RabbitListener(queues = AmqpConsts.QUEUE_UPLOAD_ADD_POINTS)
	public void addPoints(ImageInfo image) {
		System.out.println("add points ----- " + image);
	}
}
