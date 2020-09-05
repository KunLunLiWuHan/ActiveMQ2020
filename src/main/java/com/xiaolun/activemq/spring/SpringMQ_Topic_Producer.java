package com.xiaolun.activemq.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service
public class SpringMQ_Topic_Producer {
	private JmsTemplate jmsTemplate;

	public SpringMQ_Topic_Producer(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		SpringMQ_Topic_Producer springMQ_topic_producer = applicationContext.getBean(SpringMQ_Topic_Producer.class);
		//直接调用application.xml里面创建的destinationTopic这个bean设置为目的地就行了
		springMQ_topic_producer.jmsTemplate.setDefaultDestination(((Destination) applicationContext.getBean("destinationTopic")));
		springMQ_topic_producer.jmsTemplate.send(session -> session.createTextMessage("***Spring和ActiveMQ的整合TopicCase111....."));
	}
}

