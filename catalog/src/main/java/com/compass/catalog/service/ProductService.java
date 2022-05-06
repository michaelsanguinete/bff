package com.compass.catalog.service;

import java.util.List;

import javax.validation.Valid;

import com.compass.catalog.dto.ProductDto;
import com.compass.catalog.dto.ProductFormDto;

public interface ProductService {
	
	void saveProduct(@Valid ProductFormDto body);

    List<ProductDto> listAllProducts();

    ProductDto getProduct(int id);

    ProductDto uptadeProduct(@Valid ProductFormDto body, Integer id);

    void deleteProduct(int id);

}
