package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


// 2024-12-11 Spring Security 실습

@Controller
@Log
public class SampleController {
	
	@GetMapping("/")
	public String index() {
		log.info("index 이다.");
	    return "index"; // 뷰페이지 경로가 WEB-INF/views/indwx.jps
	}
	
	@RequestMapping("/guest")
	public void forGuest() {
		// 리턴타입이 없는 void형이면 매핑주소가 뷰페이지 파일명이 된다.
		// 뷰페이지 경로는 /WEB-INF/views/guest.jsp
		log.info("guest");
	}
	
	@RequestMapping("/manager")
	public void forManager() {
		log.info("manager 이다.");
	}
	
	@RequestMapping("/admin")
	public void forAdmin() {
		log.info("admin이다");
	}
	
}
