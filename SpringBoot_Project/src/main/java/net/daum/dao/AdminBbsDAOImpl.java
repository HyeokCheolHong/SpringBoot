package net.daum.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.BbsVO;
import net.daum.vo.PageVO;

//2024-12-18 Spring Admin Project 실습

@Repository
public class AdminBbsDAOImpl implements AdminBbsDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private AdminBbsRepository adminBbsRepo;

	@Override
	public int getRowCount(PageVO p) {
		return this.sqlSession.selectOne("abbs_count", p);
	} // 관리자 자료실 검색전후 레코드 개수

	@Override
	public List<BbsVO> getBbsList(PageVO p) {
		return this.sqlSession.selectList("abbs_list", p);
	} // 관리자 자료실 검색전후 목록

}
