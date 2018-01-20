package com.uestc.newhelp.dto;


public class Result<T> {
	private boolean success;
	private T data;
	private String message;

	public Result() {
		super();
	}
	
	public Result(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}
	
	public Result(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public Result(boolean success, T data, String message) {
		super();
		this.success = success;
		this.data = data;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
