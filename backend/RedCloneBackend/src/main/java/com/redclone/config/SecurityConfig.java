package com.redclone.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import com.redclone.security.JWTAuthFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JWTAuthFilter jwtAuthenticationFilter;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.cors().and().csrf().disable()
		.authorizeRequests()
		.antMatchers("/api/auth/**")
		.permitAll()
		.antMatchers(HttpMethod.GET,"/api/subred/**")
		.permitAll()
		.antMatchers(HttpMethod.GET,"/api/subred")
		.permitAll()
		.antMatchers(HttpMethod.GET,"/api/topic/**")
		.permitAll()
		.antMatchers(HttpMethod.GET,"/api/topic")
		.permitAll()
		.antMatchers(HttpMethod.GET,"/api/posts/**")
		.permitAll()
		.antMatchers(HttpMethod.GET,"/api/posts/")
		.permitAll()
		.antMatchers("/v2/api-docs","/configuration/ui","/swagger-resources/**","/configuration/security","/swagger-ui.html","/webjars/**")
		.permitAll()
		.anyRequest()
		.authenticated();
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authManBuilder) throws Exception
	{
		authManBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	@Bean
	PasswordEncoder passwordEncoder()
	{
	   return new BCryptPasswordEncoder();	
	}

}
