package com.example.shopstyle.demo.exception;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldErrorMessage {

	private int statusCode;
	private Date timestamp;
	private List<FieldErrorDTO> fieldErrors;
	private String description;

}
