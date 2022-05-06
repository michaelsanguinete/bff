package com.compass.catalog.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.compass.catalog.entity.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VariationDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;

    @NotNull
    private String color;

    @NotNull
    private Size size;

    @NotNull
    private double price;

    @NotNull
    private int quantity;

    @NotNull
    private int product_id;

}
