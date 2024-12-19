package net.daum.dao;

import java.util.List;

import net.daum.vo.BbsVO;
import net.daum.vo.PageVO;

//2024-12-18 Spring Admin Project 실습

public interface AdminBbsDAO {

	int getRowCount(PageVO p);

	List<BbsVO> getBbsList(PageVO p);

	void insertBbs(BbsVO bbs);

	BbsVO getAdminBbsCont(int no);

	void adminUpdateBbs(BbsVO bbs);

	void adminDeleteBbs(int no);

}
