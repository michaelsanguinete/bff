package com.compass.catalog.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	
	public int id;

    private String name;

    private String description;

    private Boolean active;

    private List<VariationDto> variations;

}
