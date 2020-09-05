package com.xiaolun.activemq.mysql;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class Consumer {
	private static final String ACTIVEMQ_URL = "tcp://192.168.10.101:61616";
	private static final String QUEUE_NAME = "Queue-delay";
	public static void main(String[] args) throws JMSException, IOException {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(ACTIVEMQ_URL);
		Connection connection = activeMQConnectionFactory.createConnection();
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue(QUEUE_NAME);
		MessageConsumer messageConsumer = session.createConsumer(queue);
		connection.start();
		messageConsumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					try {
						session.commit();
						System.out.println("消费者收到消息" + textMessage.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			}
		});
		System.in.read();
	}
}


