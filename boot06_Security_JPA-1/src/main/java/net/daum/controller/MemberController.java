package net.daum.controller;

import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.daum.service.MemberService;
import net.daum.vo.MemberVO;

// 2024-12-12 Spring Security 실습

@Controller
public class MemberController { // 사용자 회원관리 컨트롤러
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 회원가입 폼
	@GetMapping("/member_join")
	public ModelAndView mem_join() {
		
		ModelAndView jm = new ModelAndView();
		jm.setViewName("member/member_Join"); 
		// 뷰페이지 경로(뷰 리졸브 경로) => /WEB-INF/views/member/member_join.jsp
		
		return jm;
	}
	
	// 아이디 중복검색
	@PostMapping("/member_idcheck")
	public String member_idcheck(@RequestParam("id") String id, HttpServletResponse response) throws Exception{
		// @RequestParam("id)로 하면 비동기식으로 하면 id파라미터 이름에 담겨져서(member.js $.ajax paramemter) 전달된 회원아이디를 구함.
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter(); // 출력 스트림 out를 생성
		
		MemberVO db_id = this.memberService.idcheck(id); // 아이디에 해당하는 회원정보를 오라클 DB로 부터 구함
		
		int re = -1;
		// 중복 아이디가 없는 경우 반환괎	
		
		if(db_id != null) { // 중복아이디가 있는경우
			re = 1;
		}
		out.println(re);
		return null;
	} // member_idcheck()
	
	// 2024-12-12 Spring Security 실습
	// 회원 저장
	@RequestMapping("/member_join_ok")
	public ModelAndView mem_join_ok(MemberVO m) {
		/*
		 * member_join.jsp의 네임파라미터 이름과 MemberVO빈클래스의 변수명이 같으면 MemberVo m에서 m에
		 * 가입폼에서 입력한 회원정보가 저장되어 있다.
		 */
		System.out.println("회원 아이디 : "+m.getMem_id());
		System.out.println("인코딩(암호화) 되기 전 비번 : "+m.getMem_pwd());
		m.setMem_pwd(passwordEncoder.encode(m.getMem_pwd())); // 비번 암호화
		System.out.println("인코딩 후(암호화 후) 비번 : " + m.getMem_pwd());
		System.out.println("회원 이름 : "+m.getMem_name());
		System.out.println("권한 목록 : "+m.getRoles().toString());
		
		// 저장
		this.memberService.insertMember(m);
		
		
		return new ModelAndView("redirect:/login");
		// 생성자 인자값에 redirect:/ 가 들어가면 새로운 매핑주소로 이동한다.
	}
	
	// 비번찾기 공지창 폼
	@GetMapping("/pwd_find")
	public String pwd_find() {
		
		return "member/pwd_find"; // 뷰페이지 경로가 /WEB-INF/views/member/pwd_find.jsp
	}
	
	// 비번찾기 결과
//	@PostMapping("/pwd_find_ok")
	@RequestMapping(value="/pwd_find_ok", method=RequestMethod.POST) // POST방식으로 전송되는 매핑주소를 처리
	public ModelAndView pwd_find_ok(@RequestParam("pwd_id") String pwd_id, String pwd_name, HttpServletResponse response, MemberVO m) throws Exception {
		
		/*
		 * @RequestParam("pwd_id") 스프링 애노테이션은 request.getParameter("pwd_id")와 기능이 같다.
		 */
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		m.setMem_id(pwd_id);
		m.setMem_name(pwd_name);
		
		MemberVO pm = this.memberService.pwdMember(m);
		// 아이디와 회원이름을 기준으로 오라클 DB로부터 회원정보를 검색
		
		if(pm == null) {
			out.println("<script>");
			out.println("alert('회원으로 검색 되지 않습니다!\\n 올바른 회원정보를 입력하세요!');");
			out.println("history.go(-1);");
			// go(-1) 또는 back()메서드는 이전주소로 이동, 뒤로 한칸 이동
			out.println("</script>");
		} else {
			Random r = new Random();
			// java.util 패키지의 Random 클래스는 정해지지 않은 난수를 발생
			
			int pwd_random = r.nextInt(100000);
			// 0이상 10만 미만사이의 정수 숫자 난수를 발생시킴.
			
			String ran_pwd = Integer.toString(pwd_random);
			// 임시 정수숫자 비번을 문자열 타입으로 변경
			
			m.setMem_pwd(passwordEncoder.encode(ran_pwd));
			// 임시 비번 암호화
			
			this.memberService.updatePwd(m);
			// 암호화 된 임시비번으로 수정
			
			ModelAndView fm = new ModelAndView("member/pwd_find_ok");
			// 생성자 인자값으로 뷰페이지 경로를 설정한다.
			// 뷰리졸브 경로는 /WEB-INF/views/member/pwd_find_ok.jsp
			
			fm.addObject("ran_pwd", ran_pwd);
			return fm;
		}
		
		return null;
	} // pwd_find_ok
}
