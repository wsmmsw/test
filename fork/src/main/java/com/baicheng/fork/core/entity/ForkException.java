package com.baicheng.fork.core.entity;

@SuppressWarnings("serial")
public class ForkException extends Exception {

	public ForkException() {
		super();
	}

	public ForkException(String message) {
		super(message);
	}

	public ForkException(String message, Throwable cause) {
		super(message, cause);
	}

	public ForkException(Throwable cause) {
		super(cause);
	}

	protected ForkException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
