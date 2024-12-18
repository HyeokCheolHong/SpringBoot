package net.daum.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired // 자동 의존성 추가(DI)
	private SqlSession sqlSession;
	// mybatis 쿼리문 수행할 sqlSession
	
	// 2024-11-28 작성
	// 게시판 저장
		@Override
		public void insertBoard(BoardVO b) {
			// insert() 메소드는 mybatis에서 레코드를 저장한다.
			// board_in은 board.xml에서 설정하는 유일한 insert 아이디명이다.
			this.sqlSession.insert("board_in", b);
		}

		// 총 게시물 수
		@Override
		public int getTotalCount() {
			// selectOne() 메소드는 mybatis에서 단 한 개의 레코드만 반환하는 메소드이다.
			// board_count 는 board.xml에서 selectOne 태그에 설정해야하는 ID명이다.
			return this.sqlSession.selectOne("board_count");
		}
		
		// 목록
		@Override
		public List<BoardVO> getBoardList(BoardVO b) {
			// mybatis에서 selectList() 메소드는 하나 이상의 레코드를 검색해서 컬렉션 List로 반환
			// b_list는 board.xml에서 select 태그에 설정해야하는 ID명이다.
			return this.sqlSession.selectList("b_list", b);
		}

		// 2024-11-29 조회수 증가 코드
		@Override
		public void updateHit(int bno) {
			sqlSession.update("b_hit", bno);
			// mybatis에서 update()메서드는 레코드를 수정한다.
			// b_hit는 board.xml에서 설정하는 유일ID이다.
			
		}

		// 2024-11-29 게시판 내용보기 코드
		@Override
		public BoardVO getBoardCont(int bno) {
			
			return this.sqlSession.selectOne("b_cont", bno);
			// mybatis에서 selectOne()메서드는 단 한개의 레코드만 반환하고, b_cont는 board.xml에서 설정하는 유일한 ID명
		}

		// 2024-11-29 게시판 수정하기 코드
		@Override
		public void updateBoard(BoardVO eb) {
			/*
			 * 문제) 번호를 기준으로 글쓴이, 글제목, 글내용을 수정되게 만들어 보자. 유일 ID 명은 b_edit로 한다.
			 */
			sqlSession.update("b_edit", eb);
		}

		//2024-11-29 게시판 삭제하기 코드
		@Override
		public void delboard(int bno) {
			sqlSession.delete("b_del", bno);
		}

		// 2024-12-04 댓글 작성 카운트 추가 작업
		@Override
		public void updateReplyCnt(int bno, int count) {
			Map<String, Object> hm = new HashMap<>();
			
			hm.put("bno", bno); // board.xml에서 왼쪽의 bno 키이름을 참조해서 게시판 번호를 가져옴
			hm.put("count", count);
			
			this.sqlSession.update("replyCntUpdate", hm);
			// replyCntUpdate는 mybatis 매퍼태그에서 설정한 유일 아이디명
		} // 댓글이 추가되면 댓글수 1증가, 댓글이 삭제되면 댓글수 1감소
		
	
}
