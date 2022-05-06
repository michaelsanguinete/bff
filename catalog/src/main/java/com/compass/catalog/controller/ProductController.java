package com.compass.catalog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.catalog.dto.ProductDto;
import com.compass.catalog.dto.ProductFormDto;
import com.compass.catalog.service.ProductService;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	@Transactional
	public ResponseEntity<?> saveProduct(@Valid @RequestBody ProductFormDto body) {
		productService.saveProduct(body);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping
	public ResponseEntity<List<ProductDto>> listAllProducts() {
		List<ProductDto> produtoDTO = productService.listAllProducts();
		return ResponseEntity.ok(produtoDTO);

	}

	@GetMapping("/:{id}")
	public ResponseEntity<ProductDto> getProduct(@PathVariable Integer id) {
		ProductDto productDTO = productService.getProduct(id);
		return ResponseEntity.ok(productDTO);
	}

	@PutMapping("/:{id}")
	@Transactional
	public ResponseEntity<ProductDto> uptadeProduct(@Valid @RequestBody ProductFormDto body, @PathVariable Integer id) {
		ProductDto productDTO = productService.uptadeProduct(body, id);
		return ResponseEntity.ok(productDTO);
	}

	@DeleteMapping("/:{id}")
	@Transactional
	public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
		productService.deleteProduct(id);
		return ResponseEntity.ok().build();
	}

}
