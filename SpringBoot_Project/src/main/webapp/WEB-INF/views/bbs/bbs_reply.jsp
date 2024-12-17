<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>사용자 자료실 답변폼</title>
		<link rel="stylesheet" href="../css/bbs.css" />
	</head>
	<body>
		<div id="bsW_wrap">
			<h2 class="bsW_title">자료실 답변폼</h2>
			<form method="post" action="bbs_reply_ok" onsubmit="return write_check();">
				<%-- 답변글 히든 값 --%>
				<input type="hidden" name="bbs_ref" value="${b.bbs_ref}" /> 
				<%-- 원본글과 답변글을 묶어주는 글그룹번호 --%>
				
				<input type="hidden" name="bbs_step" value="${b.bbs_step}" /> 
				<%-- 원본글과 답변글을 구분하는 번호값이면서 몇번째 답변글인가를 알려준다 --%>
				
				<input type="hidden" name="bbs_level" value="${b.bbs_level}" />
				<%-- 답변글 정렬순서 --%>
				
				<%-- 페이징(쪽나누기)에서 책갈피 기능 히든값을 제공 --%>
				<input type="hidden" name="page" value="${page}" />				
				
				<table id="bsW_t">
					<tr>
						<th>글쓴이</th>
						<td>
							<input name="bbs_name" id="bbs_name" size="14" /> <%-- type속성을 생략하면 기본값이 한줄 입력필드인 text이다. --%>
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input name="bbs_title" id="bbs_title" size="36" value="Re:${b.bbs_title}" ></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="bbs_pwd" id="bbs_pwd" size="14" /></td>
					</tr>
					<tr>
						<th>글내용</th>
						<td><textarea name="bbs_cont" id="bbs_cont" rows="8" cols="34"></textarea></td>
					</tr>
				</table>
				<div id="bsW_menu">
					<button type="submit">답변</button>
					<button type="reset" onclick="loaction='bbs_cont?bbs_no=${b.bbs_no}&state=cont&page=${page}';">취소</button>
					<button type="button" onclick="location='bbs_list?page=${page}';">목록</button>
				</div>"
			
			</form>
		</div>	
		
			
		<script src="https://code.jquery.com/jquery-latest.min,js"></script>
		<%-- jQuery CDN방식으로 가장 최신 버전을 가져온다. --%>
		<script src="../js/bbs.js"></script>
	</body>
</html>