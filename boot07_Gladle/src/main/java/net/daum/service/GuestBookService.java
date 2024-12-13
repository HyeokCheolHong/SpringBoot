package net.daum.service;

import java.util.List;

import net.daum.vo.GuestBookVO;
import net.daum.vo.PageVO;

// 2024-12-13 Spring Gradle Project 실습

public interface GuestBookService {

	void insertGuestBook(GuestBookVO g);

	long getTotalCount();

	List<GuestBookVO> getGuestBookList(PageVO p);

}
