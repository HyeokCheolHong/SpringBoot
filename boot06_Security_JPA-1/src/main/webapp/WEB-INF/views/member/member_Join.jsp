<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="_csrf_header" content="${_csrf.headerName}" >
		<meta name="_csrf" content="${_csrf.token}" >
		<%-- 
			비동기식 Ajax 통신 시 403 forbidden(권한없음) 에러 발생 해결법
			-> csrf(Cross-site request forgery)의 token이 누락으로 발생하기 때문에
				위 meta 코드 2개를 추가
		--%>
		<link rel="stylesheet" type="text/css" href="./css/member.css" />
		<title>회원가입 폼</title>
	</head>
	<body>
		<div id="mJoin_wrap">
			<H2 class="mJoin_title">회원가입</H2>
			<form method="post" action="member_join_ok" onsubmit="return join_check();">
				<table id="mJoin_t">
					<tr>
						<th>회원아이디</th>
						<td>
							<input name="mem_id" id="mem_id" size="14" class="input_box" />
							<button type="button" class="input_b" onclick="id_chcek();" >아이디 중복검색</button> <br/>
							<span id="idcheck"></span>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="mem_pwd" id="mem_pwd" size="14" class="input_box" /></td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td><input type="password" name="mem_pwd2" id="mem_pwd2" size="14" class="input_box" /></td>
					</tr>
					<tr>
						<th>회원 이름</th>
						<td><input name="mem_name" id="mem_name" size="14" class="input_box" /></td>
					</tr>
					<tr>
						<th>회원 권한</th>
						<td>
							<input type="checkbox" name="roles[0].roleName" value="BASIC" checked />BASIC
							<input type="checkbox" name="roles[1].roleName" value="MANAGER" checked /> MANAGER
							<input type="checkbox" name="roles[2].roleName" value="ADMIN" checked /> ADMIN
							<%-- MemberVO 엔티티빈 클래스에서 List<MemberRole>로 처리되기 때문에 [0]은 컬렉션 주소 첫번째 인덱스 번호로 처리 --%>							
						</td>
					</tr>
				</table>
				
				<div id="mJoin_menu">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<%-- post 방식으로 데이터를 전송할 때는 스프링 시큐리티에서는 CSRF 토큰을 같이 보내야 한다. --%>
					<button type="submit" class="input_b">가입</button>
					<button type="reset" class="input_b" onclick="$('#mem_id').focus();">취소</button>
				</div>
			</form>
		</div>
		<script src="https://code.jquery.com/jquery-latest.min,js"></script>
		<script type="text/javascript" src="./js/member.js"></script>
	</body>
</html>