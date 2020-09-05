package com.xiaolun.activemq.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class SpringMQ_Consumer {
	@Autowired
	private JmsTemplate jmsTemplate;

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		SpringMQ_Consumer springMQ_consumer = applicationContext.getBean(SpringMQ_Consumer.class);
		String returnValue = (String) springMQ_consumer.jmsTemplate.receiveAndConvert();
		System.out.println("****消费者收到的消息:   " + returnValue);
	}
}
