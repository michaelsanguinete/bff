package com.compass.catalog.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.catalog.dto.ProductDto;
import com.compass.catalog.dto.ProductFormDto;
import com.compass.catalog.entity.Product;
import com.compass.catalog.entity.Variation;
import com.compass.catalog.exception.ExceptionResponse;
import com.compass.catalog.repository.ProductRepository;
import com.compass.catalog.repository.VariationRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private VariationRepository variationRepository;

	@Override
	public void saveProduct(@Valid ProductFormDto body) {
		try {
			Product product = this.modelMapper.map(body, Product.class);
			List<Product> allProduct = productRepository.findAll();
			boolean active = true;
			if ((allProduct.size() - 1 >= 0)) {

				Collections.sort(allProduct, (p1, p2) -> {
					return p2.getId() - p1.getId();
				});
				Product productMaxId = allProduct.get(0);
				product.setId(productMaxId.getId() + 1);
			} else {
				product.setId(1);
			}
			
			if (active) {

				Optional<Variation> variation = variationRepository.findBy_Product_id(product.getId());
				if (!variation.isPresent()) {
					productRepository.save(product);
					System.out.println(product);

				} else {
					productRepository.save(product);
					System.out.println(product);
				}
			} else {
				throw new ExceptionResponse(400, "Produto não está ativo");
			}
		} catch (Exception e) {
			throw new ExceptionResponse(400, e.getMessage());
		}
	}

	@Override
	public List<ProductDto> listAllProducts() {
		try {
			List<Product> products = productRepository.findAll();
			if (products.isEmpty()) {
				throw new ExceptionResponse(404, "Lista vazia");
			} else {
				List<ProductDto> productDTOs = new ArrayList<>();
				products.forEach((pr) -> {
					productDTOs.add(modelMapper.map(pr, ProductDto.class));
				});

				return productDTOs;
			}
		} catch (Exception e) {
			throw new ExceptionResponse(400, e.getMessage());
		}

	}

	@Override
	public ProductDto getProduct(int id) {
		try {
			Optional<Product> oProduct = this.productRepository.findById(id);
			if (oProduct.isPresent()) {
				return this.modelMapper.map(productRepository.findById(id), ProductDto.class);

			} else {

				throw new ExceptionResponse(404, "Product not found");
			}
		} catch (Exception e) {
			throw new ExceptionResponse(400, e.getMessage());
		}
	}

	@Override
	public ProductDto uptadeProduct(@Valid ProductFormDto body, Integer id) {
		try {
			Optional<Product> oProduct = this.productRepository.findById(id);
			if (oProduct.isPresent()) {

				oProduct.get().setName(body.getName());
				oProduct.get().setDescription(body.getDescription());
				oProduct.get().setActive(body.getActive());
				this.productRepository.save(oProduct.get());
				return modelMapper.map(oProduct.get(), ProductDto.class);
			} else {
				throw new ExceptionResponse(404, "Product Not Found");

			}
		} catch (Exception e) {
			throw new ExceptionResponse(400, e.getMessage());
		}

	}

	@Override
	public void deleteProduct(int id) {
		try {
			Optional<Product> oProduct = this.productRepository.findById(id);
			if (oProduct.isPresent()) {
				this.productRepository.deleteById(id);
			} else {
				throw new ExceptionResponse(404, "Product Not Found");

			}
		} catch (Exception e) {
			throw new ExceptionResponse(400, e.getMessage());
		}
	}


}
