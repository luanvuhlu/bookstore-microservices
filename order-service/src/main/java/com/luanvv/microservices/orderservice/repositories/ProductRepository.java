package com.luanvv.microservices.orderservice.repositories;

import com.luanvv.microservices.orderservice.models.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}
