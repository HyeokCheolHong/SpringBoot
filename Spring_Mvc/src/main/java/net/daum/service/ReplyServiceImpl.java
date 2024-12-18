package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.daum.dao.BoardDAO;
import net.daum.dao.ReplyDAO;
import net.daum.vo.ReplyVO;

// 2024-12-02 댓글 ServiceImple 작성

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyDAO replyDao;

	// 2024-12-04 댓글수 카운트 기능 추가 작업 코드
	@Autowired
	private BoardDAO boardDao;
	// boardDao 의존성 주입 추가는 고객의 추가요구 사항을 반영해서 replycnt컬럼에 댓글이 추가되면 1증가, 댓글이 삭제되면 -1감소
	// 2024-12-04 여기까지
	
//	// 2024-12-04 댓글수 카운트 기능 추가 작업 코드
	@Transactional
	@Override
	public void addReply(ReplyVO vo) {
		this.replyDao.addReply(vo); // 댓글 추가 insert
		this.boardDao.updateReplyCnt(vo.getBno(), 1);
		// vo.getBno()로 게시판 번호를 구함. 댓글수 1증가
	}
	
//	// 2024-12-04 댓글수 카운트 기능 추가 작업 코드로 주석처리
//	@Override
//	public void addReply(ReplyVO vo) {
//		this.replyDao.addReply(vo);
//	}


	// 2024-12-02 기존 코드이나 ARC에서 댓글 정보 불러오는것이 안되서 주석처리
	@Override
	public List<ReplyVO> listReply(int bno) {
		return this.replyDao.listReply(bno);
	}

	
	// 2024-12-03 ARC로 댓글 정보 불러오는 것이 불가능해서 작성한 SQL 로그 불러오기 코드
//	@Override
//	public List<ReplyVO> listReply(int bno) {
//	    List<ReplyVO> replies = this.replyDao.listReply(bno);
//	    System.out.println("Fetched replies: " + replies);
//	    return replies;
//	}

	// 2024-12-03 댓글 수정관련 코드 작성
	@Override
	public void updateReply(ReplyVO vo) {
		this.replyDao.updateReply(vo);
	}

	// 2024-12-04 댓글 카운트 관련 코드작성
	@Transactional // 트랜잭션 적용
	@Override
	public void removeReply(int rno) {
		// 댓글이 삭제되기 전에 먼저 게시판 번호부터 구해야함.
		int bno = this.replyDao.getBno(rno); // 댓글 번호를 기준으로 게시판 번호를 구함
		this.replyDao.removeReply(rno);
		this.boardDao.updateReplyCnt(bno, -1); // 댓글 삭제 후 댓글 개수 1감소
	}
	
	// 2024-12-04 댓글 카운트 관련 코드작성 으로 인한 주석처리
//	//2024-12-03 댓글 삭제관련 코드 작성
//	@Override
//	public void removeReply(int rno) {
//		this.replyDao.removeReply(rno);
//	}	
	// 2024-12-04 댓글 카운트 관련 코드작성 으로 인한 주석처리
	
	
}
