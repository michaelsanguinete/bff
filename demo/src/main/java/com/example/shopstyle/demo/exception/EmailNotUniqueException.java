package com.example.shopstyle.demo.exception;

public class EmailNotUniqueException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	static String mensagem = "E-mail address already exists: ";

    public EmailNotUniqueException(String msg) {
        super(mensagem + msg);
    }

}
