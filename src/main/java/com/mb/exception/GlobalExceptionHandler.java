package com.mb.exception;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.mb.response.ErrorResponse;
import com.mb.response.SuccResponse;
import com.mb.response.ValidationErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler
{

	@ExceptionHandler(value = ResourceAlreadyExistsException.class)
	public ErrorResponse ResourceNotFoundException(Exception ex)
	{
		return new ErrorResponse(new Date(), ex.getMessage(), ex.getLocalizedMessage(), HttpStatus.IM_USED.value(), "Resource Already Exists");
	}

	@ExceptionHandler(value = CustomException.class)
	public ErrorResponse customizedException(CustomException ex)
	{
		return new ErrorResponse(new Date(), ex.getMessage(), ex.getLocalizedMessage(), HttpStatus.CONFLICT.value(), "Bad Request");
	}

	// @ExceptionHandler(value = Exception.class)
	// public ErrorResponse internalServerException(Exception ex)
	// {
	// return new ErrorResponse(new Date(), ex.getMessage(), ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error");
	// }

	@ExceptionHandler(value = AccessDeniedException.class)
	public ErrorResponse accessedDeniedException(Exception ex)
	{
		return new ErrorResponse(new Date(), ex.getMessage(), ex.getLocalizedMessage(), HttpStatus.FORBIDDEN.value(), "Access Denied");
	}

	@ExceptionHandler(value = NullPointerException.class)
	public ErrorResponse nullPointerException(NullPointerException ex)
	{
		return new ErrorResponse(new Date(), ex.getMessage(), ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something Went wrong");
	}

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
	{
		List<ValidationErrorResponse> errorsValidation = ex.getBindingResult().getFieldErrors().stream()
				.map(err -> new ValidationErrorResponse(err.getField(), err.getDefaultMessage())).collect(Collectors.toList());
		SuccResponse model = new SuccResponse();

		return model.validationErrorsResponse("enter valid data ", errorsValidation);
	}

	// @ExceptionHandler(value = {InvalidCredentialsException.class})
	// @ResponseStatus(HttpStatus.BAD_REQUEST)
	// public ErrorResponse handleInvalidCredentialsException(InvalidCredentialsException ex)
	// {
	// return new ErrorResponse(new Date(), "Please enter correct credentials", ex.getMessage(), HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	// }

}
