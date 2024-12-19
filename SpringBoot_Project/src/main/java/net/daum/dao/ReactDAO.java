package net.daum.dao;

import java.util.List;

import net.daum.vo.BoardFormDataVO;

//2024-12-18 React-Spring 연결 게시판 실습

public interface ReactDAO {

	void reactInsert(BoardFormDataVO boardData);

	List<BoardFormDataVO> boardFormDataList();

	BoardFormDataVO boardFormDataCont(int no);

	void updateBoard(BoardFormDataVO vo);

	void deleteBoard(int no);

}
