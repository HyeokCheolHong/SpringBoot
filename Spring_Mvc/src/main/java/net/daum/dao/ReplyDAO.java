package net.daum.dao;

import java.util.List;

import net.daum.vo.ReplyVO;

// 2024-12-02 댓글 작성하는 DAO 생성
public interface ReplyDAO {

	void addReply(ReplyVO vo);

	List<ReplyVO> listReply(int bno);

	// 2024-12-03 댓글 수정관련 코드작성
	void updateReply(ReplyVO vo);

	// 2024-12-03 댓글 삭제관련 코드작성
	void removeReply(int rno);

	// 2024-12-04 댓글 카운트 관련 코드작성
	int getBno(int rno);

}
