package net.daum.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.hibernate.Hibernate;
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

	@Override
	public List<BoardFormDataVO> boardFormDataList() {
		return this.sqlSession.selectList("react_list");
	}// 리액트 게시판 목록

	@Override
	public BoardFormDataVO boardFormDataCont(int no) {
		
		System.out.println(" \n ===========> JPA로 리액트 게시판 번호에 해당하는 내용보기");
		BoardFormDataVO board = this.BoardFormDataRepo.findById(no).orElse(null);
		// 프록시가 아닌 실제 엔티티를 조회
		
		if(board != null) {
			Hibernate.initialize(board); // 프록시 초기화
		}
		return board;
	}

	@Override
	public void updateBoard(BoardFormDataVO eb) {
		System.out.println(" \n ===============> JPA로 리액트 게시판 수정");
		Optional<BoardFormDataVO> result = this.BoardFormDataRepo.findById(eb.getNo());
		
		BoardFormDataVO b;
		if(result.isPresent()) { // 번호에 해당하는 값이 있다면
			b = result.get();
			
			b.setName(eb.getName()); // 수정할 글쓴이를 저장
			b.setTitle(eb.getTitle()); // 수정할 제목을 저장
			b.setPwd(eb.getPwd()); // 수정할 비번을 저장
			b.setContent(eb.getContent()); // 수정할 비번을 저장
			
			this.BoardFormDataRepo.save(b);
			
		}
		
	} // 리액트 게시판 수정

	@Override
	public void deleteBoard(int no) {
		System.out.println(" \n =============> JPA로 리액트 게시판 삭제");
		
		this.BoardFormDataRepo.deleteById(no);
	}
	
	
	
}
