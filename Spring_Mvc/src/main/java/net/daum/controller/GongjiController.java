package net.daum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import net.daum.service.GongjiService;
import net.daum.vo.GongjiVO;

// 2024-12-04 문제) 공지사항 만들어보기

@Controller
@RequestMapping("/gongji/*")
public class GongjiController {
	
	/*
	 * 문제) 1. 다음과 같은 테이블 정보를 가진 tbl_gongji 공지 사항 테이블을 생성한다.
	 * 		컬럼명	자로형		크기		제약조건
	 * 		gno		number		38		primary key
	 * 		gname	varchar2	50		not null
	 * 		gtitle	varchar2	200		not null
	 * 		gcont	varchar2	4000	not null
	 * 		ghit	number		38		default 0
	 * 		gdate	date				default sysdate
	 * 
	 * 		2. 1부터 시작하고 1씩 증가하고 임시 메모리를 사용하지 않으며 최대 시퀀스 번호값 생성시 다시 처음부터 반복하지 않는 시퀀스 gno_seq 시퀀스를 생성
	 * 		
	 * 		3. 테이블 컬럼명과 일치하는 GongjiVO.java 데이터 저장빈 클래스 생성하고 Lambok lib 적용
	 * 
	 * 		4. mybatis-config.xml에서 GongjiVO빈클래스 객체 별칭이름 gongji를 등록한다. 그리고 sql문을 담는 매퍼태그를 가진 gongji.xml를 생성한다
	 * 
	 * 		5. 모델 DAO인 GongjiDAO.java 인터페이스와 이를 구현한 GongjiDAOImpl.java를 만든다.
	 * 
	 * 		6. 서비스에 해당하는 GongjiService.java 인터페이스와 이를 구현한 GongjiServiceImpl.java 를 만든다.
	 * 
	 * 		7. GongjiController.java에서 get으로 접근하는 매핑 주소가 g_write인 공지작성자, 공지제목, 공지내용 입력폼을 가진 뷰페이지 gongji_write.jsp를 생성한다.
	 * 			물론 사전에 해당컨트롤러에서 이동하게 만들어줘야 한다. 해당 뷰페이지는 JS와 jQuery등을 사용한 유효성 검증도 해야한다.
	 * 
	 * 2024-12-05 추가 문제 작성
	 * 문제2) 1. this.gongjiService.insert(g); 공지 저장메서드로 공지사항을 저장되게 만든다. gongji.xml에서 설정할 유일 아이디명은 gongji_save로 한다. 저장메서드는 다음과 같이 정의한다.
	 * 			public String gongji_insert(GongjiVO g){} 로 정의해서 한다.
	 * 
	 * 		2. public void gongji_list(GongjiVO g, HttpServletRequest request) {} 공지 리스트 메서드를 작성해 보자.
	 * 			공지 목록 뷰페이지 파일명은 gongji_list.jsp로 한다. 페이징이 되게 만든다. gongji.xml에서 설정할 유일 아이디명은 gongji_li로 한다.
	 * 
	 * 2024-12-05 추가 문제 작성
	 * 문제3) 1. GongjiController.java에서 this.gongjiService.updateHit(gno);메서드로 조회수 증가되게 하고,
	 * 			GongjiVO gc = this.gongjiService.getGongjiCont(gno); 메서드로 번호에 해당하는 공지내용을 오라클로 부터 가져와서
	 * 			ModelAndView 리턴 타입 객체 cm에 각 저장해서 해당 뷰페이지 파일명 gongji_cont.jsp에서 제목, 내용, 조회수만 나오게 만들자
	 * 			위의 두개의 메서드를 포함한 사용자 정의메서드는 다음과 같다.
	 * 			public ModelAndView getGongjiView(적절한 전달인자를 정의한다) {}
	 * 
	 * 			gongji.xml에서 설정할 유일 아이디명은 각각 gongji_hit와 gongji_cont가 된다.
	 * 
	 * 2024-12-05 추가 문제 작성
	 * 문제4) 1. 매핑주소가 gongji_Edit인 public String gongji_Edit(적절한 매개변수 정의){} 메서드를 GongjiController에 작성하고
	 * 			공지 수정폼 뷰페이지 파일명을 gongji_edit.jsp로 해서 보이게 한다. 그런다음 수정완료 매핑주소는 gongji_Edit_ok로 하고
	 * 			GongjiController에 public ModelAndView gongji_Edit_ok(적절한 매개변수 정의){} 메서드를 작성하고
	 * 			코드 생략..
	 * 			this.gongjiService.updateGongji(eg); 번호를 기준으로 공지작성자, 공지제목, 공지내용만 수정되게 하는 메서드를 작성한다.
	 * 
	 * 		2.	수정완료 gongji.xml에 설정할 유일 아이디명은 gongji_edit로 한다.
	 * 
	 * 2024-12-05 추가 문제 작성
	 * 문제5) 1. 매핑주소가 gongji_Del인 public ModelAndView gong_Del(적절한 전달인자 정의){} 메서드를 작성한다
	 * 			번호를 긱준으로 레코드를 삭제한다. this.gongjiService.delGongji(gno);
	 * 			삭제 이후 공지목록으로 이동할 때 책갈피 기능이 되게 만든다.
	 * 			삭제완료후 gongji.xml에 설정할 유일 아이디명은 gongji_del로 한다.
	 */
	
	@Autowired
	private GongjiService gongjiService;
	
	
//	@GetMapping("/g_write")
//	public ModelAndView g_write() {
//		return new ModelAndView("gongji/gongji_write");
//	}
//	
//	@GetMapping("/g_write")
//	public String gongji_insert(GongjiVO g) {
//
//		this.gongjiService.gongji_insert(g);
//
//		return "redirect:/g_list";
//	}
//	
//	// 2024-12-05 문제2 공지사항 저장 메서드 만들기
//	@RequestMapping("/gongji/g_list")
//	public void gongji_list(GongjiVO g, HttpServletRequest request) {
//		try {
////			List<GongjiVO> blist = this.gongjiService.getGongjiList(g);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
}
