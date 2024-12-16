package net.daum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import net.daum.service.GuestBookService;
import net.daum.vo.GuestBookVO;
import net.daum.vo.PageVO;

// 2024-12-13 Spring Gradle Project 실습

@Controller
@RequestMapping("/guestbook/*")
public class GuestBookController {

	@Autowired
	private GuestBookService guestBookService;
	
	//방명록 글쓰기 폼
		@GetMapping("/guestbook_write")
		public void guestbook_write(Model m, HttpServletRequest request) {

			int page=1;		
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			m.addAttribute("page", page);//페이징 목록에서 책갈피기능을 구현
		}
	
	// 방명록 저장
	@PostMapping("/guestbook_write")
	public ModelAndView guestbook_write(GuestBookVO g) {
		
		/*
		 * 문제) JPA로 방명록 저장되게 해보자
		 */
		this.guestBookService.insertGuestBook(g);
		return new ModelAndView("redirect:/guestbook/guestbook_list");
	}
	
	// 방명록 목록
	@GetMapping("/guestbook_list")
	public ModelAndView guestbook_list(HttpServletRequest request, GuestBookVO g, PageVO p) {
		
		// paging 관련
		int page = 1;
		int limit = 10;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		/*
		 * 문제) 총 레코드 개수를 JPA를 사용해서 구한 다음 출력해 보자
		 */
		long totalCount = this.guestBookService.getTotalCount(); // 총레코드 개수
		System.out.println("총 게시물 수: " + totalCount+" 개");
		
		p.setStartrow((page-1)*10+1); // 시작 행번호
		p.setEndrow(p.getStartrow()+limit-1); // 끝 행번호
		
		List<GuestBookVO> glist = this.guestBookService.getGuestBookList(p); // 페이징 목록
		
		System.out.println("페이징 목록 개수 : "+glist.size() + " 개");
		
		// 총 게시물 수
		int maxpage = (int)((double)totalCount/limit+0.95);
		// 현재 페이지에 보여질 시작페이지
		
		int startpage = (((int)((double)page/10+0.9))-1)*10+1;
		// 현재페이지에 보여질 마지막 페이지
		
		int endpage = maxpage;
		
		if(endpage > startpage+10-1) endpage = startpage+10-1;
		
		ModelAndView listM = new ModelAndView("guestbook/guestbook_list");
		// 생성자 인자값으로 뷰페이지 경로
		
		listM.addObject("totalCount", totalCount);
		// totalCount 키에 총 게시물 수를 저장
		
		listM.addObject("glist", glist);
		// glist 키에 목록을 저장
		
		listM.addObject("startpage", startpage);
		listM.addObject("endpage", endpage);
		listM.addObject("maxpage", maxpage);
		listM.addObject("page", page);
		return listM;
	}
	
	// 조회수 증가와 내용보기
	@GetMapping("/guestbook_cont")
	public ModelAndView guestbook_cont(int gno, int page) {
		
		GuestBookVO g = this.guestBookService.getGCont(gno);
		
		String guestcont = g.getGuest_cont().replace("\n", "<br/>");
		// textarea입력필드에서 엔터키를 웹브라우저에서 줄바꿈에서 내용을 보여준다
		
		ModelAndView gm = new ModelAndView("guestbook/guestbook_cont");
		/*
		 * ModelAndView 생성자 인자값으로 들어간느 값
		 * 	1. 뷰페이지 경로 => WEB-INF/views/guestbook/guestbook_cont.jsp
		 * 	2.redirect:/매핑주소 가 들어갈 수 있다.
		 */
		
		gm.addObject("g", g);
		gm.addObject("guestcont", guestcont);
		gm.addObject("page", page);
		return gm;
	}
	
	
}
