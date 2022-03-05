package com.example.jbdl.demosecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSecurityApplication.class, args);

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String s1 = bCryptPasswordEncoder.encode("sagar123");
		String s2 = bCryptPasswordEncoder.encode("mohanish123");
		String s3 = bCryptPasswordEncoder.encode("APARNA");
		System.out.println(s1 + " " + s2 + " " + s3);


		System.out.println(bCryptPasswordEncoder.matches( "password123" , s1));
		System.out.println(bCryptPasswordEncoder.matches( "password123" , s3));


	}

}
