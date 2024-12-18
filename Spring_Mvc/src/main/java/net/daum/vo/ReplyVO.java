package net.daum.vo;

import lombok.Getter;
import lombok.Setter;

// 2024-12-02 댓글 데이터 저장 빈 클래스 => TBL_REPLY 테이블의 컬럼명과 일치하는 빈클래스 변수명을 정의
@Setter
@Getter
public class ReplyVO {
	private int rno; // 댓글번호
	private int bno; // 게시판번호
	private String replyer; // 댓글 작성자
	private String replytext; // 댓글 내용
	private String regdate; // 댓글 등록 날짜
	private String upadatedate; // 댓글 수정 날짜
	
	// Getters and Setters

    @Override
    public String toString() {
        return "ReplyVO{" +
                "rno=" + rno +
                ", bno=" + bno +
                ", replyer='" + replyer + '\'' +
                ", replyText='" + replytext + '\'' +
                ", regDate=" + regdate +
                ", updateDate=" + upadatedate +
                '}';
    }         
}
