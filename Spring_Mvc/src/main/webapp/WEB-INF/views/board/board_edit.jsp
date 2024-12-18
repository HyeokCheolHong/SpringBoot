<!-- 2024-11-29 게시판 글수정 작성을 위한 board_wirte 내용 복사후 수정 -->
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>스프링 MVC 게시판 글수정</title>
	<%-- jQuery 라이브러리를 CDN 방식으로 가져오기 --%>
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>
	<script src="../js/b.js" type="text/javascript"></script>
</head>
<body>
	<%-- 
	action 속성의 매핑주소를 생략하면 이전 주소인 board_write가 action의 속성값이 된다. 즉, 이전 매핑주소가 action 매핑주소가 된다.
	동일 매핑주소 구분은 메소드 방식으로 구분(GET, POST)한다.
	--%>
	<form method="POST" onsubmit="return check()">
	
		<!-- board_wirte 내용 복사후 Edit를 위한 수정내용 -->
		<input type="hidden" name="bno" value="${eb.bno}">
		<input type="hidden" name="page" value="${page}">
		
		<table border="1">
			<tr>
				<th colspan="2">스프링 MVC 게시판 입력폼</th>
			</tr>
			<tr>
				<th>글쓴이</th>
				<td>
					<!-- board_wirte 내용 복사후 Edit를 위한 수정내용 -->
					<input id="writer" name="writer" size="14" placeholder="글쓴이 입력" value="${eb.writer}"/> <br/>
					
					<!-- board_edit맞게 수정후 board_write 내용은 주석처리 -->
					<!-- <input id="writer" name="writer" size="14" placeholder="글쓴이 입력"/> <br/> -->
					
					<span id="error_writer"></span>
				</td>
			</tr>
			<tr>
				<th>글제목</th>
				<td>
					<!-- board_wirte 내용 복사후 Edit를 위한 수정내용 -->
					<input id="title" name="title" size="36" placeholder="글제목 입력" value="${eb.title}"/> <br/>
					
					<!-- board_edit맞게 수정후 board_write 내용은 주석처리 -->
					<!-- <input id="title" name="title" size="36" placeholder="글제목 입력" /> <br/> -->
					
					<span id="error_title"></span>
				</td>
			</tr>
			<tr>
				<th>글내용</th>
				<td>
					<!-- board_wirte 내용 복사후 Edit를 위한 수정내용 -->
					<textarea id="content" name="content" rows="10" cols="36" placeholder="글내용 입력" >${eb.content}</textarea> <br/>
					
					<!-- board_edit맞게 수정후 board_write 내용은 주석처리 -->
					<!-- <textarea id="content" name="content" rows="10" cols="36" placeholder="글내용 입력"></textarea> <br/> -->
					<span id="error_content"></span>
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="수정하기" />
					<input type="reset" value="취소" onclick="reset()" />
					<!-- 2024-11-29 게시판리스트 책갈피 기능 추가를 위한 코드 -->
					<button type="button" onclick="location='/board/board_list?page=${page}';">목록</button>
				</th>
			</tr>
		</table>
	</form>
</body>
</html>