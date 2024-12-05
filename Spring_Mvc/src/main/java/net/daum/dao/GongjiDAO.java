package net.daum.dao;

import net.daum.vo.GongjiVO;

//2024-12-04 문제)1 공지사항 만들기

public interface GongjiDAO {

	// 2024-12-05 문제2) 공지사항 저장메서드 및 리스트 메서드 만들기
	void gongji_insert(GongjiVO g);

	void gongji_list(GongjiVO g);

}
