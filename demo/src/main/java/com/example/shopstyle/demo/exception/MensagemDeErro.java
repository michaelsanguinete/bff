package com.example.shopstyle.demo.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MensagemDeErro {

	private int statusCode;
	private Date timestamp;
	private String message;
	private String description;

}
