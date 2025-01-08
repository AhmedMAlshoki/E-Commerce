package com.example.ECommerce.Repositories;

import com.example.ECommerce.Documents.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
//https://www.mongodb.com/resources/products/compatibilities/spring-boot#update-using-mongotemplate
public interface OrderRepository extends MongoRepository<Order, String> {
}
