package com.compass.catalog.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFormDto {
	
	@NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Boolean active;

    @NotNull
    @NotEmpty
    private List<String> category_ids;

}
