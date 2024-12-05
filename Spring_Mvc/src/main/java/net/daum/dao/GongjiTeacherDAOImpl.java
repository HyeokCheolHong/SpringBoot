package net.daum.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.GongjiTeacherVO;

//2024-12-05 문제) 공지사항 만들기 정답

@Repository
public class GongjiTeacherDAOImpl implements GongjiTeacherDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert(GongjiTeacherVO g) {
		this.sqlSession.insert("gongji_teacher_save", g);
	} // 공지 저장

	@Override
	public int getTotalCount() {
		return this.sqlSession.selectOne("gongji_teacher_count");
	} // 총 레코드 개수

	@Override
	public List<GongjiTeacherVO> getGongjiTeacherList(GongjiTeacherVO g) {
		return this.sqlSession.selectList("gongji_teacher_li", g);
	} // 공지 목록

	@Override
	public void updateTeacherHit(int gno) {
		this.sqlSession.update("gongji_teacher_hit", gno);
	} // 조회수 증가

	@Override
	public GongjiTeacherVO getGongjiTeacherCont(int gno) {
		return this.sqlSession.selectOne("gongji_teacher_cont", gno);
	} // 내용 보기

	@Override
	public void updateTeacherGongji(GongjiTeacherVO eg) {
		this.sqlSession.update("gongji_teacher_edit", eg);
	} // 공지 수정

	@Override
	public void deleteTeacherGongji(int gno) {
		this.sqlSession.delete("gongji_teacher_del", gno);
	}


}
