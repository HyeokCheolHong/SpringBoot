package net.daum.controller;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.daum.service.BbsService;
import net.daum.vo.Bbs2VO;
import net.daum.vo.BbsVO;
import net.daum.vo.PageVO; 

//2024-12-16 Spring Project 

@Controller
@RequestMapping("/bbs/*") //컨트롤러 자체 매핑주소를 등록
public class BbsController {//사용자 자료실 컨트롤러 클래스

	@Autowired
	private BbsService bbsService;
	
	//자료실 글쓰기
	@GetMapping("/bbs_write")
	public ModelAndView bbs_write(HttpServletRequest request) {
		
		//페이징 책갈피 기능 추가
		int page=1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		ModelAndView wm = new ModelAndView();
		wm.setViewName("bbs/bbs_write");
		/* 
		 * setViewName()메서드 인자값 종류)
		 *  1.뷰페이지 경로 : /WEB-INF/views/bbs/bbs_write.jsp
		 *  2.redirect:/매핑주소
		 */
		wm.addObject("page", page);
		return wm;		
	}//bbs_write()
	
	// 자료실 저장
	@PostMapping("/bbs_write_ok")
	private String bbs_wirte_ok(BbsVO bbs, Bbs2VO bbs2, HttpServletRequest request) {
		String uploadFolder = request.getSession().getServletContext().getRealPath("upload");
		// 첨부파일 서버에 업로드할 실제 경로
		
		MultipartFile uploadFile = bbs2.getUploadFile();
		// 스프링에서 MultipartFile 타입을 제공해서 업로드 되는 파일 데이터를 쉽게 처리함.
		// 첨부된 파일을 가져온다.
		
		if(!uploadFile.isEmpty()) {
		// 첨부파일이 있다면
			
			System.out.println("================================>(첨부파일 JPA)");
			System.out.println("Upload 파일 명 : "+uploadFile.getOriginalFilename());
			// 첨부된 원본 파일명
			System.out.println("Upload 파일 크기 : "+uploadFile.getSize());
			// 업로드 파일 크기
			
			String fileName = uploadFile.getOriginalFilename();
			
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR); // 년도
			int month = cal.get(Calendar.MONTH)+1; // 월, +1을 한 이유는 1월이 0으로 반환되기 때문이다.
			int date = cal.get(Calendar.DATE); // 일
			
			String homedir = uploadFolder + "/"+year+"-"+month+"-"+date; // 오늘날짜 폴더경로 저장
			
			File path01 = new File(homedir);
			
			if(!(path01.exists())) {
				path01.mkdir(); // 오늘날짜 폴더 생성
			}
			
			Random r = new Random();
			// 난수를 발생시키는 클래스 Random
			
			int random = r.nextInt(100000000);
			// 0부터 1억 미만사이의 정수숫자 난수 발생

			/*
			 * 첨부파일 확장자 구함
			 */
			int index = fileName.lastIndexOf(".");
			// 첨부파일에서 "."를 맨오른쪽에서 부터 찾아서 가장 먼저 나오는 해당 문자위치번호를 맨왼쪽  첫문자를 0부터 카운터해서 구한다.
			// 즉 abcd.jsp 라면 우측에서 부터 "."을 찾아서 abcd 다음에 . 이 나오므로 4가 반화노딘다.
			
			String fileExtendsion = fileName.substring(index+1); // .이후부터 마지막 문자까지 구한다. (첨부파일 확장자를 구함)
			String refFileName = "bbs"+year+month+date+random+"."+fileExtendsion; // 새로운 첨부파일명을 구함
			String fileDBName = "/"+year+"-"+month+"-"+date+"/"+refFileName; // 데이터베이스 저장될 값
			
			bbs.setBbs_file(fileDBName);
			
			File saveFile = new File(homedir+"/"+refFileName);
			// 서버에 업로드될 파일 객체
			
			try {
				uploadFile.transferTo(saveFile);
				// 업로드폴더에 오늘날짜로 새롭게 생성된 폴더에 변경된 첨부파일명으로 실제 업로드를 함
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
		// 첨부파일이 없는 경우
			
			String fileDBName = "";
			bbs.setBbs_file(fileDBName);
		}
		
		this.bbsService.insertBbs(bbs);
		
		return "redirect:/bbs/bbs_list"; // 새로운 매핑주소인 자료실목록으로 이동
	} // bbs_write_ok()
	
	// 자료실 페이징 목록
	@RequestMapping(value="bbs_list", method=RequestMethod.GET)
	// Get으로만 접근하는 매핑주소를 처리
	public ModelAndView bbs_list(HttpServletRequest request, BbsVO b, PageVO p) {
		
		int page=1;
		int limit=10; // 한 페이지에 보여지는 목록개수
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		String find_name = request.getParameter("find_name"); // 검색어
		String find_field = request.getParameter("find_field"); // 검색 필드
		p.setFind_field(find_field);
		p.setFind_name("%"+find_name+"%");
		// SQL문에서 % 와일드카드문자는 검색에서 하나이상의 임의의 모르는 문자와 매핑 대응
		
		int totalCount = this.bbsService.getRowCount(p);//검색전 총 레코드개수, 검색후 레코드개수
		
		p.setStartrow((page-1)*10+1); // 시작행번호
		p.setEndrow(p.getStartrow()+limit-1); // 끝행 번호
		
		List<BbsVO> blist = bbsService.getBbsList(p); // 검색 전후 목록
		
		
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

