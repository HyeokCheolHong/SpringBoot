package net.daum.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_members6") // tbl_members6 테이블명이 생성
@EqualsAndHashCode(of="uid2") // 

// 2024-12-10 JPA 연관관게 수업

public class MemberVO {
	@Id // 각 엔티티빈을 식별할 수 있도록 해주는 식별키 => 기본키
	private String uid2; // 회원아이디
	private String upw; // 비밀번호
	private String uname; // 회원이름
	
}
