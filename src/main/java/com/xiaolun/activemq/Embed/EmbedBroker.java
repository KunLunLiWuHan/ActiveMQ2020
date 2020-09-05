package com.xiaolun.activemq.Embed;
import org.apache.activemq.broker.BrokerService;

public class EmbedBroker {

	public static void main(String[] args) throws Exception {
		//ActiveMQ也支持在vm中通信基于嵌入的broker
		BrokerService brokerService = new BrokerService();
		brokerService.setUseJmx(true);
		brokerService.addConnector("tcp://127.0.0.1:61616");
		brokerService.start();

//		System.in.read();
	}
}
