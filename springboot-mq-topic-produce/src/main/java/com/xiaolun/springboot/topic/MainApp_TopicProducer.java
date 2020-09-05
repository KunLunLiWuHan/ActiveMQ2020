package com.xiaolun.springboot.topic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MainApp_TopicProducer {
	public static void main(String[] args) {
		SpringApplication.run(MainApp_TopicProducer.class);
	}
}