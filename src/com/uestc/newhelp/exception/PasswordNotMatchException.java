package com.uestc.newhelp.exception;

public class PasswordNotMatchException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5644029318761575604L;

	public PasswordNotMatchException(String message) {
		super(message);
	}
}
