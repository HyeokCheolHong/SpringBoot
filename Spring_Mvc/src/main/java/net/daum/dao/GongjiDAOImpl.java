package net.daum.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.GongjiVO;

//2024-12-04 문제)1 공지사항 만들기

@Repository
public class GongjiDAOImpl implements GongjiDAO {

	@Autowired
	private SqlSession sqlSession;

	// 2024-12-05 문제2) 공지사항 저장메서드 및 리스트 메서드 만들기
	@Override
	public void gongji_insert(GongjiVO g) {
		this.sqlSession.insert("gongjili_save", g);
	}

	@Override
	public void gongji_list(GongjiVO g) {
		this.sqlSession.selectList(null);
		
	}
}
