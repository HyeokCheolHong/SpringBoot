package net.daum.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude="member") // ToString() 메서드 호출시 "member"변수를 제외
@Entity
@SequenceGenerator( // 시퀀스 생성기
			name = "fno_seq_gename", // 시퀀스 제너레이터 이름
			sequenceName = "fno_seq", // 시퀀스 이름
			initialValue = 1, // 시작값
			allocationSize = 1 // 증가값
		)
@Table(name="tbl_profile3") // tbl_prifle3 테이블 생성
@EqualsAndHashCode(of="fname")

//2024-12-10 JPA 연관관게 수업

public class Profile {
	@Id // 각 엔티티를 식별할 수 있는 개별 식별키
	@GeneratedValue(
				strategy=GenerationType.SEQUENCE, // 사용할 전략을 시퀀스로 선택
				generator="fno_seq_gename" // 시퀀스 생성기에서 설정한 시퀀스 제너레이터 이름
			)
	private int fno;
	
	private String fname;
	
	private boolean current2;
	
	@ManyToOne // 다대일 연관관계
	private MemberVO member; // 외래키로 설정
	
}
