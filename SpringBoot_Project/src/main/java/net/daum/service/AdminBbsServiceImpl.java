package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.AdminBbsDAO;
import net.daum.vo.BbsVO;
import net.daum.vo.PageVO;

//2024-12-18 Spring Admin Project 실습

@Service
public class AdminBbsServiceImpl implements AdminBbsService {
	
	@Autowired
	private AdminBbsDAO adminBbsDao;

	@Override
	public int getRowCount(PageVO p) {
		return this.adminBbsDao.getRowCount(p);
	}

	@Override
	public List<BbsVO> getBbsList(PageVO p) {
		return this.adminBbsDao.getBbsList(p);
	}
	
	
}
