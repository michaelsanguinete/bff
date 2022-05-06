package com.compass.catalog.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.compass.catalog.entity.Variation;

public interface VariationRepository extends MongoRepository<Variation, Integer> {

	@Query("{'product_id' :?0}")
	Optional<Variation> findBy_Product_id(int id);
}
