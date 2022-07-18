package com.mb.service;

import com.mb.entity.User;
import com.mb.model.SignInModel;
import com.mb.model.SignUpModel;

public interface UserService
{
	User signUpUser(SignUpModel userSignup);

	User signUpAdmin(SignUpModel adminSignup);

	Object signIn(SignInModel userLogin);
}
