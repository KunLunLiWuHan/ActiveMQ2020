package com.xiaolun.springboot.topic.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class Topic_Consumer {

	@JmsListener(destination = "${myTopicName}")
	public void consumer(TextMessage textMessage) throws JMSException {
		System.out.println("订阅着收到消息:    " + textMessage.getText());
	}
}