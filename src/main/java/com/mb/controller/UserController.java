package com.mb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController
{

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/all")
	public String checkAllAccess()
	{
		return "All Access Area";
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String getUserAccess()
	{
		return "authenticated user Access Area";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String getAdminAccess()
	{
		return "authenticated admin access Area";
	}

}
