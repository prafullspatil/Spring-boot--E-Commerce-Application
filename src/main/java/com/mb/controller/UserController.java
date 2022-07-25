package com.mb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mb.entity.User;
import com.mb.model.SignUpModel;
import com.mb.response.SuccResponse;
import com.mb.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController
{

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	// @GetMapping("/all")
	// public String checkAllAccess()
	// {
	// return "All Access Area";
	// }
	//
	// @GetMapping("/user")
	// @PreAuthorize("hasRole('ROLE_USER')")
	// public String getUserAccess()
	// {
	// return "authenticated user Access Area";
	// }
	//
	// @GetMapping("/admin")
	// @PreAuthorize("hasRole('ROLE_ADMIN')")
	// public String getAdminAccess()
	// {
	// return "authenticated admin access Area";
	// }

	@PreAuthorize("hasRole('ROLE_USER')")
	@PutMapping("/update-user/{id}")
	public ResponseEntity<SuccResponse> updateUser(@PathVariable long id,
			@RequestBody SignUpModel signUpModel)
	{
		SuccResponse responseModel = SuccResponse.getInstance();
		responseModel.setData(userService.updateUser(id, signUpModel));
		responseModel.setMessage("User Updated Successfully");
		responseModel.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<SuccResponse>(responseModel, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/user-details/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") long id)
	{

		User user = userService.getUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

}
