package com.xiaolun.activemq.acknowledge;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Jms_Transaction_AUTOACK_Producer {
	private static final String ACTIVEMQ_URL = "tcp://192.168.10.101:61616";
	private static final String ACTIVEMQ_QUEUE_NAME = "Queue-ACK-NoTransaction";

	public static void main(String[] args) throws JMSException {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
		Connection connection = activeMQConnectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		Queue queue = session.createQueue(ACTIVEMQ_QUEUE_NAME);
		MessageProducer producer = session.createProducer(queue);

		for (int i = 1; i <= 3; i++) {
			TextMessage textMessage = session.createTextMessage("Transaction_AUTOACK-msg:   " + i);
			producer.send(textMessage);
		}
//		session.commit(); //提交事务
		System.out.println("发送完成");
		producer.close();
		session.close();
		connection.close();
	}
}