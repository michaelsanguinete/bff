package com.compass.catalog.service;

import java.util.List;

import javax.validation.Valid;

import com.compass.catalog.dto.CategoryDto;
import com.compass.catalog.dto.CategoryFormDto;
import com.compass.catalog.dto.VariationDto;

public interface CategoryService {
	
	void saveCategory(@Valid CategoryFormDto body);

    List<CategoryDto> listAllCategories();

    CategoryDto getCategory(Integer id);

    CategoryDto uptadeCategory(Integer id, @Valid CategoryFormDto body);

    void deleteCategory(Integer id);
    
    


}
