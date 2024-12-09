package net.dau.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.daum.vo.SampleVO;

@RestController
public class SampleController {
	
	@GetMapping("/begin_Jpa")
	public String startJpa() {
		return "스프링 부트와 JPA 시작";
		// 문자열 객체 반환
	}
	
	@GetMapping("/sample")
	public SampleVO sample() {
		// @RestController에서 리턴타입이 SampleVO 빈타입이면 JSON데이터의 키이름은 해당빈클래스의 변수명이 된다.
		
		SampleVO vo = new SampleVO();
		
		vo.setVal01("val01 입니다.");
		vo.setVal02("val02 입니다.");
		vo.setVal03("val03 입니다.");
		
		System.out.println(vo.toString());
		// val03은 배제되고 출력
		return vo;
	}
}
