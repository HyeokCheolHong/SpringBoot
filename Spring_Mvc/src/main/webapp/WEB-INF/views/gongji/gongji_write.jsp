<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항</title>
		<script src="https://code.jquery.com/jquery-latest.min.js"></script>
		<script src="../js/gongji.js" type="text/javascript"></script>
	</head>
	<body>
		<form method="POST" onsubmit="return gongji_check()">
			<table border="1">
				<tr>
					<th colspan="2">공지사항</th>
				</tr>
				<tr>
					<td>글쓴이</td>
					<td>
						<input id="gongji_writer" name="gongji_writer" size="14" placeholder="글쓴이 입력"/> <br/>
						<span id=error_gongji_writer"></span>
					</td>
				</tr>
				<tr>
					<th>글제목</th>
					<td>
						<input id="gongji_title" name="gongji_title" size="36" placeholder="글제목 입력" /> <br/>
						<span id="error_gongji_title"></span>
					</td>
				</tr>
				<tr>
					<th>글내용</th>
					<td>
						<textarea id="gongji_content" name="gongji_content" rows="10" cols="36" placeholder="글내용 입력"></textarea> <br/>
						<span id="error_gongji_content"></span>
					</td>
				</tr>
				<tr>
				<th colspan="2">
					<input type="submit" value="글쓰기" />
					<input type="reset" value="취소" onclick="gongji_reset()" />
					<button type="button" onclick="location='/gongji/';">목록</button>
				</th>
			</tr>
			</table>
		</form>
	</body>
</html>