package net.daum.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // Lombok lib에서 제공하는 애너테이션으로 class에 선언된 모든 필드(속성)를 매게변수(전달인자)로 받는 생성자를 자동으로 생성
// 생성된 생성자가 5개라면 오버로딩 5개를 받는 생성자를 생성

//2024-12-10 thymeLeaf 실습

public class MemberVO {
	
	private int mno;
	private String mid;
	private String mpw;
	private String mname;
	private Timestamp regdate;
	// 자동으로 MemberVO(int mno, String mid, String mpw, String mname ,Timestamp regdate) 를 받는 생성자를 생성
}
