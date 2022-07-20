package com.mb.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mb.model.SignInModel;
import com.mb.model.SignUpModel;
import com.mb.response.SuccResponse;
import com.mb.service.UserService;

@RestController
@RequestMapping("/api")
public class AuthController
{
	@Autowired
	private UserService userService;

	@PostMapping("/signup/user")
	public ResponseEntity<SuccResponse> signUpUser(@Valid @RequestBody SignUpModel model)
	{
		SuccResponse responseModel = SuccResponse.getInstance();
		responseModel.setData(userService.signUpUser(model));
		responseModel.setMessage("User Registered Successfully");
		responseModel.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<SuccResponse>(responseModel, HttpStatus.ACCEPTED);
	}

	@PostMapping("/signup/admin")
	public ResponseEntity<SuccResponse> signUpAdmin(@Valid @RequestBody SignUpModel model)
	{
		SuccResponse responseModel = SuccResponse.getInstance();
		responseModel.setData(userService.signUpAdmin(model));
		responseModel.setMessage("User Registered Successfully");
		responseModel.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<SuccResponse>(responseModel, HttpStatus.ACCEPTED);
	}

	@PostMapping("/signin")
	public ResponseEntity<SuccResponse> signIn(@Valid @RequestBody SignInModel login)
	{
		SuccResponse model = SuccResponse.getInstance();
		model.setData(userService.signIn(login));
		model.setMessage("User login successfull");
		model.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<SuccResponse>(model, HttpStatus.ACCEPTED);
	}
}
