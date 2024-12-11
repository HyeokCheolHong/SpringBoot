package net.daum.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.extern.java.Log;

// 2024-12-11 Spring Security 실습

@Log
@EnableWebSecurity // 스프링 웹 시큐리티로 인식되게함
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// 스프링 웹 시큐리티 설정을 담당하는 WebSecurityConfigurerAdapter 클래스 상속을 받는다
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	// HttpSecurity는 웹과 관련된 다양한 보안설정을 걸어 줄 수 있다.
		
		log.info("security config ......");
		
		http.authorizeRequests().antMatchers("/guest/**").permitAll();
		// authorizeRequests는 시큐리티 처리에서 HttpServlet Request에 해당한다.
		// antMatchers()에서는 특정한 경로를 지정한다.
		// permitAll()은 모든 사용자가 접근 할 수 있다는 것을 의미한다.
		
		http.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER");
		// hasRole()은 특정권한을 가진 사람만이 접근할 수 있다는 것을 의미한다.
		
//		http.formLogin();
//		// 스프링 시큐리티에서 제공하는 로그인 페이지가 아닌 사용자 커스텀 로그인 페이지를 사용하기 위한 주석처리
		// http.formLogin() form 태그 기반의 로그인 지원한다는 의미이다.
		// 이를 이용하면 별도의 커스텀 로그인 페이지를 작성하지 않아도 스프링 시큐리티에서 제공하는 /login 매핑주소로 인식되는 기본 로그인 페이지가 띄워진다.
		
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
		/*
		 * admin으로 접근할 때는 'ADMIN' 권한이 있어야만 하는 설정을 추가
		 */
		
		http.formLogin().loginPage("/login");
		// loginPage("/login")을 사용하면 매핑주소가 login인 즉 커스텀 로그인 페이지를 별도로 만들 수 있다.
		// loginController.java
		
		http.exceptionHandling().accessDeniedPage("/accessDenied");
		// 403 접근금지 에러가 발생했을때 실행
		
		http.logout().logoutUrl("/logout").invalidateHttpSession(true); // 세션 무효화 : 로그아웃
		// invalidateHttpSession(true) : 세션 무효화
		
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// 로그인 되게 만들기 위해서는 AuthenticationMangaerBuilder을 주입해서 인증에 대한 처리를 해야 한다.
		// 예를들어 메모리상에 정보만을 이용한다든지, jdbc등을 이용해서 인증처리가 가능한다. 
		// 여기서는 메모리상의 인증 정보를 활용한다.
		
		log.info("메모리상의 로그인 인증처리");
		
		auth.inMemoryAuthentication().withUser("manager").password("{noop}1111").roles("MANAGER");
		/*
		 * 사용자 manager, 비번 1111, 권한 MANAGER로 지정
		 * Spring Security 4 에서는 메모리 내의 인증을 사용하여 암호를 인코딩 즉 암호화 하지 않고 일반 텍스트로 저장할 수 있었다.
		 * Spring Security 5 에서는 비번을 인코딩 즉 암호화해서 저장한다. 그러므로 There is no PasswordEncoder mapped for the id "null" 오류를 내지 않기 위해서는
		 *  {noop}을 사용해서 비번을 인코딩 즉 암호화 해서 처리해야 한다.
		 */
	}
	
	

}
