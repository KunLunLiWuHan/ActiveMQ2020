package com.xiaolun.activemq.spring;

import org.springframework.stereotype.Component;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 实现MessageListener的类,需要把这个类交给xml配置里面的DefaultMessageListenerContainer管理
 * 注解 @Component 等价于 <bean id="myMessageListener" class="com.xiaolun.activemq.spring.MyMessageListener"></bean>
 */
@Component
public class MyMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			try {
				System.out.println("消费者收到的消息" + textMessage.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
