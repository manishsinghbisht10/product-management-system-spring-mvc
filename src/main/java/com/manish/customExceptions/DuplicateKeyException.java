package com.manish.customExceptions;

public class DuplicateKeyException extends Exception {

	private static final long serialVersionUID = 1L;

	public DuplicateKeyException(String message) {
		super(message);
	}

}
