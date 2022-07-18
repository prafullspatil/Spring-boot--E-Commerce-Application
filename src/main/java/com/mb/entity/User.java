package com.mb.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "user_name", unique = true)
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "mobile_no", unique = true)
	private String mobileNo;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
	private Set<Role> roles;

	public User()
	{

	}

	public User(long id, String userName, String password, String email, String mobileNo)
	{
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.mobileNo = mobileNo;

	}

	public long getId()
	{
		return id;
	}

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

	public Set<Role> getRoles()
	{
		return roles;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public void setMobileNo(String mobileNo)
	{
		this.mobileNo = mobileNo;
	}

	public void setRoles(Set<Role> roles)
	{
		this.roles = roles;
	}

}
