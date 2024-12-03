package net.daum.service;

import java.util.List;

import net.daum.vo.ReplyVO;

// 2024-12-02 댓글 Service 코드 작성

public interface ReplyService {

	void addReply(ReplyVO vo);

	// Object listReply(int bno);
	// 자동으로 생성된 listReply는 Object 타입인데 Object로 받으면 댓글 목록을 받을수 없다.
	List<ReplyVO> listReply(int bno);

	// 2024-12-03 댓글 수정관련 코드
	void updateReply(ReplyVO vo);

	// 2024-12-03 댓글 삭제관련 코드
	void removeReply(int rno);

}
