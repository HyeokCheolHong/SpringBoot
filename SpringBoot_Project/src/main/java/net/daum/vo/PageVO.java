package net.daum.vo;

import lombok.Getter;
import lombok.Setter;

// 2024-12-16 Spring Project 


@Setter
@Getter
public class PageVO {
// 페이징과 검색기능 관련 빈클래스
	
	// 페이징(쪽나누기) 관련 변수
	private int startrow; // 시작 행 번호
	private int endrow; // 끝 행 번호
	
	// 검색 관련 변수
	private String find_field; // 검색 필드
	private String find_name; // 검색어
}
