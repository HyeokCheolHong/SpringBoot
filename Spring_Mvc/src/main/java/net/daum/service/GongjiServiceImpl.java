package net.daum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.GongjiDAO;
import net.daum.vo.GongjiVO;

// 2024-12-04 문제)1 공지사항 만들기

@Service
public class GongjiServiceImpl implements GongjiService {
	
	@Autowired
	private GongjiDAO gongjiDAO;

	// 2024-12-05 문제2) 공지사항 저장메서드 및 리스트 메서드 만들기
	@Override
	public void gongji_insert(GongjiVO g) {
		this.gongjiDAO.gongji_insert(g);		
	}

	@Override
	public void gongji_list(GongjiVO g) {
		this.gongjiDAO.gongji_list(g);
		
	}
}
