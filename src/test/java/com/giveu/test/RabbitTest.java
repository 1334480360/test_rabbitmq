package com.giveu.test;

import com.giveu.test.sender.ApmAlarmSender;
import com.giveu.test.sender.RabbitSender;
import com.giveu.test.sender.UploadSender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RabbitTest extends RabbitMqApplicationTests {
	@Autowired
	private RabbitSender sender;
	@Autowired
	private ApmAlarmSender apmAlarmSender;
	@Autowired
	private UploadSender uploadSender;

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

	@Test
	public void uploadTest() {
		uploadSender.send();
	}
}
