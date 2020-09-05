package com.xiaolun.activemq.acknowledge;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class Jms_Transaction_CLIENTACK_Consumer {
	private static final String ACTIVEMQ_URL = "tcp://192.168.10.101:61616";
	private static final String ACTIVEMQ_QUEUE_NAME = "Queue-ACK-NoTransaction";

	public static void main(String[] args) throws JMSException, IOException {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
		Connection connection = activeMQConnectionFactory.createConnection();
		connection.start();
		//消费者设置了手动签收,就必须自己签收,向服务器发送我已经收到消息了
		//开启事务如果不提交,就算手动签收,也是无效的
		Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
		Queue queue = session.createQueue(ACTIVEMQ_QUEUE_NAME);
		MessageConsumer messageConsumer = session.createConsumer(queue);
		messageConsumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					try {
						//Session.CLIENT_ACKNOWLEDGE客户端需要下面的方法手动签收，不然会有手动消费
//						textMessage.acknowledge();
						System.out.println(textMessage.getText());
//						session.commit();
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			}
		});
		//7.关闭资源
		System.in.read();
		messageConsumer.close();
		session.close();
		connection.close();
	}
}