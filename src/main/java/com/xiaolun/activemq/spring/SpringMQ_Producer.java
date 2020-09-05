package com.xiaolun.activemq.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class SpringMQ_Producer {

	@Autowired
	private JmsTemplate jmsTemplate;


	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		SpringMQ_Producer springMQ_producer = applicationContext.getBean(SpringMQ_Producer.class);

		springMQ_producer.jmsTemplate.send(session -> session.createTextMessage("***Spring和ActiveMQ的整合case111....."));
		System.out.println("********send task over");
	}
}


