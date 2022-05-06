package com.compass.catalog.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.catalog.dto.VariationDto;
import com.compass.catalog.dto.VariationFormDto;
import com.compass.catalog.entity.Product;
import com.compass.catalog.entity.Variation;
import com.compass.catalog.exception.ExceptionResponse;
import com.compass.catalog.repository.ProductRepository;
import com.compass.catalog.repository.VariationRepository;

@Service
public class VariationServiceImpl implements VariationService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private VariationRepository variationRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void saveVariation(@Valid VariationFormDto body) {
		try {
			Variation variation = this.modelMapper.map(body, Variation.class);
			List<Variation> allVariations = variationRepository.findAll();

			if ((allVariations.size() - 1 >= 0)) {
				Variation variationMaxId = allVariations.get(allVariations.size() - 1);
				variation.setId(variationMaxId.getId() + 1);
			} else {
				variation.setId(1);
			}

			int productId = body.getProduct_id();
			Optional<Product> prod = this.productRepository.findById(productId);
			if (prod.isPresent()) {
				prod.get().setVariation(variation);
				this.productRepository.save(prod.get());
			} else {
				throw new ExceptionResponse(404, "Product Not Found");

			}
			this.variationRepository.save(variation);
		} catch (Exception e) {
			throw new ExceptionResponse(400, e.getMessage());
		}
	}

	@Override
	public VariationDto uptadeVariation(@Valid VariationFormDto body, Integer id) {
		try {
			Optional<Variation> var = this.variationRepository.findById(id);

			if (var.isPresent()) {
				var.get().setColor(body.getColor());
				var.get().setQuantity(body.getQuantity());
				var.get().setPrice(body.getPrice());
				var.get().setSize(body.getSize());

				int productId = body.getProduct_id();
				Optional<Product> oProduct = this.productRepository.findById(productId);
				if (oProduct.isPresent()) {
					var.get().setProduct_id(body.getProduct_id());
					oProduct.get().setVariation(var.get());
					this.productRepository.save(oProduct.get());
				}

				this.variationRepository.save(var.get());
			} else {
				throw new ExceptionResponse(404, "Variation Not Found");

			}

			return modelMapper.map(var.get(), VariationDto.class);
		} catch (Exception e) {
			throw new ExceptionResponse(400, e.getMessage());

		}
	}

	@Override
	public void deleteVariation(Integer id) {
		try {
			Optional<Variation> oVariation = this.variationRepository.findById(id);
			if (oVariation.isPresent()) {
				this.variationRepository.deleteById(id);
			} else {
				throw new ExceptionResponse(404, "Variation Not Found");

			}
		} catch (Exception e) {
			throw new ExceptionResponse(400, e.getMessage());

		}
	}

}
