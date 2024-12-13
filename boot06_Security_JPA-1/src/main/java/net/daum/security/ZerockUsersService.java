package net.daum.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;
import net.daum.dao.MemberRepository;


//2024-12-13 Spring 사용자 인증 실습

@Service // 스프링에 빈으로 등록
@Log 
public class ZerockUsersService implements UserDetailsService {

	/*
	 * ZerockUsersService 별도의 인증/권한 체크를 하는 이유는 jsp등에서 단순히 사용자 아이디(Spring Security에서는 username) 정도가 아닌
	 * 사용자 이름이나 이메일 같은 추가적인 정보를 이용하기 위해서 이다.
	 */
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 회원아이디를 이용해서 User Details 객체를 반환
		
		System.out.println(" \n ====================> UserDetailsService로 접근");
		
		return 
				this.memberRepo.findById(username)
				.filter(member -> member != null) 
				// 검색된 회원정보가 있다면
				
				.map(member -> new ZerockSecurityUser(member, request)).get();
				// 검색된 회원정보(권한정보)를 ZerockSecurityUser 생성자 인자값으로 전달하고 ZerockSecurityUser 객체타입을 get() 메서드로 구함
	}

}
