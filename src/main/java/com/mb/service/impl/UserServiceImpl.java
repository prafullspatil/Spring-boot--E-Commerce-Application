package com.mb.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.mb.entity.CustomUserDetails;
import com.mb.entity.Role;
import com.mb.entity.User;
import com.mb.exception.ResourceAlreadyExistsException;
import com.mb.jwt.JwtUtil;
import com.mb.model.SignInModel;
import com.mb.model.SignUpModel;
import com.mb.repository.RoleRepository;
import com.mb.repository.UserRepository;
import com.mb.service.UserService;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public User signUpUser(SignUpModel userSignup)
	{
		if (userRepository.findByEmail(userSignup.getEmail()) != null)
		{
			throw new ResourceAlreadyExistsException("Email already exists with address " + userSignup.getEmail());
		}
		else
		{
			Set<Role> roles = new HashSet<Role>();

			User user = new User();

			Role userRole = roleRepository.findByName("ROLE_USER");

			roles.add(userRole);

			user = modelMapper.map(userSignup, User.class);

			user.setPassword(passwordEncoder.encode(userSignup.getPassword()));

			user.setRoles(roles);

			userRepository.save(user);

			return user;
		}
	}

	@Override
	public User signUpAdmin(SignUpModel adminSignup)
	{

		if (userRepository.findByEmail(adminSignup.getEmail()) != null)
		{
			throw new ResourceAlreadyExistsException("Email already exists with address " + adminSignup.getEmail());
		}
		else
		{
			Set<Role> roles = new HashSet<Role>();

			User user = new User();

			Role userRole = roleRepository.findByName("ROLE_ADMIN");

			roles.add(userRole);

			user = modelMapper.map(adminSignup, User.class);

			user.setPassword(passwordEncoder.encode(adminSignup.getPassword()));

			user.setRoles(roles);

			userRepository.save(user);

			return user;
		}
	}

	@Override
	public Object signIn(SignInModel userLogin)
	{

		Map<String, Object> data = new HashMap<String, Object>();

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtToken = jwtUtil.generateJwtToken(authentication);

		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		data.put("jwtToken", jwtToken);
		data.put("username", userDetails.getUsername());
		data.put("password", userDetails.getPassword());
		data.put("roles", roles);

		return data;
	}

	@Override
	public User updateUser(long id, SignUpModel signUpModel)
	{

		// Employee empDB = employeeRepository.findById(id).get();
		//
		// if (Objects.nonNull(employee.getEmployeeName())
		// && !"".equalsIgnoreCase(
		// employee.getEmployeeName()))
		// {
		// empDB.setEmployeeName(
		// employee.getEmployeeName());
		// }
		//
		// if (Objects.nonNull(
		// employee.getEmployeeEmail())
		// && !"".equalsIgnoreCase(
		// employee.getEmployeeEmail()))
		// {
		// empDB.setEmployeeEmail(
		// employee.getEmployeeEmail());
		// }
		//
		// if (Objects.nonNull(employee.getSalary())
		// && !"".equals(employee.getSalary()))
		// {
		// empDB.setSalary(
		// employee.getSalary());
		// }
		//
		// return employeeRepository.save(empDB);

		User oldUser = userRepository.findById(id).get();

		if (Objects.nonNull(signUpModel.getEmail())
				&& !"".equalsIgnoreCase(
						signUpModel.getEmail()))
		{
			oldUser.setEmail(
					signUpModel.getEmail());
		}

		if (Objects.nonNull(signUpModel.getMobileNo())
				&& !"".equals(signUpModel.getMobileNo()))
		{
			oldUser.setMobileNo(
					signUpModel.getMobileNo());
		}

		return userRepository.save(oldUser);

	}

	@Override
	public User getUserById(long id)
	{

		return userRepository.findUserById(id);

	}

}
