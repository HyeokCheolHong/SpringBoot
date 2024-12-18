package net.daum.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.daum.service.AdminService;
import net.daum.vo.AdminVO;
import pwdconv.PwdChange;

// 2024-12-18 Spring Admin Project 실습

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	// 관리자 로그인 페이지
	@GetMapping("/admin_Login")
	public ModelAndView admin_Login() {
		return new ModelAndView("admin/admin_login");
		// 뷰페이지 경로는 /WEB-INF/views/admin/admin_login.jsp
	}
	
	// 관리자 정보 저장과 로그인 인증, 비번 암호화
	@PostMapping("/admin_Login")
	public String admin_Login(AdminVO ab, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception {
		// ab 객체로 관리자의 ID와 비번을 쉽게 가져올 수있다.
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		ab.setAdmin_pwd(PwdChange.getPassWordToXEMD5String(ab.getAdmin_pwd())); // 비번 암호화
		
//		ab.setAdmin_no(1);
//		ab.setAdmin_name("관리자");
//		this.adminService.insertAdmin(ab); 
//		// 관리자 정보 저장
//		// 	관리자 아이디, 암호화 된 비번, 관리자 번호, 관리자 이름
		
		AdminVO admin_info = this.adminService.adminLogin(ab.getAdmin_id()); // 관리자 로그인 인증
		
		if(admin_info == null) {
			out.println("<script>");
			out.println("alert('관리자 정보가 없습니다!');");
			out.println("history.go(-1);");
			out.println("</script>");
		} else {
			if(!admin_info.getAdmin_pwd().equals(ab.getAdmin_pwd())) {
				out.println("<script>");
				out.println("alert('관리자 비밀번호가 다릅니다!');");
				out.println("history.back();");
				out.println("</script>");
			} else {
				session.setAttribute("admin_id", ab.getAdmin_id());
				// admin_id 세션키이름에 관리자 아이디를 저장
				
				session.setAttribute("admin_name", admin_info.getAdmin_name());
				// 관리자 이름을 세션에 저장
				
				return "redirect:/admin_main";
				// 관리자 로그인 인증후 관리자 메인페이지로 이동
			}
		} 
		
		return null;
	} // admin_Login()
	
	// 관리자 로그인 인증후 메인페이지
	@GetMapping("/admin_main")
	public ModelAndView admin_main(HttpServletResponse response, HttpSession session) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String admin_id = (String)session.getAttribute("admin_id"); // 관리자 세션 아이디를 구함
		
		if(admin_id == null) {
			out.println("<script>");
			out.println("alert('관리자로 다시 로그인 하세요!');");
			out.println("location='admin_Login';");
			out.println("</script>");
		} else {
			ModelAndView am = new ModelAndView();
			am.setViewName("admin/admin_main");
			// 뷰페이지(뷰리졸브) 경로 : /WEB-INF/views/admin/admin_main.jsp
			return am;
		}
		
		return null;
	}
	
	// 관리자 로그아웃
	@RequestMapping("/admin_logout")
	public String admin_logout(HttpSession session, HttpServletResponse response, HttpServletRequest request) throws Exception{
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		session.invalidate(); // 세션 만료 => 로그아웃
		
		out.println("<script>");
		out.println("alert('관리자 로그아웃 되었습니다!');");
		out.println("location='admin_Login';");
		out.println("</script>");
		
		return null;
	}
}
