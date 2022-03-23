package com.example.jbdl.minorproject1;

import com.example.jbdl.minorproject1.models.Transcation;
import com.example.jbdl.minorproject1.models.enums.TransactionStatus;
import com.example.jbdl.minorproject1.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MinorProject1Application {

	@Autowired
	TransactionRepository transactionRepository;

	public static void main(String[] args) {
		SpringApplication.run(MinorProject1Application.class, args);
	}


}
