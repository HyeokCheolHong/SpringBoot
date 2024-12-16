package net.daum.service;

import java.util.List;

import net.daum.vo.BbsVO;
import net.daum.vo.PageVO;

public interface BbsService  {

	void insertBbs(BbsVO bbs);

	int getRowCount(PageVO p);

	List<BbsVO> getBbsList(PageVO p);


}
