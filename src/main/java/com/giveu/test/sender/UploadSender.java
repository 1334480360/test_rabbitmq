package com.giveu.test.sender;

import com.giveu.test.consts.AmqpConsts;
import com.giveu.test.entity.ApmAlarm;
import com.giveu.test.entity.ImageInfo;
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
public class UploadSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void send() {
		ImageInfo imageInfo = new ImageInfo(1L, 10001L, "c://images/1.png");

		rabbitTemplate.convertAndSend(AmqpConsts.EXCHANGE_UPLOAD, null, imageInfo);
	}

}
