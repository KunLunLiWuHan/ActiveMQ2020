package com.xiaolun.springboot.activemq.queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Queue_Producer {
	@Autowired
	//JmsMessagingTemplate是Springboot的Jms模板,Spring的是JmsTemplate
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Autowired
	//把ConfigBean类的ActiveMQQueue注入进来
	private ActiveMQQueue activeMQQueue;

	//发送Queue的方法
	public void producerMsg() {
		jmsMessagingTemplate.convertAndSend(activeMQQueue, "**************" + UUID.randomUUID().toString());
	}

//	//构造注入对象(推荐)
//	public Queue_Producer(JmsMessagingTemplate jmsMessagingTemplate, ActiveMQQueue activeMQQueue) {
//		this.jmsMessagingTemplate = jmsMessagingTemplate;
//		this.activeMQQueue = activeMQQueue;
//	}
	//间隔3秒投递,SpringBoot的Scheduled用来定时执行
	@Scheduled(fixedDelay = 3000)
	public void producerMsgScheduled() {
		jmsMessagingTemplate.convertAndSend(activeMQQueue, "**************Scheduled" + UUID.randomUUID().toString());
		System.out.println("Scheduled定时投递");
	}

}

