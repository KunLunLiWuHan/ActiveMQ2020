package com.xiaolun.springboot.activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

@Component
@EnableJms //开启Springboot的Jms
public class ConfigBean {
	@Value("${myQueueName}")
	private String myQueueName;

	@Bean
	public ActiveMQQueue queue() {
		//创建一个ActiveMQQueue
		return new ActiveMQQueue(myQueueName);
	}
}
