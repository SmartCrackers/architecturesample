package com;

public class DeskAppWebException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public int code;
	
	public DeskAppWebException() {
		super();
	}

	public DeskAppWebException(String message, Throwable cause, boolean enableSuppression,
                               boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DeskAppWebException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeskAppWebException(String message) {
		super(message);
	}
	
	public DeskAppWebException(String message, int code) {
		super(message);
		this.code = code;
	}

	public DeskAppWebException(Throwable cause) {
		super(cause);
	}

}
