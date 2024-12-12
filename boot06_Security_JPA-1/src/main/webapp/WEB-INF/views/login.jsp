<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인 폼</title>
		<link rel="stylesheet" type="text/css" href="./css/member.css"/>
	</head>
	<body>
		
		<%-- 로그인 전 화면 --%>
		<div id="Login_wrap">
			<h2 class="Login_title">로그인 폼</h2>
			<div style="margin:0px 0px 0px 50px;">
				<c:if test="${param.error != null}">
					<h2>Invalid Username or password</h2>
					<h2><c:out value="${param.error}" /></h2>
				</c:if>
			</div>	
			
			<!-- 로그아웃 되었다는 창 화면 -->
			<div style="margin:0px 0px 0px 50px;">
				<c:if test="${param.logout != null}">
					<h2>You have been logged out.</h2>
				</c:if>
			</div>
			
			<form method="post" onsubmit="return login_check();">
				<%-- 
					action속성을 생략하면 이전 매핑주소인 login이 action속성 값이 된다.
					동일 매핑주소 구분은 메서드 방식으로 구분한다.
					스프링 시큐리티가 적용되면 post방식으로 보내는 데이터는 CSRF 토큰값이 필요하다.
				--%>
				<table id="Login_t">
					<tr>
						<th>아이디</th>
						<td><input name="username" id="username" size="14" tabindex="1" /></td>
						<%-- 
							type 속성을 생략하면 input에서는 기본값이 text이다.
							 Spring Sequrity에서는 로그인 입력폼 아이디 입력필드 네임파라미터 이름은 username으로 설정해야 한다.
							 tabindex="1"로 지정하면 탭키를 눌렀을 때 첫번째로 포커스를 가진다.
						--%>
						
						<th rowspan="2">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<%--
								_csrf 는 사이트간 요청 위조를 방지하기 위한 것으로, 
								요청을 보내는 URI에서 서버가 가지는 동일한 값과 같은 값을 가지고 데이터 전송할 때에만 신뢰하는 방법이다.
							--%>
							<button type="submit">로그인</button>
						</th>
					</tr>
					
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="password" id="password" size="14" tabindex="2" /></td>
						<%-- Spring Security에서는 로그인 입력폼의 비번 입력필드 네임파라미터 이름은 password로 해야 한다. --%>
					</tr>
					
					<tr>
						<td colspan="3">
							Remember-Me : 
							<input type="checkbox" name="remember-me" id="remember-me" />
							<%-- 
								Spring Security에서는 자동 로그인 유지 기능을 쿠키를 사용해서 remeber-me 라는 이름으로 특정 토큰 데이터를 2주간 저장 유지한다.
								체크박스 네임파라미터 이름은 remember-me로 지정을 해야한다.
								로그인 화면에서 remember-me 체크박스를 선택하고 로그인 화면 웹브라우저 상에 remember-me라는 쿠키가 만들어 진다.
								생성된 'remember-me' 쿠키유효시간은 로그인 시간 + 2주 이다.
								쿠키는 유효시간이 설정되면 브라우저 내부에 저장한다.
								따라서 사용자가 브라우저를 종료하고 다시 브라우저를 실행한 다음 같은 서버상의 도메인 주소로 접근하면 자동으로 브라우저에 보관된 
									'remember-me'쿠키는 그대로 가진 상태에서 서버에 접근해서 자동 로그인 기능이 유지된다.
								물론 로그아웃 하면 브라우져의 저장된 'remember-me' 쿠키는 삭제되어서 자동 로그인 기능을 유지하지 못한다.
								
								Spring Security는 기본적으로 'remember-me'라는 기능을 사용하기 위해서 'Hash-based Token 저장방식' 또는 'Persistent Token' 저장방식을 사용할 수 있다.
								아무런 설정하지 않으면 통상 'Hash-based Token'을 사용한다.
							--%>
						</td>
					</tr>
				</table>
				
				<div id="Login_menu">
					<button type="button" onclick="pwd_find();">비밀번호 찾기</button>
					<button type="button" onclick="location='member_join';">회원가입</button>
				</div>
			</form>
		</div>

		
		
		<script src="https://code.jquery.com/jquery-latest.min,js"></script>
		<script>
			function login_check() {
				if($.trim($('#username').val()).length === 0) {
					alert('로그인 아이디를 입력하세요!');
					$('#username').val('').focus();
					return false;
				}
				if($.trim($('#password').val()).length == '') {
					alert('로그인 비번을 입력하세요!');
					$('#password').val('').focus();
					return false;
				}
			} // 로그인 인증 유효성 검증
			
			<%-- 비밀번호 찾기 --%>
			function pwd_find() {
				let url = 'pwd_find'; // 매핑주소 저장
				window.open(url, '비번검색', 'windth=400px, height=300px, scrollbars=yes');
				/*
					open(공지창경로, 공지창이름, 속성)메서드로 폭이 400px, 높이가 300px, 스크롤바가 생성되는 비번찾기 공지창이 만들어 진다.
				*/
			}
		</script>
	</body>
</html>