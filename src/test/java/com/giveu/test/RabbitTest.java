package com.giveu.test;

import com.giveu.test.rabbitmq.ApmAlarmSender;
import com.giveu.test.rabbitmq.RabbitSender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RabbitTest extends RabbitMqApplicationTests {
	@Autowired
	private RabbitSender sender;
	@Autowired
	private ApmAlarmSender apmAlarmSender;

	@Test
	public void hello() {
		sender.send();
	}

	@Test
	public void alarmTest() {
		apmAlarmSender.sendInfo();
		apmAlarmSender.sendWarning();
		apmAlarmSender.sendError();
	}
}
