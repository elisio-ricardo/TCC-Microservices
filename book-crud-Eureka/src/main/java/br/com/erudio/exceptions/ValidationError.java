package br.com.erudio.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Long timestamp, Integer status, String error) {
		super(timestamp, status, error);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addErrors(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}

}
