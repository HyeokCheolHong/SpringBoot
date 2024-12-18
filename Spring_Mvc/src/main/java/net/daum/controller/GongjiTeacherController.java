package net.daum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import net.daum.service.GongjiTeacherService;
import net.daum.vo.GongjiTeacherVO;

//2024-12-05 문제) 공지사항 만들기 정답

@Controller
public class GongjiTeacherController {
	
	@Autowired
	private GongjiTeacherService gongjiTeacherService;
	
	// 공지 글쓰기폼
	@GetMapping("/gongji/gongji_teacher_write")
	public ModelAndView g_write(HttpServletRequest request) {
		
		int page = 1;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		request.setAttribute("page", page);
		
		return new ModelAndView("gongji/gongji_teacher_write");
	} // g_write()
	
	// 공지 저장
	@PostMapping("/gongji/gongji_teacher_write")
	public String gongji_insert(GongjiTeacherVO g ) {
		this.gongjiTeacherService.insert(g); // 공지저장
		return "redirect:/gongji/gongji_teacher_list";
	} // gongji_insert()
	
	// 페이징 공지목록
	@GetMapping("/gongji/gongji_teacher_list")
	public void gongji_list(GongjiTeacherVO g, HttpServletRequest request, Model m) {

		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		g.setStartrow((page-1)*10+1);
		g.setEndrow(g.getStartrow()+limit-1);
		
		List<GongjiTeacherVO> glist = this.gongjiTeacherService.getGongjiTeacherList(g);
		
		int totalCount = this.gongjiTeacherService.getTotalCount();
		int maxpage = (int)((double)totalCount/limit+0.95);
		int startpage = (((int)((double)page/10+0.9))-1)*10+1;
		int endpage = maxpage;
		if(endpage > startpage+10-1) endpage = startpage+10-1;
		
		
		m.addAttribute("totalCount", totalCount);
		request.setAttribute("glist", glist);
		m.addAttribute("startpage", startpage);
		m.addAttribute("endpage", endpage);
		m.addAttribute("maxpage", maxpage);
		m.addAttribute("page", page);
	};
	
	@GetMapping("/gongji/gongji_teacher_cont")
	public ModelAndView getGongjiTeacherView(int gno, int page) {
		
		this.gongjiTeacherService.updateTeacherHit(gno);
		
		GongjiTeacherVO gc = this.gongjiTeacherService.getGongjiTeacherCont(gno);
		
		String gcont = gc.getGcont().replace("\n", "<br/>");
		
		ModelAndView cm = new ModelAndView();
		cm.addObject("gc", gc);
		cm.addObject("page", page);
		cm.addObject("bcont", gcont);
		
		cm.setViewName("gongji/gongji_teacher_cont");
		
		return cm;
	};
	
	// 게시판 수정 폼 코드 작성
	@GetMapping("/gongji_teacher_edit")
	public String gongji_teacher_edit(Model m, @RequestParam int gno, int page) {
		
		GongjiTeacherVO gc = this.gongjiTeacherService.getGongjiTeacherCont(gno);
		
		m.addAttribute("gc", gc);
		m.addAttribute("page", page);

		return "gongji/gongji_teacher_edit";
	};
	
	
	// 게시판 수정 완료 코드
	@PostMapping("/gongji_teacher_edit_ok")
	public ModelAndView gongji_teacher_edit_ok(GongjiTeacherVO eg, int page) {
		
		this.gongjiTeacherService.updateTeacherGongji(eg);
		
		ModelAndView em = new ModelAndView();
		
		em.setViewName("redirect:/gongji_teacher_cont");
		em.addObject("gno", eg.getGno());
		em.addObject("page", page);
		
		return em;
	};
	
	// 게시판 삭제 코드
	@DeleteMapping("/gongji_teacher_del")
	public ModelAndView gongji_teacher_del(int gno, int page) {
		this.gongjiTeacherService.deleteTeacherGongji(gno);
		
		ModelAndView dm = new ModelAndView("redirect:/gongji/gongji_teacher_list");
		
		dm.addObject("page", page);
		
		return dm;
	}
}
