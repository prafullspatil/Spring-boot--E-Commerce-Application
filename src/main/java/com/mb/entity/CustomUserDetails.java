package com.mb.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomUserDetails implements UserDetails
{
	private Long id;

	private String email;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public CustomUserDetails(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities)
	{
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static CustomUserDetails build(User user)
	{
		List<GrantedAuthority> authority = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

		return new CustomUserDetails(user.getId(), user.getEmail(), user.getPassword(), authority);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{

		return this.authorities;
	}

	@Override
	public String getPassword()
	{
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername()
	{
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		// TODO Auto-generated method stub
		return true;
	}

}
