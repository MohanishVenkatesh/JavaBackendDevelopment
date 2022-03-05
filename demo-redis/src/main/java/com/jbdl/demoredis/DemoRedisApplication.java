package com.jbdl.demoredis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

@SpringBootApplication
public class DemoRedisApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoRedisApplication.class, args);


	}

	@Override
	public void run(String... args) throws Exception {
		Class<Person> personClass = Person.class;
		Field fields [] = personClass.getDeclaredFields();

		Class<DemoController> demoControllerClass = DemoController.class;
		Method [] methods = demoControllerClass.getMethods();
		Arrays.stream(fields)
				.forEach(field -> System.out.println("Field Name: "  + field.getName()));




		Arrays.stream(methods)
				.forEach(method -> System.out.println("Method Name: " + method.getName()));

	}
}
