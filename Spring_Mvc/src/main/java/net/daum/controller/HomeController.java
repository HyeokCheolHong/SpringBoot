package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 2024-12-03 HomeController 비동기식 기본댓글작성 연습
/*
 * ReplyController.java에서 RestController로 백엔드서버 쪽 댓글 REST API 프로그램을 개발한 것은 비동기식 jQuery Ajax 뷰페이지 개발을 위한 일반 컨트롤러 클래스이다.
 */

@Controller
public class HomeController {
	
	// Ajax 댓글
	@RequestMapping("/test")
	public void test() {
		// 리턴타입이 없는 void형이면 매핑주소인 test가 뷰페이지 파일명이 된다.
	}
}
