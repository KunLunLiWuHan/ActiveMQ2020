package com.xiaolun.springboot.topic.produce;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
//@EnableScheduling
public class Topic_Producer {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Autowired
	private ActiveMQTopic activeMQTopic;

	@Scheduled(fixedDelay = 3000)
	public void producer() {
		jmsMessagingTemplate.convertAndSend(activeMQTopic, "主题消息:    " + UUID.randomUUID().toString());
	}

//	public Topic_Producer(JmsMessagingTemplate jmsMessagingTemplate, ActiveMQTopic activeMQTopic) {
//		this.jmsMessagingTemplate = jmsMessagingTemplate;
//		this.activeMQTopic = activeMQTopic;
//	}
}
