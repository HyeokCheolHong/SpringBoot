package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login") // 사용자 로그인 페이지, login 매핑주소 등록
	private void login() {
		// 리턴타입이 없는 void형이면 매핑주소인 login이 뷰페이지 파일명이 된다.
	}
	
	@GetMapping("/accessDenied") // 403 접근금지 에러가 발생했을 경우
	public void accessDenied() {
		// 리턴타입이 없는 void형이면 매핑주소인 accessDenied가 뷰페이지 파일명이 된다.
	}
	
	@GetMapping("/logout") // 로그아웃 페이지
	public void logout() {
		
	}
	
}
