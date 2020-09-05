package com.xiaolun.activemq.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service
public class SpringMQ_Topic_Consumer {
	private JmsTemplate jmsTemplate;

	public SpringMQ_Topic_Consumer(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		SpringMQ_Topic_Consumer springMQConsumer = applicationContext.getBean(SpringMQ_Topic_Consumer.class);
		//直接调用application.xml里面创建的destinationTopic这个bean设置为目的地就行了
		springMQConsumer.jmsTemplate.setDefaultDestination(((Destination) applicationContext.getBean("destinationTopic")));
		String returnValue = (String) springMQConsumer.jmsTemplate.receiveAndConvert();
		System.out.println("****消费者收到的消息:   " + returnValue);
	}
}
