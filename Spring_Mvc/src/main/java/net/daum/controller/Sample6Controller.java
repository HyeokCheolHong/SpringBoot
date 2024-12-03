package net.daum.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.daum.vo.SampleVO;

// 2024-12-02 REST API를 연습하기 위한 연습용 코드

@RestController
// @RestController 애너테이션으로 뷰페이지를 직접 만들지 않고도 원하는 문자열 객체, JSON, XMl 데이터를 만들 수 있다.

@RequestMapping("/rest")
// Rest라는 컨트롤러 자체 매핑주소 등록
public class Sample6Controller {
	
	@GetMapping("/beginRest")
	public String begin() {
		return "Rest 시작";
	};
	
	@RequestMapping("/sendVO")
	public SampleVO sendVO() {
		// 메서드 리턴타입이 SampleVo 빈타입이면 해당 클래스 변수명이 JSON데이터의 키 이름이 된다.
		
		SampleVO vo = new SampleVO();
		vo.setFirstName("홍");
		vo.setLastName("길동");
		vo.setMno(7);
		
		return vo;
	};
	
	@GetMapping("/sendList")
	public List<SampleVO> sendList() {
		List<SampleVO> list = new ArrayList<>();
		
		for(int i = 1; i <= 7; i++) {
			SampleVO vo = new SampleVO();
			vo.setMno(i);
			vo.setFirstName("세종");
			vo.setLastName("대왕");
			
			list.add(vo);
		}
		return list;
		/* 
		 * [{"mno":1,"firstName":"세종","lastName":"대왕"},
		 * {"mno":2,"firstName":"세종","lastName":"대왕"},
		 * {"mno":3,"firstName":"세종","lastName":"대왕"},
		 * {"mno":4,"firstName":"세종","lastName":"대왕"},
		 * {"mno":5,"firstName":"세종","lastName":"대왕"},
		 * {"mno":6,"firstName":"세종","lastName":"대왕"},
		 * {"mno":7,"firstName":"세종","lastName":"대왕"}]
		 */
	};
	
	// 키, 값 쌍의 컬렉션 Map 타입 JSON
	@GetMapping("/sendMap")
	public Map<Integer, SampleVO> sendMap(){
		Map<Integer, SampleVO> map = new HashMap<>();
		
		for(int i = 1; i <= 5; i++) {
			SampleVO vo = new SampleVO();
			
			vo.setMno(i);
			vo.setFirstName("강");
			vo.setLastName("감찬");
			
			map.put(i, vo);
			// 커렉션에 키, 값 쌍으로 저장
		}
		
		return map; 
		/* 
		 * {"1":{"mno":1,"firstName":"강","lastName":"감찬"},
		 * "2":{"mno":2,"firstName":"강","lastName":"감찬"},
		 * "3":{"mno":3,"firstName":"강","lastName":"감찬"},
		 * "4":{"mno":4,"firstName":"강","lastName":"감찬"},
		 * "5":{"mno":5,"firstName":"강","lastName":"감찬"}}
		 */
	};
	
	@RequestMapping("/sendError")
	public ResponseEntity<Void> sendError() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		/*
		 * @RestController는 별도의 뷰페이지를 만들지 않고 Rest 프로그램을 개발하다 보니,
		 * 결과 데이터에 대한 예외적인 오류 상황이 발생할 수 있다.
		 * 이런 경우 스프링에서 제공하는 ResponseEntity API를 사용하면 개발자가 나쁜 상태코드인 404, 500도 함께 브라우저로 전송되기 때문에 좀더 세밀한 에러 제어를 할 수 있다.
		 * 여기서 나쁜상태 코드 BAD_REQUEST인 400이 브라우저로 전송된다.
		 */
	};
	
	// 정상적인 JSON데이터와 404(자원을 찾이 못했을 때) 나쁜 상태코드가 함께 브라우저로 전송
	@GetMapping("/sendErrorNot")
	public ResponseEntity<List<SampleVO>> sendListNot() {
		List<SampleVO> list = new ArrayList<>();
		
		for(int i = 1; i<=3; i++) {
			SampleVO vo = new SampleVO();
			vo.setMno(i);
			vo.setFirstName("악동");
			vo.setLastName("강호동");
			
			list.add(vo);
		}
		return new ResponseEntity<List<SampleVO>>(list, HttpStatus.NOT_FOUND);
		// NOT_FOUND는 404 의미
	};
	
	
}
