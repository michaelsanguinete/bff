package com.compass.catalog.exception;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(value = { ExceptionResponse.class })
	public ResponseEntity<ErrorMessage> exceptionResponse(ExceptionResponse e, WebRequest request) {
		return new ResponseEntity<ErrorMessage>(new ErrorMessage(e.getMessage(), e.getCause()),
				HttpStatus.valueOf(e.getStatus()));
	}

	@ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, WebRequest request) {
		return new ErrorMessage(e.getMessage(), e.getCause());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception) {
		List<ErroDeFormularioDto> dto = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(), mensagem);
			dto.add(erro);
		});

		return dto;
	}

}
