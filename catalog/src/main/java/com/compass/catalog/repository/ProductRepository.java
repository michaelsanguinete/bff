package com.compass.catalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.compass.catalog.entity.Product;

public interface ProductRepository extends MongoRepository<Product, Integer> {

}
