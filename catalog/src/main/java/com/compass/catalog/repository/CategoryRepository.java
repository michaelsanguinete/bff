package com.compass.catalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.compass.catalog.entity.Category;

public interface CategoryRepository extends MongoRepository<Category, Integer> {

}
