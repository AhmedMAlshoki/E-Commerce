package com.example.ECommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoAuditing
@EnableMongoRepositories
@EnableCaching
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

}
