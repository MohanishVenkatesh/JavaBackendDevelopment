package com.example.springdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDemoApplication {
	private static Logger logger = LoggerFactory.getLogger(SpringDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);

		logger.error("this is a error log");
		logger.warn("this is a warn log ");
		logger.info("this is a info log");
		logger.debug("this is a debug log");
		logger.trace("this is a trace log");

		MyThread thread = new MyThread();
		thread.start();

			}
		private static class MyThread extends Thread{
		@Override
			public void run(){
			logger.info("This is an info log in my Thread");

		}
		}
}
