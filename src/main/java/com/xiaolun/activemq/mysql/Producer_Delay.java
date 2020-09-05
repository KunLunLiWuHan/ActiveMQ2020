package com.xiaolun.activemq.mysql;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

import javax.jms.*;

public class Producer_Delay {
	private static final String ACTIVEMQ_URL = "tcp://192.168.10.101:61616";
	private static final String QUEUE_NAME = "Queue-delay";

	public static void main(String[] args) throws JMSException {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(ACTIVEMQ_URL);
		Connection connection = activeMQConnectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue(QUEUE_NAME);
		//向上转型到ActiveMQMessageProducer
		MessageProducer messageProducer = session.createProducer(queue);
		long delay = 3 * 1000;      //延迟投递的时间
		long period = 4 * 1000;     //每次投递的时间间隔
		int repeat = 5;                     //投递的次数

		for (int i = 1; i <= 3; i++) {
			TextMessage textMessage = session.createTextMessage("message-延时投递" + i);
			//给消息设置属性以便MQ服务器读取到这些信息,好做对应的处理
			textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
			textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, period);
			textMessage.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, repeat);
			messageProducer.send(textMessage);
		}
		messageProducer.close();
		session.close();
		connection.close();
	}
}
