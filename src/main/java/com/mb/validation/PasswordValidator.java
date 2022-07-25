package com.mb.validation;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String>
{

	private String regEx = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context)
	{
		if (password.isBlank())
		{
			return true;
		}

		Pattern pat = Pattern.compile(regEx);
		return pat.matcher(password).matches();

	}
}
