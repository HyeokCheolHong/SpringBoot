<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>비번검색 공지창 입력폼</title>
		<link rel="stylesheet" type="text/css" href="./css/member.css" />
	</head>
	<body>
		<div id="pFind_wrap">
			<h2 class="pFind_title">비번찾기</h2>
			<form method="post" action="pwd_find_ok" onsubmit="return pwd_check();">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<%-- 
					Spring Security 에서는 post방식으로 데이터를 전송할 때는 반드시 csrf 토큰을 같이 보내야 한다.
					csrf 토큰을 같이 보내는 이유는 사이트 간 요청 위조 방지(CSRF) 때문이다. 
				--%>
				
				<table id="pFind_t">
					<tr>
						<th>회원 아이디</th>
						<td><input name="pwd_id" id="pwd_id" size="14" /></td>
					</tr>
					<tr>
						<th>회원 이름</th>
						<td><input name="pwd_name" id="pwd_name" size="14" /></td>
					</tr>
				</table>
				<div id="pFind_menu">
					<input type="submit" value="찾기" />
					<input type="reset" value="취소" onclick="$('#pwd_id').focus();"/>				
				</div>
			</form>
		</div>	
		
		<script src="https://code.jquery.com/jquery-latest.min,js"></script>
		<script type="text/javascript">
			function pwd_check(){
				if($.trim($("#pwd_id").val()).lengh == 0 ) {
					alert("회원아이디를 입력하세요!");
					$("#pwd_id").val("").focus();
					return false;
				}
				
				if($.trim($("#pwd_name").val()) == "") {
					alert("회원 이름을 입력하세요!");
					$("#pwd_name").val("").focus();
					return false;
				}
			}
		</script>
	</body>
</html>