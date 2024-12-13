package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}
