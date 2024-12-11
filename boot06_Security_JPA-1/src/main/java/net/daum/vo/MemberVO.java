package net.daum.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 2024-12-11 Spring Security 실습

@Setter
@Getter
@ToString // toString()메서드 자동제공
@Entity // 엔티티빈
@Table(name="tbl_members7") // tbl_members7 테이블 생성
@EqualsAndHashCode(of="mem_id")
// equals(), hashCode, canEqual() 메서드 자동제공

public class MemberVO { // 회원 관리 엔티티빈 클래스
	
	@Id // 유일키로 사용될 기본키 컬럼 => primary key
	private  String mem_id; // 회원 아이디
	
	private String mem_pwd; // 회원 비번
	private String mem_name; // 회원 이름
	
	@CreationTimestamp // hibernate 의 특별한 기능으로 등록시점 날짜/시간 값 기록
	private Timestamp mem_date; // 가입날짜
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	/*
	 * OneToMany : 일대다 연관관계
	 * cascade = cascadeType.ALL 은 jpa에서 영속성 전이중에서 모든 변경에 대한 전이로서 모든 엔티티빈 상태 변화에 대해 같이 처리하는 옵션
	 * 즉 부모 엔티티(tbl_members7)가 수행하는 작업(저장, 수정, 삭제 등)이 자식 엔티티(tbl_member_roles7)에도 전이됩니다.
	 * 
	 * fetch=FetchType.EAGER 은 tbl_members7과 tbl_member_roles7(종속관계에 있는 TBL) 두 테이블을 조회해야 하기 때문에 트랜잭션을 처리해주 거나,
	 * 즉시 로딩을 이용해서 조인하는 방법으로 처리해야 한다.
	 * 권한 정보는 회원정보와 마찬가지로 필요한 경우가 많기 때문에 fetch모드를 즉시 로딩으로 설정한다.
	 * 즉 데이터를 조회할 때, 연관된 엔티티도 함께 조회합니다.
		예: tbl_members7를 조회하면 자동으로 tbl_member_roles7도 함께 조회합니다.
	 */
	@JoinColumn(name="member") // 이미 존재하는 tbl_member_roles7 테이블에 
	private List<MemberRole> roles;
}
