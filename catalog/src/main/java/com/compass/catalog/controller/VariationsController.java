package com.compass.catalog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.catalog.dto.VariationDto;
import com.compass.catalog.dto.VariationFormDto;
import com.compass.catalog.service.VariationService;

@RestController
@RequestMapping("/v1/variations")
public class VariationsController {

	@Autowired
	private VariationService variationService;

	@PostMapping
	@Transactional
	public ResponseEntity<VariationDto> saveVariation(@Valid @RequestBody VariationFormDto body) {
		variationService.saveVariation(body);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/:{id}")
	@Transactional
	public ResponseEntity<VariationDto> uptadeVariation(@Valid @RequestBody VariationFormDto body,
			@PathVariable Integer id) {
		VariationDto variationDTO = variationService.uptadeVariation(body, id);
		return ResponseEntity.ok(variationDTO);
	}

	@DeleteMapping("/variations/:id")
	@Transactional
	public ResponseEntity<?> deleteVariation(@PathVariable Integer id) {
		variationService.deleteVariation(id);
		return ResponseEntity.ok().build();
	}

}
