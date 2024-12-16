package net.daum.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name="bbs") // bbs라는 테이블 생성
@EqualsAndHashCode(of="bbs_no")
//equals(), hashCode(), canEqual() 메서드 자동제공

// 2024-12-16 Spring Project 

public class BbsVO {
// 자료실 데이터 저장빈 클래스 => bbs_write.jsp의 네임팔파미터 이름과 bbs테이블 컬럼명과 빈클래스 변수명은 되도록이면 같게 한다.
	
	@Id // 구분키(식별키) => 유일키로 사용될 기본키 =>primary key
	private int bbs_no; // 자료실 번호
	
	private String bbs_name; // 글쓴이
	private String bbs_title; // 글제목
	private String bbs_pwd; // 비밀번호
	
	@Column(length=4000)
	private String bbs_cont; // 글내용
	
	private String bbs_file; // 첨푸파일 경로와 파일명
	
	private int bbs_hit; // 조회수
	
	// 담변글과 관련된 부분
	private int bbs_ref; // 글 그룹번호 => 원본글과 답변글을 묶어주는 역할(원본글과 답변글 그룹번호는 같다.
	private int bbs_step; // 원본글과 답변글을 구분하는 번호값이면서 몇번째 답변글인가를 알려준다.
	// 원본글이면 0, 처선째 답변글이면 1, 2번째 답변글이면 2...
	private int bbs_level; // 답변글 정렬순서
	@CreationTimestamp // 하이버네이트의 특별한 기능으로 등록시점의 날짜값을 기록
	private Timestamp bbs_date;
	
	
	
	
}
