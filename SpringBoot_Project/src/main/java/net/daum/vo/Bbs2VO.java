package net.daum.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

// 2024-12-16 Spring Project 

public class Bbs2VO {
// 첨부된 파일을 가져오는 클래스
	
	private MultipartFile uploadFile;
	// 실제 업로드 되어진 파일을 저장. bbs_wirte.jsp의 <input type="file" name="uploadFile"의 네임파라미터 이름과 멤버변수명(속성명, 필드명)을 같게 한다.
}
