package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import net.daum.dao.GuestBookDAO;
import net.daum.vo.GuestBookVO;
import net.daum.vo.PageVO;

//2024-12-13 Spring Gradle Project 실습

@Service
public class GuestBookServiceImpl implements GuestBookService {
	
	@Autowired
	private GuestBookDAO guestBookDao; //guestBookRepo는 JPA수행

	@Override
	public void insertGuestBook(GuestBookVO g) {
		this.guestBookDao.insertGuestBook(g);
	}

	@Override
	public long getTotalCount() {
		return this.guestBookDao.getTotalCount();
	}

	@Override
	public List<GuestBookVO> getGuestBookList(PageVO p) {
		return this.guestBookDao.getGuestBookList(p);
	}

	// 조회수증가+내용보기 => 스프링의 AOP를 통한 트랜잭션을 적용
	@Transactional(isolation = Isolation.READ_COMMITTED)
	// 트랜잭션 처리 => 트랜잭션이 적용되는 중간에 외부 간섭을 없애는것
	@Override
	public GuestBookVO getGCont(int gno) {
		this.guestBookDao.updateHit(gno); // 조회수 증가
		
		return this.guestBookDao.getGCont(gno);
	}
	
}
