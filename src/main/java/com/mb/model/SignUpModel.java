package com.mb.model;

import com.mb.validation.ValidPassword;

public class SignUpModel
{

	private String userName;

	@ValidPassword
	private String password;

	private String email;

	private String mobileNo;

	public String getUserName()
	{
		return userName;
	}

	public String getPassword()
	{
		return password;
	}

	public String getEmail()
	{
		return email;
	}

	public String getMobileNo()
	{
		return mobileNo;
	}

}
