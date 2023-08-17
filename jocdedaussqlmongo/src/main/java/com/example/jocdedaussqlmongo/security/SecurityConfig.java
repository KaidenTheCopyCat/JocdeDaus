package com.example.jocdedaussqlmongo.security;

import java.security.Key;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.jsonwebtoken.security.Keys;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().requestMatchers("/players/**").authenticated().anyRequest().permitAll()
				.and().httpBasic();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return authenticationManagerBean();
	}

	@Bean
	public Key jwtSecretKey() {
		return Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
	}

}
