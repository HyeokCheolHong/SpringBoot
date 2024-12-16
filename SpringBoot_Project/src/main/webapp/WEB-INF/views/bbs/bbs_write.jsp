<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>사용자 자료실 입력폼</title>
		<link rel="stylesheet" href="../css/bbs.css" />
	</head>
	<body>
		<div id="bsW_wrap">
			<h2 class="bsW_title">자료실 입력폼</h2>
			<form method="post" action="bbs_write_ok" onsubmit="return write_check();" enctype="multipart/form-data">
				<%-- 
					파일 첨부되는 자료실 기능을 만들기 위해서 필요한 코드
						1. 폼태그내에 enctype="multipart/form-data"속성을 꼭 지정해야 한다.
						2. method="post"방식으로 설정해야 한다. 
				--%>
				<table id="bsW_t">
					<tr>
						<th>글쓴이</th>
						<td>
							<input name="bbs_name" id="bbs_name" size="14" /> <%-- type속성을 생략하면 기본값이 한줄 입력필드인 text이다. --%>
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input name="bbs_title" id="bbs_title" size="36"></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="bbs_pwd" id="bbs_pwd" size="14" /></td>
					</tr>
					<tr>
						<th>글내용</th>
						<td><textarea name="bbs_cont" id="bbs_cont" rows="8" cols="34"></textarea></td>
					</tr>
					<tr>
						<th>파일첨부</th>
						<td><input type="file" name="uploadFile" /></td>
					</tr>
				</table>
				<div id="bsW_menu">
					<button type="submit">입력</button>
					<button type="reset" onclick="$('#bbs_name').focus();">취소</button>
					<button type="button" onclick="location='/bbs/bbs_list?page=${page}';">목록</button>
				</div>"
			
			</form>
		</div>	
		
			
		<script src="https://code.jquery.com/jquery-latest.min,js"></script>
		<%-- jQuery CDN방식으로 가장 최신 버전을 가져온다. --%>
		<script src="../js/bbs.js"></script>
	</body>
</html>