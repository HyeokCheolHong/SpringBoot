package net.daum.vo;

import lombok.Getter;
import lombok.Setter;

@Setter // lombok.jar에 의한 setter()메서드 자동 추가
@Getter // lombok.jar에 의한 getter()메서드 자동 추가

public class BoardVO {
// 스프링 mvc게시판 빈클래스이다.
// 빈클래스 변수명과 네임 파라미터이름과 해당 테이블(tbl_board) 컬럼명을 같게 한다.
	private int bno; // 게시판 번호
	private String writer; // 작성자
	private String title; // 제목
	private String content; // 글 내용
	private int viewcnt; // 조회수
	private String regdate; // 등록날짜
	
	// 페이징(쪽 나누기) 관련 변수
	private int startrow; // 시작 행 번호
	private int endrow; // 끝행 번호
	
	// 현재 setter() getter()가 없다.
	// Lombok lib로 처리하자
	
}
