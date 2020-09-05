package com.xiaolun.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 简单消息消费者
 */
public class JmsConsumer {
	private static final String ACTIVEMQ_URL = "tcp://192.168.10.101:61616";
	private static final String QUEUE_NAME = "Queue-JdbcPersistence";
//	private static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";; //嵌入式broker
//	private static final String QUEUE_NAME = "queue01";

//	private static final String ACTIVEMQ_URL = "nio://192.168.10.101:61608";
//	private static final String QUEUE_NAME = "Queue-NIO";

	public static void main(String[] args) throws JMSException {
		//1.创建连接工厂，按照给定的URL，采用默认的用户名密码
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
		//2.通过连接工厂,获得connection并启动访问
		Connection connection = activeMQConnectionFactory.createConnection();
		connection.start();
		//3.创建会话session
		//两个参数transacted=事务,acknowledgeMode=确认模式(签收)
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//4.创建目的地(具体是队列queue还是主题topic)
		Queue queue = session.createQueue(QUEUE_NAME);
		//5.创建消息的消费者,指定消费哪一个队列里面的消息
		MessageConsumer messageConsumer = session.createConsumer(queue);
		//循环获取
		while (true) {
			//6.通过消费者调用方法获取队列里面的消息(发送的消息是什么类型,接收的时候就强转成什么类型)
			TextMessage textMessage = (TextMessage) messageConsumer.receive();
			if (textMessage != null) {
				System.out.println("****消费者接收到的消息:  " + textMessage.getText());
			} else {
				break;
			}
		}
		//7.关闭资源
		messageConsumer.close();
		session.close();
		connection.close();
	}
}
