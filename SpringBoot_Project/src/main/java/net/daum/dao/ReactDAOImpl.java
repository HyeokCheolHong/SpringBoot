package net.daum.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.BoardFormDataVO;

//2024-12-18 React-Spring 연결 게시판 실습

@Repository
public class ReactDAOImpl implements ReactDAO {
	
	@Autowired
	private BoardFormDataRepository BoardFormDataRepo; // JPA를 실행할 자동의존성 주입
	
	@Autowired
	private SqlSession sqlSession; // mybatis 쿼리문을 수행할 자동의존성 주입

	@Override
	public void reactInsert(BoardFormDataVO boardData) {
		System.out.println(" \n =================> JPA로 react 게시판 저장");
		
		int no = this.BoardFormDataRepo.getNextSequenceValue();
		
		boardData.setNo(no); // 게시판 번호값 저장
		
		this.BoardFormDataRepo.save(boardData);
		// JPA로 react 게시판에 저장시킴
	}
	
	
}
