package com.augusto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Livro Não Encontrado")
public class BookNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public BookNotFoundException () {
		super("Operação não Suportada");
	}

}
