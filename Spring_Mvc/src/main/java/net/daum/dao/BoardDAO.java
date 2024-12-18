package net.daum.dao;

import java.util.List;

import net.daum.vo.BoardVO;

public interface BoardDAO {

	// 2024-11-28 게시팡 작성 코드
	void insertBoard(BoardVO b);

	int getTotalCount();

	List<BoardVO> getBoardList(BoardVO b);

	// 2024-11-29 조회수 증가 코드 작성
	void updateHit(int bno);

	// 2024-11-29 게시판 내용보기 코드 작성
	BoardVO getBoardCont(int bno);

	// 2024-11-29 게시판 수정하기 코드 작성
	void updateBoard(BoardVO eb);
	
	//2024-11-29 게시판 삭제하기 코드 작성
	void delboard(int bno);

	// 2024-12-04 댓글 작성 카운트 추가 작업
	void updateReplyCnt(int bno, int count);
	
}
