package com.example.ECommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableMongoAuditing
@EnableAspectJAutoProxy
@EnableMongoRepositories(basePackages = "com.example.ECommerce.Repositories.Mongo")
@EnableJpaRepositories(basePackages = "com.example.ECommerce.Repositories.JPA")
@EnableRedisRepositories(basePackages = "com.example.ECommerce.Repositories.Redis")
@EnableCaching
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

}
