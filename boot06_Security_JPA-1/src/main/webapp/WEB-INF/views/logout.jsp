<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그아웃</title>
	</head>
	<body>
		<h2>Custom LogOut Page</h2>
		
		<form method="post" >
		<%-- 
			action속성이 생략되면 이전 매핑주소인 /logout이 action속성값이 된다.
			동일 매핑주소 구분은 메서드 방식으로 한다.
		--%>
			<h3>LogOut</h3>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<%--
				_csrf 는 사이트간 요청 위조를 방지하기 위한 것으로, 
				요청을 보내는 URI에서 서버가 가지는 동일한 값과 같은 값을 가지고 데이터 전송할 때에만 신뢰하는 방법이다.
				스프링 시큐리티가 적용되면 post방식으로 보내는 데이터는 CSRF 토큰값이 필요하다.
			--%>
			<button type="submit" class="btn">로그아웃</button>
		</form>
	</body>
</html>