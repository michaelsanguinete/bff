package com.compass.catalog.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.compass.catalog.entity.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VariationFormDto {

	@NotNull
	private String color;

	@NotNull
	private Size size;

	@NotNull
	private BigDecimal price;

	@NotNull
	private int quantity;

	@NotNull
	private int product_id;

}
