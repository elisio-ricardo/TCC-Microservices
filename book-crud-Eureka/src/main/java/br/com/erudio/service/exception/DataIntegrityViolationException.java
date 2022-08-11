package br.com.erudio.service.exception;

import br.com.erudio.exceptions.StandardError;
import org.springframework.http.ResponseEntity;

public class DataIntegrityViolationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	//public DataIntegrityViolationException(String message, Throwable cause) {super(message, cause);}
	public DataIntegrityViolationException(String message) {
		super(message);
	}
}
