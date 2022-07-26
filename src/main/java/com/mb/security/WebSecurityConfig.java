package com.mb.security;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.mb.jwt.AuthEntryPointJwt;
import com.mb.jwt.AuthTokenFilter;
import com.mb.service.impl.CustomUserDetailsServiceImpl;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private CustomUserDetailsServiceImpl userdetailService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ModelMapper modelMapper()
	{
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		return mapper;
	}

	@Bean
	public AuthTokenFilter authTokenFilter()
	{
		return new AuthTokenFilter();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userdetailService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{

		http.cors().and().csrf().disable()
				// .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				// .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()

				.antMatchers("/api/signin").permitAll()

				.antMatchers("/swagger-ui/**").permitAll()
				.antMatchers("/api/signup/admin").permitAll()
				.antMatchers("/api/signup/user").permitAll()
				.anyRequest().authenticated();

		http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	// @Override
	// public void configure(WebSecurity web) throws Exception
	// {
	// web.ignoring().antMatchers("/v2/api-docs",
	// "/configuration/ui",
	// "/swagger-resources/**",
	// "/configuration/security",
	// "/swagger-ui.html",
	// "/webjars/**");
	// }
}
