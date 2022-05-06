package com.example.shopstyle.demo.exception;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {
	
	@Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public MensagemDeErro resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return new MensagemDeErro(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public FieldErrorMessage methodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        List<FieldErrorDTO> dto = new ArrayList<>();

        fieldErrors.forEach(fieldError -> {
            String field = fieldError.getField();
            String error = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

            dto.add(new FieldErrorDTO(field, error));
        });

        dto.sort(Comparator.comparing(FieldErrorDTO::getField));

        return new FieldErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                dto,
                request.getDescription(false));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public MensagemDeErro illegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return new MensagemDeErro(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getLocalizedMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public MensagemDeErro constraintViolationException(ConstraintViolationException ex, WebRequest request) {
        return new MensagemDeErro(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getCause().getLocalizedMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public MensagemDeErro httpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        return new MensagemDeErro(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getRootCause().getLocalizedMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler(EmailNotUniqueException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public MensagemDeErro emailNotUniqueException(EmailNotUniqueException ex, WebRequest request) {
        return new MensagemDeErro(
                HttpStatus.CONFLICT.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }

}
