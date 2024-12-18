package net.daum.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.MessageVO;

//2024-12-04 스프링 AOP와 트랜잭션 실습을 위한 코드

@Repository // @Repository 애노테이션을 설정해야 스프링에서 DAO로 인식한다.
public class MessageDAOImpl implements MessageDAO {
	
	@Autowired
	private SqlSession sqlSession; // sqlSession은 mybatis에서 쿼리문 수행

	@Override
	public void insertMem(MessageVO vo) {
		this.sqlSession.insert("message_in", vo); // mybatis에서 insert() 메서드는 레코드를 저장한다. message_in은 mybatis매퍼태그에서 설정할 유일 아이디멍
	}// tbl_message 테이블에 메시지 추가
	
}
