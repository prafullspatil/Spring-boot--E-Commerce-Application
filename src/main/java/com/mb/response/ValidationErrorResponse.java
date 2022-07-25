package com.mb.response;

public class ValidationErrorResponse
{
	private String fieldName;
	private String message;

	public ValidationErrorResponse(String fieldName, String message)
	{
		super();
		this.fieldName = fieldName;
		this.message = message;
	}

	public String getFieldName()
	{
		return fieldName;
	}

	public String getMessage()
	{
		return message;
	}
}
