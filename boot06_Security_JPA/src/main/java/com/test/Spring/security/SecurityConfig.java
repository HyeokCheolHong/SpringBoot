package com.test.Spring.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.extern.java.Log;

@Log
@EnableWebSecurity // 스프링 웹 시큐리티로 인식되게함
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// 스프링 웹 시큐리티 설정을 담당하는 WebSecurityConfigurerAdapter 클래스 상속을 받는다
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		super.configure(http);
	}
	

}
