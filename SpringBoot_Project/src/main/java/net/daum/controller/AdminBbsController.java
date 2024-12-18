package net.daum.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.daum.service.AdminBbsService;
import net.daum.vo.BbsVO;
import net.daum.vo.PageVO;

//2024-12-18 Spring Admin Project 실습

@Controller
public class AdminBbsController {
	
	@Autowired
	private AdminBbsService adminBbsService;
	
	// 관리자 자료실 목록
	@GetMapping("/admin_bbs_list")
	public ModelAndView admin_bbs_list(BbsVO b, PageVO p, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception{
		
		response.setContentType("text/html;charset=UTF-8");
		
		if(isAdminLogin(session, response)) {
			int page=1;
			int limit=7; // 한 페이지에 보여지는 목록개수
			if(request.getParameter("page") != null) {
				page=Integer.parseInt(request.getParameter("page"));
			}
			
			String find_name = request.getParameter("find_name"); // 검색어
			String find_field = request.getParameter("find_field"); // 검색 필드
			p.setFind_field(find_field);
			p.setFind_name("%"+find_name+"%");
			// SQL문에서 % 와일드카드문자는 검색에서 하나이상의 임의의 모르는 문자와 매핑 대응
			
			int totalCount = this.adminBbsService.getRowCount(p);//검색전 총 레코드개수, 검색후 레코드개수
			
			p.setStartrow((page-1)*limit+1); // 시작행번호
			p.setEndrow(p.getStartrow()+limit-1); // 끝행 번호
			
			List<BbsVO> blist = adminBbsService.getBbsList(p); // 검색 전후 목록
			
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
			
			listM.setViewName("admin/admin_bbs_list"); // 뷰 페이지 경로 => /WEB-INF/views/admin/admin_bbs_list.jsp
			
			return listM;
		}
		
		return null;
	}
	
	// 관리자 자료실 글쓰기
	@RequestMapping("/admin_bbs_write")
	public ModelAndView admin_bbs_write(HttpServletResponse response, HttpSession session, HttpServletRequest request) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		
		if(isAdminLogin(session, response)) { // ==true가 생략됨.
			// true이면 관리자 로그인 상태
			int page = 1;
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			ModelAndView wm = new ModelAndView("admin/admin_bbs_write");
			wm.addObject("page", page); // 페이징에서 책갈피 기능때문에 쪽번호를 page키이름에 저장
			
			return wm;
		}
		
		
		return null;
	}
	
	
	// 세션이 만료되었을 때나 로그아웃 된 이후에 반복적인 관리자 로그인 매핑주소로 이동하기 위한 코드
	public static boolean isAdminLogin(HttpSession session, HttpServletResponse response) throws Exception {
		
		PrintWriter out = response.getWriter();
		
		String admin_id=(String)session.getAttribute("admin_id"); // 세션 관리자 아이디를 구함
		
		if(admin_id == null) {
			out.println("<script>");
			out.println("alert('관리자로 다시 로그인 하세요!');");
			out.println("location='admin_Login';");
			out.println("</script>");
			
			return false;
		}
		
		return true; // 관리자 로그인 된 경우
	}
}
