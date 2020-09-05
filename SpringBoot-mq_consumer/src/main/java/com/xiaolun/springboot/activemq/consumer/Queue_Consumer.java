package com.xiaolun.springboot.activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class Queue_Consumer {
	//监听接收的方法
	@JmsListener(destination = "${myQueueName}")
	public void consumerMsg(TextMessage textMessage) throws JMSException {
		String text = textMessage.getText();
		System.out.println("***消费者收到的消息:    " + text);
	}
}
