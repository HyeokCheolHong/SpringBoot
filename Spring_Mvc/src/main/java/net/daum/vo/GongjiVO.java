package net.daum.vo;

import lombok.Data;

// 2024-12-04 문제)공지사항 만들기

@Data
public class GongjiVO {
	private int gno;
	private String gname;
	private String gtitle;
	private String gcont;
	private int ghit;
	private String gdate;
	
	private int startrow;
	private int endrow;
}
