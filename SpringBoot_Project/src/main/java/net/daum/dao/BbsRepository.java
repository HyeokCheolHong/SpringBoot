package net.daum.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.daum.vo.BbsVO;

// 2024-12-16 Spring Project 

public interface BbsRepository extends JpaRepository<BbsVO, Integer> {
// <엔티티빈 타입, @Id 기본키로 설정된 해당 속성 기본타입의 참조레퍼타입>
	
	@Query(value="select bbs_no_seq.nextval from dual", nativeQuery=true)
	Long getNextSequenceValue(); // bbs_no_seq 시퀀스 다음번호값 가져오기
	
	
	
}
