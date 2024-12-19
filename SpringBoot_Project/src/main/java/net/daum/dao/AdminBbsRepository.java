package net.daum.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.daum.vo.BbsVO;

//2024-12-18 Spring Admin Project 실습

public interface AdminBbsRepository extends JpaRepository<BbsVO, Integer> {
	
	@Query(value="select bbs_no_seq.nextval from dual", nativeQuery=true)
	int getNextSequenceValue(); // bbs_no_seq 시퀀스 다음번호값 가져오기
	
	@Modifying // @Query 애노테이션은 select문만 가능하지만 @Modifying 애노테이션을 이용해서 DML(Insert, update, delete)문 작업 처리가 가능하다.
	@Query("update BbsVO b set b.bbs_name=?1, b.bbs_title=?2, b.bbs_cont=?3, b.bbs_file=?4 where b.bbs_no=?5")
	public void adminEditBbs(String name, String title, String cont, String file, int no);
	
	/*
	 * ?1 은 첫번째 전달되어질 인자값 ...
	 * JPQL문은 실제 테이블 명 대신 엔티티빈 클래스를 사용하고 실제 컬럼명 대신 엔티티빈의 속성을 사용한다.                                    
	 */
	
	@Modifying
	@Query("delete from BbsVO b where b.bbs_no=?1")
	public void adminDeleteBbs(int no);
	
}
