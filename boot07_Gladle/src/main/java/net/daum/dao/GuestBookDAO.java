package net.daum.dao;

import java.util.List;

import net.daum.vo.GuestBookVO;
import net.daum.vo.PageVO;

//2024-12-13 Spring Gradle Project 실습

public interface GuestBookDAO {

	void insertGuestBook(GuestBookVO g);

	long getTotalCount();

	List<GuestBookVO> getGuestBookList(PageVO p);

	
}
