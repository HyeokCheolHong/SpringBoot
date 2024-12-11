package net.daum.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//2024-12-11 Spring Security 실습

@Setter
@Getter
@ToString
@Entity
@SequenceGenerator(
			name="member7_no_seq_gename", // 시퀀스 제너레이터 이름
			sequenceName = "member7_no_seq", // 시퀀스 이름
			initialValue = 1, // 시작값
			allocationSize = 1 // 증가값
		)
@Table(name="tbl_member_roles7")
@EqualsAndHashCode(of="fno")

public class MemberRole { // 회원이 가지는 권한
	
	@Id
	@GeneratedValue(
				strategy = GenerationType.SEQUENCE, // 사용할 전략을 시퀀스로 선택
				generator = "member7_no_seq_gename" // 시퀀스 생성기에 설정해 놓은 member7_no_seq_gename 시퀀스 제너레이터 이름
			)
	private int fno;
	
	private String roleName; 
}
