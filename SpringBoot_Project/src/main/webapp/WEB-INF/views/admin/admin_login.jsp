<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자 로그인</title>
	<link rel="stylesheet" type="text/css" href="./css/admin.css" />
</head>
<body>
	<div id="aLogin_wrap">
		<h2 class="aLogin_title">관리자 로그인</h2>
		<form method="post" onsubmit="return admin_check();">
		<%-- 
			폼태그에서 action속성을 생략하면 이전 매핑주소인 admin_Login이 액션속성값이 된다.
			동일매핑주소 구분은 메서드 방식으로 한다.
		--%>
			<table id="aLogin_t">
				<tr>
					<th>관리자 아이디</th>
					<td><input name="admin_id" id="admin_id" size="14"
						tabindex="1" />
					<%-- tabindex="1"로 설정하면 탭키를 눌렀을 때 첫번째로 포커스를 가짐. --%></td>
					<th rowspan="2"><button type="submit">로그인</button></th>
				</tr>
				<tr>
					<th>관리자 비밀번호</th>
					<td><input type="password" name="admin_pwd" id="admin_pwd"
						size="14" tabindex="2" />
					<%-- tabindex="2"로 설정하면 탭키를 눌렀을때 2번째로 포커스를 가짐. --%></td>
				</tr>
			</table>
		</form>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
	 function admin_check(){
	    if($.trim($("#admin_id").val()) == ""){
	       alert("관리자 아이디를 입력하세요!");
	       $("#admin_id").val("").focus();
	       return false;
	    }
	    if($.trim($("#admin_pwd").val()) == ""){
	       alert("관리자 비번을 입력하세요!");
	       $("#admin_pwd").val("").focus();
	       return false;
	    }
	 }
	</script>
</body>
</html>