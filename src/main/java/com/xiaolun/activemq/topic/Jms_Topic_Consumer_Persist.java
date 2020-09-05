package com.xiaolun.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * 持久化Topic消费者
 */
public class Jms_Topic_Consumer_Persist {
	private static final String ACTIVEMQ_URL = "tcp://192.168.10.101:61616";
	private static final String ACTIVEMQ_TOPIC_NAME = "Topic-Persist";

	public static void main(String[] args) throws JMSException, IOException {
		System.out.println("*******我是1号消费者");
		//1.创建连接工厂，按照给定的URL，采用默认的用户名密码
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
		//2.通过连接工厂,获得connection,设置connectionID
		Connection connection = activeMQConnectionFactory.createConnection();
		connection.setClientID("1号ID"); //客户端ID，表示是谁订阅了主题Topic-Persist
		//3.创建会话session
		//两个参数transacted=事务,acknowledgeMode=确认模式(签收)
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//4.创建目的地(具体是队列queue还是主题topic)
		Topic topic = session.createTopic(ACTIVEMQ_TOPIC_NAME);
		//5.通过session创建持久化订阅，参数2是一个备注
		TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "1号消费者");
		//6.启动连接
		connection.start();
		//7.接收消息
		topicSubscriber.setMessageListener(message -> {
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				try {
					System.out.println("收到的持久化订阅消息: " + textMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});

		/**
		 * 一定要先运行一次消费者,类似于像MQ注册,我订阅了这个主题
		 * 然后再运行主题生产者
		 * 无论消费着是否在线,都会接收到,在线的立即接收到,不在线的等下次上线把没接收到的接收
		 */
		System.in.read();
		session.close();
		connection.close();
	}
}