package net.daum.service;

import java.util.List;

import net.daum.vo.BoardFormDataVO;

//2024-12-18 React-Spring 연결 게시판 실습

public interface ReactService {

	void reactInsert(BoardFormDataVO boardData);

	List<BoardFormDataVO> boardFormDatalist();

	BoardFormDataVO getBoardDataCont(int no);

	void updateBoard(BoardFormDataVO vo);

	void delBoard(int no);

}
