package com.mb.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class SignInModel
{
	@Email
	@NotEmpty
	private String email;
	@NotBlank
	private String password;

	public String getEmail()
	{
		return email;
	}

	public String getPassword()
	{
		return password;
	}

}
