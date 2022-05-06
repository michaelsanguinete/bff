package com.compass.catalog.service;

import javax.validation.Valid;

import com.compass.catalog.dto.VariationDto;
import com.compass.catalog.dto.VariationFormDto;

public interface VariationService {
	
	void saveVariation(@Valid VariationFormDto body);

    VariationDto uptadeVariation(@Valid VariationFormDto body, Integer id);

    void deleteVariation(Integer id);

}
