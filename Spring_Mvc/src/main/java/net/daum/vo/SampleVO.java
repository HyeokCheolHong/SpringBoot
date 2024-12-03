package net.daum.vo;

import lombok.Data;


@Data // @Data는 lombok 라이브러리로 setter, getter, toString, equals, hashCode, 기본생성자, canEquals메서드 까지 자동생성
public class SampleVO {
// 2024-12-02 REST API 연습을 위한 연습용 코드 작성
	
	private int mno;
	// 변수명이 JSON데이터의 키이름이 된다.
	
	private String firstName;
	private String lastName;
}
