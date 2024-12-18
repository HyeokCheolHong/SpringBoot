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

// 2024-12-18 React-Spring 연결 게시판 실습

@Setter
@Getter
@ToString
@Entity
@Table(name="board_form_data") // board_form_data table 생성
@EqualsAndHashCode(of="no")
/* equals(), hashCode(), canEqual() 메서드 자동 제공 */
public class BoardFormDataVO {
// 리액트와 스프링 연동 게시판 엔티티빈 클래스
	
	@Id // 구분키(식별키) 즉 유일키로 사용될 기본키 컬럼(primary key)
	private int no; // 번호
	private String name; // 글쓴이
	private String title; // 글제목
	private String pwd; // 비번
	
	@Column(length=4000) // 컬럼 내용 크기를 4000으로 지정
	private String content; // 글내용
	
	@CreationTimestamp // CreationTimestamp 애노테이션은 하이버네이트만의 특별한 기능으로 등록시점의 날짜값 자동 기록
	private Timestamp regdate; // 등록날짜
	
}
