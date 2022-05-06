package com.compass.catalog.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private int status;
	private String message;

	public ExceptionResponse(int status) {
		this.status = status;
	}

}
