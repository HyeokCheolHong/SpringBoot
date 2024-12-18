package net.daum.service;

import java.util.List;

import net.daum.vo.GongjiTeacherVO;

//2024-12-05 문제) 공지사항 만들기 정답

public interface GongjiTeacherService {

	void insert(GongjiTeacherVO g);

	int getTotalCount();

	List<GongjiTeacherVO> getGongjiTeacherList(GongjiTeacherVO g);

	void updateTeacherHit(int gno);
	
	GongjiTeacherVO getGongjiTeacherCont(int gno);

	void updateTeacherGongji(GongjiTeacherVO eg);

	void deleteTeacherGongji(int gno);
}
