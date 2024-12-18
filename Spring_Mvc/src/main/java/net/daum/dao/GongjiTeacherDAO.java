package net.daum.dao;

import java.util.List;

import net.daum.vo.GongjiTeacherVO;

// 2024-12-05 문제) 공지사항 만들기 정답
public interface GongjiTeacherDAO {

	void insert(GongjiTeacherVO g);

	int getTotalCount();

	List<GongjiTeacherVO> getGongjiTeacherList(GongjiTeacherVO g);

	GongjiTeacherVO getGongjiTeacherCont(int gno);

	void updateTeacherHit(int gno);

	void updateTeacherGongji(GongjiTeacherVO eg);

	void deleteTeacherGongji(int gno);


}
