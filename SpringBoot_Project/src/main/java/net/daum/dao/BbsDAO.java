package net.daum.dao;

import java.util.List;

import net.daum.vo.BbsVO;
import net.daum.vo.PageVO;

// 2024-12-16 Spring Project 

public interface BbsDAO {

	void insertBbs(BbsVO bbs);

	int getRowCount(PageVO p);

	List<BbsVO> getBbslist(PageVO p);

}
