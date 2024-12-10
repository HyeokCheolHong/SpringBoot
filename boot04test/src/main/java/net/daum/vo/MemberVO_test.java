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
@Table(name="tbl_members6_test")
@EqualsAndHashCode(of="uid2_test")

//2024-12-10 JPA 연관관게 수업

public class MemberVO_test {
	
	@Id // 각 엔티티를 식별할 수 있는 식별키 => 기본키
	private String uid2_test;
	private String upw_test;
	private String uname_test;
}
