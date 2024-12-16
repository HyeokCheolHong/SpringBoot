package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.BbsDAO;
import net.daum.vo.BbsVO;
import net.daum.vo.PageVO;

// 2024-12-16 Spring Project 



@Service // @Service 애노테이션을 설정해야 스프링에서 서비스로 인식한다.
public class BbsServiceImpl implements BbsService {

	
	@Autowired
	private BbsDAO bbsDao;

	@Override
	public void insertBbs(BbsVO bbs) {
		bbsDao.insertBbs(bbs);
	}

	@Override
	public int getRowCount(PageVO p) {
		return this.bbsDao.getRowCount(p);
	}

	@Override
	public List<BbsVO> getBbsList(PageVO p) {
		return this.bbsDao.getBbslist(p);
	}

	
	
}
