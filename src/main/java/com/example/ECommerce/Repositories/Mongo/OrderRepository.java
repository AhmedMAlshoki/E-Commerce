package com.example.ECommerce.Repositories.Mongo;

import com.example.ECommerce.Documents.Order;
import com.example.ECommerce.Enums.STATUS;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findByProductId(Long id);

    List<Order> findByUserId(Long id);

    @Query("{ '_id' : ?0 }, { '$set' : { 'status' : ?1 } }")
    Order changeStatus(String id, STATUS status);

}
