package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.GongjiTeacherDAO;
import net.daum.vo.GongjiTeacherVO;

//2024-12-05 문제) 공지사항 만들기 정답

@Service
public class GongjiTeacherServiceImpl implements GongjiTeacherService {
	
	@Autowired
	private GongjiTeacherDAO gongjiTeacherDAO;

	@Override
	public void insert(GongjiTeacherVO g) {
		this.gongjiTeacherDAO.insert(g);
	}

	@Override
	public int getTotalCount() {
		return this.gongjiTeacherDAO.getTotalCount();
	}

	@Override
	public List<GongjiTeacherVO> getGongjiTeacherList(GongjiTeacherVO g) {
		return this.gongjiTeacherDAO.getGongjiTeacherList(g);
	}

	@Override
	public void updateTeacherHit(int gno) {
		this.gongjiTeacherDAO.updateTeacherHit(gno);
	}
	
	@Override
	public GongjiTeacherVO getGongjiTeacherCont(int gno) {
		return gongjiTeacherDAO.getGongjiTeacherCont(gno);
	}

	@Override
	public void updateTeacherGongji(GongjiTeacherVO eg) {
		this.gongjiTeacherDAO.updateTeacherGongji(eg);
	}

	@Override
	public void deleteTeacherGongji(int gno) {
		this.gongjiTeacherDAO.deleteTeacherGongji(gno);
	}
}
