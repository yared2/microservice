package com.appsdeveloperblog.photoapp.api.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.appsdeveloperblog.photoapp.api.user.service.UserService;

@Configuration
@EnableWebSecurity
public class Websecurity extends WebSecurityConfigurerAdapter{
	
	
	private UserService userService;
	
	private Environment environment;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public Websecurity(UserService userService,BCryptPasswordEncoder bCryptPasswordEncoder,Environment environment) {
		this.userService=userService;
		this.bCryptPasswordEncoder =bCryptPasswordEncoder;
		this.environment = environment;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable();
		http.authorizeHttpRequests()
		.antMatchers("/users/**").permitAll()
		.and().addFilter(getAuthenticationFilter());
	
		http.headers().frameOptions().disable();
	}
	
	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(userService,environment,authenticationManager());
		//authenticationFilter.setAuthenticationManager(authenticationManager());
		authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.ur.path"));
		return authenticationFilter;
	}
	
	protected void configure(AuthenticationManagerBuilder auth ) throws Exception{
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}

}
