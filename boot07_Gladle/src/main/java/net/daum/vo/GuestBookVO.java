package net.daum.vo;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter // setter()메서드 자동제공
@Getter // getter()메서드 자동제공
@ToString // 
@Entity
@SequenceGenerator(
			name="guestbook_no_seq_gename", // 시퀀스 제너레이터 이름
			sequenceName = "guestbook_no_seq", // 시퀀스 이름
			initialValue = 1, // 시작값
			allocationSize = 1 // 증가값
		)
@Table(name="guestbook") // gusetbook 테이블 생성
@EqualsAndHashCode(of="guest_no")
// equals, hashCode, canEqual 메소드 자동제공

public class GuestBookVO {
	// 방명록 엔티티빈 클래스 -> 해당 클래스를 통해서 guestbook 테이블을 생성한다.
	// guestbook_write.jsp의 방명록 글쓰기 폼의 네임파라미터 이름과 빈클래스 변수명, 해당 테이블 컬럼명은 같게 한다.
	
	@Id // 구분키인 식별키 지정 (유일키로 사용될 기본키) => primary key
	@GeneratedValue(
				strategy = GenerationType.SEQUENCE, // 사용할 전략을 시퀀스로 선택
				generator = "guestbook_no_seq_gename" // 시퀀스 생성기에 설정해 놓은 제너레이터 이름
			)
	private int guest_no; // 방명록 번호
	
	private String guest_name; // 글쓴이
	private String guest_title; // 글제목
	
	@Column(length = 4000)
	private String guest_cont; // 글내용
	// 컬럼 크기가 4000인 글내용
	
	private int guest_hit; // 조회수
	
	@CreationTimestamp // 이 애노테이션은 글 등록시점의 날짜와 시간값을 기록한다.
	private Timestamp guest_date; // 글 등록날짜
	
	

	
	
	

}
