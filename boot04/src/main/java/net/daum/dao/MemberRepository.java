package net.daum.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.daum.vo.MemberVO;

//2024-12-10 JPA 연관관게 수업

public interface MemberRepository extends JpaRepository<MemberVO, String> { // <Entity Bean Class, Type>
	
	@Query(value="select m.uid2, count(fname) from tbl_members6 m left outer join tbl_profile3 p "
			+ "on m.uid2 = p.member_uid2 where m.uid2 = ?1 group by m.uid2", nativeQuery = true)
	/*
	 * nativeQuery문은 말 그대로 데이터베이스에 종속적인 sql문을 그대로 사용하겠다는 의미이다.
	 * 다만 이경우는 데이트베이스에 독립적이라는 장점은 어느 정도 포기해야 한다.
	 * @Query 에 nativeQuery속성값을 true로 주면 메서드 실행시 value갑승ㄹ 실행한다.
	 * tbl_members6 테이블에는 레코드가 있거나 tbl_profile3 테이블에는 레코드가 없는 경우 left outer join을 한다. => 단방향 Fetch Join
	 */
	// ?1 : 첫번째로 전달된 인자값
	
	 /* 쿼리문 세부 설명
		tbl_members6 m와 tbl_profile3 p:
		tbl_members6 테이블은 m이라는 별칭으로 사용됩니다.
		tbl_profile3 테이블은 p라는 별칭으로 사용됩니다.
		
		LEFT OUTER JOIN:
		m.uid2와 p.member_uid2를 기준으로 두 테이블을 조인합니다.
		LEFT OUTER JOIN은 tbl_members6 테이블의 모든 데이터를 유지하고, 매칭되지 않는 경우에는 tbl_profile3에서 NULL 값을 반환합니다.
		
		WHERE m.uid2 = ?1:
		tbl_members6 테이블에서 특정 uid2 값(?1로 표시된 바인딩된 값)에 대해 필터링합니다.
		이 ?1은 일반적으로 Prepared Statement에서 동적으로 입력되는 값입니다.
		
		COUNT(fname):
		tbl_profile3의 fname 컬럼에 대해 개수를 셉니다.
		만약 fname이 NULL인 경우는 제외됩니다.

		GROUP BY m.uid2:
		결과를 m.uid2별로 그룹화합니다.
		각 uid2에 대해 COUNT(fname) 결과를 반환합니다.
	*/ 
	
	public List<Object[]> getMemberVOWithProfileCount(String id); // 실제 회원아이디와 프로필 사진 개수

	
	// JPQL
	@Query(value="select m.uid2,m.upw, m.uname, p.current2, p.fname from tbl_members6 m left outer join tbl_profile3 p "
			 +" on m.uid2 = p.member_uid2 where m.uid2 = ?1  and p.current2 = 1",nativeQuery = true)
	/* 쿼리문 세부 설명
		MemberVO 엔티티(m)와 Profile 엔티티(p)를 함께 선택합니다.
		결과는 각 MemberVO와 Profile의 엔티티 객체 쌍으로 반환됩니다.
		
		FROM MemberVO m:
		MemberVO 엔티티를 조회하며, 이를 m으로 참조합니다.
		
		LEFT OUTER JOIN Profile p ON m.uid2 = p.member:
		MemberVO의 uid2 필드와 Profile의 member 필드를 기준으로 두 엔티티를 조인합니다.
		LEFT OUTER JOIN을 사용했기 때문에 MemberVO에 해당하는 데이터는 항상 반환되며, Profile에 매칭되는 데이터가 없는 경우 NULL로 채워집니다.

		WHERE m.uid = ?1:
		MemberVO의 uid 값이 동적으로 제공된 값(?1)과 동일한 데이터를 필터링합니다.
		?1은 일반적으로 파라미터 바인딩에 의해 전달되는 값입니다.

		AND p.current = true:
		추가 조건으로 Profile 엔티티의 current 필드가 true인 데이터만 포함합니다.
		단, LEFT OUTER JOIN이기 때문에 p가 NULL인 경우에는 이 조건이 적용되지 않습니다. 따라서 매칭되지 않는 경우에도 MemberVO 데이터는 반환됩니다.
	*/
	public List<Object[]> getMemberVOWithProfile(String uid); // 회원 user1 아이디의 회원정보와 현재 사용중인 프로필 사진 정보
}
