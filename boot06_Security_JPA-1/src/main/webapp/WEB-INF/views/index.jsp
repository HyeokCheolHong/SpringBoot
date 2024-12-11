<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- 2024-12-11 Spring Security 실습 -->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>index.jsp</title>
		<link rel="stylesheet" type="text/css" href="./css/member.css" />
	</head>
	<body>
		<c:if test="${empty id}"> <%-- 로그인 전 --%>
			<div id="Index_wrap">
				<h1 class="title_size">Index Page for EveryOne!</h1>
				<span class="login_a"><a href="/login">로그인</a></span>
			</div>
		</c:if>
		
		<c:if test="${!empty id}"> <%-- 로그인 이후 --%>
			<div id="Index_wrap">
				<h1 class="title_size">Index Page for EveyOne!</h1>
				<h2 class="Index_title">로그인 후 메인 화면</h2>
				
				<table id="Index_t">
					<tr>
						<th><button type="button">정보수정</button> <button type="button" onclick="location='logout';">로그아웃</button></th>
					</tr>
				</table>
			</div>
		</c:if>
	</body>
</html>