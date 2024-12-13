package net.daum.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PageVO { // 페이징 관련 빈클래스
	
	private int startrow; // 시작행
	private int endrow; // 끝행 번호
	
}
