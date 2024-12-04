package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.BoardDAO;
import net.daum.vo.BoardVO;

@Service
// @Service 애노테이션을 추가함으로써 스프링에서 서비스로 인식한다.
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDao;
	// Service가 modelDAO와 ?? 를 연결해주는 bridge 역할을 한다.
	
	// 2024-11-28 작성
	// 게시판 저장
	@Override
	public void insertBoard(BoardVO b) {
		this.boardDao.insertBoard(b);
	}

	// 총 게시물 수
	@Override
	public int getTotalCount() {
		// this.이 생략 되어있다.
		return boardDao.getTotalCount();
	}

	// 목록
	@Override
	public List<BoardVO> getBoardList(BoardVO b) {
		return this.boardDao.getBoardList(b);
	}

	// 2024-11-29 게시판 내용보기 작업 후 추가된 코드 (BoardService.java) 
	// 스프링의 AOP를 통한 트랜잭션 적용
	@Override
	public BoardVO getBoardCont(int bno) {
		this.boardDao.updateHit(bno); 
		// 조회수 증가
		
		// 내용보기를 위한 return 값 내용 추가
		// 번호에 해당하는 레코드 가져오기 => 내용보기
		return boardDao.getBoardCont(bno);
	} // 조회수증가+내용보기

	// 2024-11-29 게시판 수정 작업 후 추가된 코드(BoardService.java)
	@Override
	public BoardVO getBoardCont2(int bno) {
		return this.boardDao.getBoardCont(bno);
	}

	// 2024-11-29 게시판 수정후 list로 돌아가는 BoardService.java 작업후 추가된 코드
	@Override
	public void updateBoard(BoardVO eb) {
		this.boardDao.updateBoard(eb);
	}

	// 2024-11-29 게시판 삭제 작업 후 추가된 코드(BoardService.java)
	@Override
	public void delBoard(int bno) {
		this.boardDao.delboard(bno);
	}
	
}
