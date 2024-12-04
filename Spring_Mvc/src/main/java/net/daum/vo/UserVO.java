package net.daum.vo;

import lombok.Data;

// 2024-12-04 스프링 AOP와 트랜잭션 실습을 위한 코드

@Data
public class UserVO {
	// tbl_user테이블의 컬럼명과 일치하는 빈클래스 변수명을 정의
	
	private String uid2;
	private String upw;
	private String uname;
	private int upoint;
}
