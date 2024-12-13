package net.daum.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import net.daum.vo.MemberRole;
import net.daum.vo.MemberVO;

// 2024-12-13 Spring 사용자 인증 실습

@Setter
@Getter
public class ZerockSecurityUser extends User{

	private static final String ROLE_PREFIX = "ROLE_"; 
	// 권한 이름 앞에 ROLE_를 붙인다. 이 접두어를 정적 상수에 저장
	
	private MemberVO member;
	
	public ZerockSecurityUser(MemberVO member, HttpServletRequest request) {
		super(member.getMem_id(), member.getMem_pwd(), makeGrantedAuthority(member.getRoles()));
		// 부모클래스 생성자를 호출해서 인자값으로 아이디, 비번, 권한 목록을 전달함
		
		System.out.println("권한 이름 : "+makeGrantedAuthority(member.getRoles()).toString());
		HttpSession session = request.getSession();
		session.setAttribute("id", member.getMem_id());
		// 세션 id에 아이디 저장
		session.setAttribute("name", member.getMem_name());
		
		List<GrantedAuthority> list = makeGrantedAuthority(member.getRoles());
		// 권한 목록
		
		String total_Auth = ""; // 누적권한
		
		for(int i=0; i<list.size();i++) {
			System.out.println(list.get(i));
			total_Auth += list.get(i);
		}
		
		System.out.println("누적 권한 : " + total_Auth);
		session.setAttribute("total_Auth", total_Auth);
	}
	
	private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles) {
		// 메서드 인자값으로 권한 목록이 전달됨  (Granted : 부여된 / Authority : 권한)
		
		List<GrantedAuthority> list = new ArrayList<>();
		
		roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));
		// 권한 목록만큼 반복해서 권한이름앞에 접두어 "ROLE_" 를 붙여서 생성자 인자값으로 전달한 다음 이것을 컬렉션에 추가
		
		return list;
	}
}
