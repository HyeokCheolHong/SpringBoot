package net.daum.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		
		// 2024-12-17 Spring Project 실습
		// 총페이지 수
		int maxpage = (int)((double)totalCount/limit+0.95);
		
		// 시작페이지(1, 11, 21, ...)
		int startpage = (((int)((double)page/10+0.9))-1)*10+1;
		
		// 현재페이지에 보여질 마지막 페이지(10, 20, 30, ...)
		int endpage = maxpage;
		if(endpage > startpage+10-1) {
			endpage = startpage+10-1;
		}
		
		ModelAndView listM = new ModelAndView();
		
		listM.addObject("blist", blist); // blist 키이름에 검색전후 목록 저장
		listM.addObject("page", page); // 페이징목록에서 내가 본 쪽번호로 바도 이동하기위한 책갈피 기능때문에 쪽번호 저장
		listM.addObject("startpage", startpage); // 시작페이지
		listM.addObject("endpage", endpage); // 마지막페이지
		listM.addObject("maxpage", maxpage); //최대페이지
		listM.addObject("totalCount", totalCount); // 검색전후 레코드 개수
		listM.addObject("find_field", find_field); // 검색필드
		listM.addObject("find_name", find_name); // 검색어
		
		listM.setViewName("bbs/bbs_list"); // 뷰 페이지 경로 => /WEB-INF/views/bbs/bbs_list.jsp
		
		return listM;
	}
	
	// 내용보기(조회수증가)+답변폼+수정폼+삭제폼
	@RequestMapping("/bbs_cont")
	public ModelAndView bbs_cont(int bbs_no, String state, int page, BbsVO b) {
		
		if(state.equals("cont")) { // 내용보기 일때만 조회수 증가
			b = this.bbsService.getBbsCont(bbs_no);
		} else { // 내용보기가 아닌 경우 답변폼, 수정폼, 삭제폼일때는 조회수 증가 안함
			b = this.bbsService.getBbsCont2(bbs_no);
			
		}
		
		String bbs_cont = b.getBbs_cont().replace("\n", "<br/>");
		// textaread에서 Enter키 를 줄바꿈을 변경
		
		ModelAndView cm = new ModelAndView();
		cm.addObject("b", b);
		cm.addObject("bbs_cont", bbs_cont);
		cm.addObject("page", page); // 책갈피
		
		if(state.equals("cont")) { // 내용보기 일때
			cm.setViewName("bbs/bbs_cont"); // 뷰페이지 경로=> /WEB-INF/views/bbs/bbs_cont.jsp
		} else if(state.equals("reply")) { // 답변폼일때
			cm.setViewName("bbs/bbs_reply");
		} else if(state.equals("edit")) { // 수정폼일때
			cm.setViewName("bbs/bbs_edit");
		} else { // state=del 일때 즉 삭제폼일때
			cm.setViewName("bbs/bbs_del");
		}
		
		return cm;
	}
	
	// 답변 저장
	@PostMapping("/bbs_reply_ok")
	public String bbs_reply_ok(BbsVO rb, int page) {
		/*
		 * BbsVO rb는 네임파라미터 이름과 빈클래스 변수명이 같으면 
		 * rb객체에 히든값과 입력필드 값이 저장되어 있다.
		 * 하지만 page는 빈클래스에 변수명으로 지정되어 있지 않기 때문에
		 * int page로 별도로 페이지 번호를 가져와야 한다.
		 */
		this.bbsService.replyBbs(rb); // 답변저장
		return "redirect:/bbs/bbs_list?page="+page;
		// page=쪽번호를 get방식으로 전달해서 페이징에서 책갈피 기능을 구현한다.
	} // bbs_reply_ok()
	
	// 자료실 수정
	@RequestMapping(value="/bbs_edit_ok", method=RequestMethod.POST) // post로 접근하는 매핑주소를 처리
	public ModelAndView bbs_edit_ok(BbsVO bbs, Bbs2VO bbs2, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8"); // 웹브라우저 출력되는 문자와 태그, 언어코딩 타입을 지정
		
		PrintWriter out = response.getWriter();
		String uploadFolder = request.getSession().getServletContext().getRealPath("upload");
		// 수정 첨부된 파일이 업로드 될 upload 폴더실제 경로를 반환
		
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		BbsVO db_pwd = this.bbsService.getBbsCont2(bbs.getBbs_no());
		// 조회수가 증가되지 않는 상태에서 오라클로 DB로 부터 비번을 가져온다.
		
		if(!db_pwd.getBbs_pwd().equals(bbs.getBbs_pwd())) {
			out.println("<script>");
			out.println("alert('비번이 다릅니다!');");
			out.println("history.back();");
			out.println("</script>");
		} else {
			MultipartFile uploadFile = bbs2.getUploadFile();
			// 첨부된 파일을 가져온다.
			
			if(!uploadFile.isEmpty()) { // 수정 첨부파일이 있는 경우
				String fileName = uploadFile.getOriginalFilename();
				// 수정첨부된 원본 파일명을 구함
				
				File delFile = new File(uploadFolder+db_pwd.getBbs_file());
				// 삭제할 파일 객체 생성 (폴더 + db로부터 가져온 폴더 경로)
				
				if(delFile.exists()) { // 기존 삭제할 파일이 있다면
					delFile.delete(); // 기존 첨부파일만 삭제
				}
				
				Calendar cal = Calendar.getInstance();
				
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH)+1;
				int date = cal.get(Calendar.DATE);
				
				String homedir = uploadFolder+"/"+year+"-"+month+"-"+date;
				File path01 = new File(homedir);
				if(!(path01.exists())) {
					path01.mkdir(); // 오늘날짜 폴더 생성
				}
				
				Random r = new Random();
				int random = r.nextInt(100000000);
				
				// 첨부파일 확장자를 구함
				int index = fileName.lastIndexOf("."); // 마침표 위치번호를 구함
				String fileExtendsion = fileName.substring(index+1);
				// . 이후부터 마지막 문자까지 구함 => 결국 수정첨부파일 확장자만 구함
				
				String reffileName = "bbs"+year+month+date+random+"."+fileExtendsion; // 새로운 첨부파일명
				String fileDBName = "/"+year+"-"+month+"-"+date+"/"+reffileName; // 오라클에 저장된 레코드 값
				
				bbs.setBbs_file(fileDBName);
				
				File saveFile = new File(homedir+"/", reffileName);
				try {
					uploadFile.transferTo(saveFile);
					// upload폴더에 오늘날짜 생성된 폴더에 변경된 파일로 실제 업로드함.
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else { // 수정 첨부파일이 없는 경우
				String fileDBName = "";
				if(db_pwd.getBbs_file() != null) { // 기존 첨부파일이 있는 경우
					bbs.setBbs_file(db_pwd.getBbs_file());
				} else {
					bbs.setBbs_file(fileDBName);
				}
			} // if else
			
			this.bbsService.editBbs(bbs); // 잘실 수정
			
			ModelAndView em = new ModelAndView("redirect:/bbs/bbs_cont");
			em.addObject("bbs_no", bbs.getBbs_no());
			em.addObject("page", page);
			em.addObject("state", "cont");
			return em;
			// 주소창에 노출되는 get방식으로 다음과 같이 전달된다.
			// bbs_cont?bbs_no=번호&page=쪽번호&state=cont
		}
		
		
		return null;
	}
	
	
	// 자료실 삭제
	@RequestMapping("/bbs_del_ok") // get or post방식으로 전송되는 매핑주소 처리
	public String bbs_del_ok(int bbs_no, int page, @RequestParam("del_pwd") String del_pwd, HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		String delFolder = request.getSession().getServletContext().getRealPath("upload"); // upload 실제 경로 반환
		
		BbsVO db_pwd = this.bbsService.getBbsCont2(bbs_no);
		if(!db_pwd.getBbs_pwd().equals(del_pwd)) {
			out.println("<script>");
			out.println("alert('비번이 다릅니다!');");
			out.println("history.go(-1);");
			out.println("</script>");
		} else {
			this.bbsService.delBbs(bbs_no); // Oracle 로부터 레코드 삭제
			
			if(db_pwd.getBbs_file() != null) { // 기존 첨부파일이 있다면
				File delFile = new File(delFolder+db_pwd.getBbs_file());
				// 삭제할 파일 객체 생성
				
				delFile.delete();
				// 폴더는 삭제 안되고, 기존 첨부파일만 삭제된다.
			}
			
			return "redirect:/bbs/bbs_list?page="+page;
		}
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

