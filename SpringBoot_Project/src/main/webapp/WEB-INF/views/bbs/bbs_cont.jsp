<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>자료실 내용보기</title>
		<link rel="stylesheet" href="../css/bbs.css" />
	</head>
	<body>
		<div id="bsC_wrap" >
			<h2 class="bsC_title">자료실 내용보기</h2>
			<table id="bsC_t">
				<tr>
					<th>제목</th>
					<td>${b.bbs_title}</td>
				</tr>			
				<tr>
					<th>내용</th>
					<td>${bbs_cont}</td>
				</tr>			
				<tr>
					<th>조회수</th>
					<td>${b.bbs_hit}</td>
				</tr>			
				<c:if test="${!empty b.bbs_file}"> <%-- 첨부파일이 있는 경우 --%>
					<tr>
						<th>첨부이미지</th>
						<td><img src="../upload${b.bbs_file}" width="130" height="100" alt="첨부이미지" /></td>
					</tr>
				</c:if>
			</table>
			
			<div id="bsC_menu">
				<button type="button" onclick="location='bbs_cont?bbs_no=${b.bbs_no}&state=reply&page=${page}';">답변</button>
				<input type="button" value="수정" onclick="location='bbs_cont?bbs_no=${b.bbs_no}&state=edit&page=${page}';" />
				<button type="button" onclick="location='bbs_cont?bbs_no=${b.bbs_no}&state=del&page=${page}';">삭제</button> 
				<button type="button" onclick="location='bbs_list?page=${page}';">목록</button>
			</div>
		</div>
	</body>
</html>