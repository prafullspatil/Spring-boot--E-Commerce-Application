package com.mb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.mb.entity.CustomUserDetails;
import com.mb.entity.User;
import com.mb.repository.UserRepository;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService
{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = null;

		user = userRepository.findByEmail(username);
		if (user != null)
		{
			return CustomUserDetails.build(user);
		}
		else
		{
			throw new UsernameNotFoundException(username + " not found");
		}
	}

}
