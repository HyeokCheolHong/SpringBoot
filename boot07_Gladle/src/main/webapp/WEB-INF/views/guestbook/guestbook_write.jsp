<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>

<!-- 2024-12-13 Spring Gradle Project 실습 -->

<html>
	<head>
		<meta charset="UTF-8">
		<title>스프링 MVC 방명록 글쓰기</title>
	</head>
	<body>
		<h2>스프링 Gradle방식으로 방명록 글쓰기</h2>
		<form method="post" onsubmit="return write_check();">
		<%-- 
			폼태그에서 action속성을 생략하면 이전 매핑주소인 guestbook_write가 action속성 값이 된다.
			동일 매핑주소 구분은 메서드방식으로 한다.
		--%>
			<label for="guest_name">글쓴이</label>
			<input name="guest_name" id="guest_name" size="14" placeholder="글쓴이를 입력하세요!"  /><br/>
			<span id="error_write"></span> <%-- 해당 영역에 에러메시지가 출력 --%> <br/><br/>
			
			<label for="guest_title">글제목</label>
			<input name="guest_title" id="guest_title" size="36" placeholder="글제목을 입력하세요!" /><br/>
			<span id="error_title"></span> <br/><br/>
			
			<label for="guest_cont">글내용</label>
			<textarea name="guest_cont" id="guest_cont" rows="10" cols="36"></textarea><br/>
			<span id="error_content"></span>
			<hr/>
					
			<button type="submit" >글쓰기</button>
			<button type="reset" onclick="reset();">취소</button>
			<button type="button" onclick="location='../guestbook/guestbook_list?page=${page}';">목록</button>
			
		</form>
				
		<script src="https://code.jquery.com/jquery-latest.min,js"></script>
		<%-- jQuery CDN방식으로 가장 최신 버전을 가져온다. --%>
		<script src="../js/guestbook.js"></script>
	</body>
</html>