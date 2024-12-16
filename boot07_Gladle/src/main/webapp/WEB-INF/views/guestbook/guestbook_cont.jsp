<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>스프링 Gradle방식의 방명록 내용보기</title>
	</head>
	<body>
		<table border="1">
			<tr>
				<th colspan="2">방명록 내용보기</th>
			</tr>
			<tr>
				<th>제목</th>
				<td>${g.guest_title}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${guestcont}</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${g.guest_hit}</td>
			</tr>
			<tr>
				<th colspan="2">
					<button type="button" onclick="location='/guestbook/guestbook_list?page=${page}';">목록</button>
				</th>
			</tr>
		</table>
	</body>
</html>