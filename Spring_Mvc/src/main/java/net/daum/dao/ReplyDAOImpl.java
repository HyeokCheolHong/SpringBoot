package net.daum.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.ReplyVO;

// 2024-12-02 댓글 작성하는 DAO 생성

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Autowired // 자동 의존성 주입(DI)
	private SqlSession sqlSession;
	// mybatis 쿼리문 수행할 sqlSession 생성

	@Override
	public void addReply(ReplyVO vo) {
		// TODO Auto-generated method stub
		this.sqlSession.insert("reply_in", vo);
		// reply_in은 mybatis매퍼태그(reply.xml)에서 설정할 유일아이디명
	}; // 댓글 추가

	@Override
	public List<ReplyVO> listReply(int bno) {
		return this.sqlSession.selectList("reply_list", bno);
		// mybatis에서 selectList()메서드는 하나 이상의 레코드를 검색해서 컬렉션 리스트로 반환한다.
		// reply_list는 mybatis매퍼태그(reply.xml)에서 설정할 유일 아이디명이다.
	}; // 게시판 번호에 해당하는 댓글 목록

	// 2024-12-03 댓글 수정관련 코드작성
	@Override
	public void updateReply(ReplyVO vo) {
		this.sqlSession.update("reply_edit", vo);
		// reply_edit는 mybatis에서 설정하는 유일 아이디명
	}

	// 2024-12-03 댓글 삭제관련 코드작성
	@Override
	public void removeReply(int rno) {
		this.sqlSession.delete("reply_del", rno);
	}

	// 2024-12-04 댓글 카운트 관련 코드 작성
	@Override
	public int getBno(int rno) {
		return this.sqlSession.selectOne("reply_bno", rno);
	}
	

	
}
