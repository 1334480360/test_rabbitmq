package com.giveu.test.controller;

import com.giveu.test.rabbitmq.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit")
public class RabbitController {
	@Autowired
	RabbitSender rabbitSender;

	/**
	 * @title：生产消息
	 * @author：xuan
	 * @date：2018/10/5
	 */
	@RequestMapping("send")
	public void testSend() {
		rabbitSender.send();
	}
}
