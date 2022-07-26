package com.mb.constant;

public class JwtConstants
{
	private JwtConstants()
	{
		throw new IllegalStateException("JWT Constants class.can't initiate");
	}

	public static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER_STRING = "Authorization";

}
