package com.uestc.newhelp.exception;

public class NoSuchStudentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4892473635713510802L;
	public NoSuchStudentException(String message) {
		super(message);
	}
}
