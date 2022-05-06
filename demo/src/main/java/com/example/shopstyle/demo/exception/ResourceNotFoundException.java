package com.example.shopstyle.demo.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private static String mensagem = "Resource not found: ";

    public ResourceNotFoundException(String msg) {
        super(mensagem + msg);
    }

}
