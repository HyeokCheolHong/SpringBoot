package net.daum.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.daum.vo.BoardFormDataVO;

// 2024-12-18 React-Spring 연결 게시판 실습

public interface BoardFormDataRepository extends JpaRepository<BoardFormDataVO, Integer> {
	// 리액트와 스프링 연동 게시판에서 JPA 실행
	
	@Query(value="select react_no_seq.nextval from dual", nativeQuery = true)
	public int getNextSequenceValue();
	// react_no_seq 시퀀스 다음 번호값 반환해주는 nativeQuery
}
