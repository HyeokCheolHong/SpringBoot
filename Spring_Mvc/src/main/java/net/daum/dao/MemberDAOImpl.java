package net.daum.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.MemberVO;

@Repository
// Spring에서 @Repository 애노테이션을 설정해야 DAO를 인식한다 
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired // 자동 의존성 추가(DI)
	private SqlSession sqlSession;
	// sqlSession은 mybatis 쿼리문 수행
	
	@Override
	public void insertMember(MemberVO m) {
		this.sqlSession.insert("member_in", m);
		// member_in은 member.xml에서 설정한 유일 insert 아이디명이다.
		
		// mybatis 쿼리문 메서드 종류
		// 1. insert() : 레코드 저장
		// 2. update() : 레코드 수정
		// 3. delete() : 레코드 삭제
		// 4. selectOne() : 한줄 레코드 검색
		// 5. selectList() : 하나이상의 레코드 검색
	}
	
	// 회원저장
	
}
