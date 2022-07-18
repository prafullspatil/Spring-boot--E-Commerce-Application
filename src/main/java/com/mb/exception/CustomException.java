package com.mb.exception;

public class CustomException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// private final int errorCode;

	public CustomException(String message, Throwable t)
	{
		super(message, t);
		// this.errorCode = errorCode;
	}

	public CustomException(String message)
	{
		super(message);
		// this.errorCode = errorCode;
	}

	// public int getErrorCode()
	// {
	// return errorCode;
	// }
}
