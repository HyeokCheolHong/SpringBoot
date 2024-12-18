package net.daum.vo;

import lombok.Data;

@Data
public class GongjiTeacherVO {
	
	// tbl_gongjiTeacherVO 테이블의 컬럼명, gongji_teacher_write.jsp의 네임파라미터 이름과 빈클래스 변수명이 같은 데이터 저장빈 클래스를 정의한다.
		private int gno;
		private String gname;
		private String gtitle;
		private String gcont;
		private int ghit;
		private String gdate;
		
		private int startrow;
		private int endrow;
}
