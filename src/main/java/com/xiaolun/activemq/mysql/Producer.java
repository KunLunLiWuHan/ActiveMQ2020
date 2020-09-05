package com.xiaolun.activemq.mysql;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {
	//故障转移的配置模式
	private static final String ACTIVEMQ_URL = "failover:(tcp://192.168.10.101:61616,tcp://192.168.10.101:61617,tcp://192.168.10.101:61618)";
	private static final String ACTIVEMQ_QUEUE_NAME = "Queue-Cluter";

	public static void main(String[] args) throws JMSException {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setAlwaysSyncSend(true);//开启开启异 步投递
		activeMQConnectionFactory.setBrokerURL(ACTIVEMQ_URL);
		Connection connection = activeMQConnectionFactory.createConnection();
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue(ACTIVEMQ_QUEUE_NAME);
		MessageProducer messageProducer = session.createProducer(queue);
		//一定要开启持久化
		messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
		connection.start();
		for (int i = 0; i < 3; i++) {
			TextMessage textMessage = session.createTextMessage("Queue-JdbcPersistence测试消息" + i);
			messageProducer.send(textMessage);
		}
		session.commit();
		System.out.println("消息发送完成");
		messageProducer.close();
		session.close();
		connection.close();
	}
}
