package com.example.seven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
public class SevenApplication {

	public static void main(String[] args) {
		SpringApplication.run(SevenApplication.class, args);
	}

}
