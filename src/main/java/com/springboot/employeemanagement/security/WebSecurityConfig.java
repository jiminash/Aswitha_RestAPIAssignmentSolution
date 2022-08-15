package com.springboot.employeemanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.springboot.employeemanagement.service.UserDetailServiceImpl;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Bean
	public UserDetailsService getMyUserDetailsService() {
		return new UserDetailServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider getdDaoAuthenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(getMyUserDetailsService());
		auth.setPasswordEncoder(getBCryptPasswordEncoder());
		return auth;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder mgr) throws Exception {
		mgr.authenticationProvider(getdDaoAuthenticationProvider());
	}
	
	

	@Override
	public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/h2-console/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
				
		http.authorizeRequests().antMatchers("/api/employees/add").hasAuthority("ADMIN")
		.antMatchers("/","/api/employees/update","/api/employees/sort","/api/employees/search","/api/employees/list"
				,"/api/employees/get","/api/employees/delete").hasAnyAuthority("ADMIN","USER")
//		.antMatchers("/api/employees/saverole","/api/employees/saveuser").permitAll()
		.anyRequest().authenticated().and().httpBasic()
		.and().cors().and().csrf().disable();
		
	}

	
	
	

}
