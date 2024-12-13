package net.daum.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.GuestBookVO;
import net.daum.vo.PageVO;

//2024-12-13 Spring Gradle Project 실습

@Repository
public class GuestBookDAOImpl implements GuestBookDAO {

	@Autowired // 자동 의존성 주입
	private GuestBookRepository guestBookRepo; // guestBookRepo는 JPA수행
	
	@Autowired
	private SqlSession sqlSession; // sqlSession은 mybatis쿼리문을 수행

	@Override
	public void insertGuestBook(GuestBookVO g) {
		System.out.println(" \n=================>(방명록 저장 JPA)");
		this.guestBookRepo.save(g);
	} // 방명록 저장

	@Override
	public long getTotalCount() {
		System.out.println(" \n=================>(총 레코드 개수 JPA)");
		return this.guestBookRepo.count();
	} // 총 레코드 개수 구하기

	@Override
	public List<GuestBookVO> getGuestBookList(PageVO p) {
		return this.sqlSession.selectList("guest_list", p);
	}

	
	
}
