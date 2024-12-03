package net.daum.service;

import java.util.List;

import net.daum.vo.BoardVO;

public interface BoardService {
	
	// 2024-11-28 작성
	void insertBoard(BoardVO b);

	int getTotalCount();

	List<BoardVO> getBoardList(BoardVO b);

	// 2024-11-29 게시판 내용보기 BoardController.java 작업 후 추가된 코드
	BoardVO getBoardCont(int bno);

	// 2024-11-29 게시판 수정 BoardController.java 작업 후 추가된 코드
	BoardVO getBoardCont2(int bno);

	// 2024-11-29 게시판 수정후 list로 돌아가는 BoardController.java 작업후 추가된 코드
	void updateBoard(BoardVO eb);

	// 2024-11-29 게시판 삭제 BoardController.java 작업 후 추가된 코드
	void delBoard(int bno);
	
}
