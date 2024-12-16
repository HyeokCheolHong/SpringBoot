package net.daum.dao;

import java.util.List;
import java.util.Optional;

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

	@Override
	public void updateHit(int gno) {
		System.out.println(" \n ===================>(레코드 검색 후 조회수 증가 JPA)");
		Optional<GuestBookVO> guestbook_hit = this.guestBookRepo.findById(gno);
		// JPA로 번호를 기준으로 레코드 검색
		
		guestbook_hit.ifPresent(guestbook_hit2 -> {
			// 방명록 번호에 해당하는 자료가 있는 경우 실행
			guestbook_hit2.setGuest_hit(guestbook_hit2.getGuest_hit()+1);
			// 기존 조회수 + 1
			guestBookRepo.save(guestbook_hit2); // JPA로 조회수 1증가
		});
		
	} // 조회수 증가

	
	// 내용보기
	@Override
	public GuestBookVO getGCont(int gno) {
		GuestBookVO gc = this.guestBookRepo.getReferenceById(gno);
		// JPA로 번호에 해당하는 방명록 레코드를 검색해서 엔티티빈 타입 GuestBookVO로 반환
		// getReferenceById() 내장메서드는 번호에 해당하는 자료가 없는 경우 예외 오류를 발생시킨다.
		// 번호에 해당하는 값이 반드시 있는 경우만 사용하는 것이 좋다.
		
		return gc;
	}

	
	
}
