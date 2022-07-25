package com.mb.service;

import com.mb.entity.User;
import com.mb.model.SignInModel;
import com.mb.model.SignUpModel;

public interface UserService
{
	public User signUpUser(SignUpModel userSignup);

	public User signUpAdmin(SignUpModel adminSignup);

	Object signIn(SignInModel userLogin);

	// public void initRolesAndUser();
	//
	// public String getEncodedPassword(String password);

	User updateUser(long id, SignUpModel signUpModel);

	// User Details
	User getUserById(long id);

}
