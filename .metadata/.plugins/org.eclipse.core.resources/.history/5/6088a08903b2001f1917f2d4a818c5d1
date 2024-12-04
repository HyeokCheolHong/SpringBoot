package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.ReplyDAO;
import net.daum.vo.ReplyVO;

// 2024-12-02 댓글 ServiceImple 작성

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyDAO replyDao;

	@Override
	public void addReply(ReplyVO vo) {
		this.replyDao.addReply(vo);
	}

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

	// 2024-12-03 댓글 삭제관련 코드 작성
	@Override
	public void removeReply(int rno) {
		this.replyDao.removeReply(rno);
	}
}
