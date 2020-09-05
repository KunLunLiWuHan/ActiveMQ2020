package com.xiaolun.springboot.activemq;

import com.xiaolun.springboot.activemq.queue.Queue_Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest(classes = MainApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestActiveMQ {
	@Autowired
	private Queue_Producer queue_producer;

	@Test
	public void testSend() {
		queue_producer.producerMsg();
	}
}


