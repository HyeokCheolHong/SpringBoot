<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 2024-12-04 문제) 공지사항 만들기 -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>스프링 게시판 목록</title>
		<style>
			strong.listReply_count{
				color:red;
				background:yellow;
				font-size:12px;
				border-radius:7px;
				padding:1px;
				box-shadow:2px 2px 2px orange;
			}
		</style>
		
	</head>
	<body>
		<table border="1">
			<tr>
				<th colspan="5'">게시판 목록</th>
			</tr>
			<tr>
				<th colspan="5" align="right">총 게시물 수 : <strong>${totalCount}</strong> 개</th>
			</tr>
			<tr>
				<th>번호</th><th>제목</th><th>글쓴이</th><th>조회수</th><th>등록날짜</th>
			</tr>
			
			<c:if test="${!empty glist}">
				<c:forEach var="g" items="${glist}">
					<tr>
						<th>${g.gno}</th>
						<td style="padding-left:10px;">
							<b><a href="../gongji/gongji_teacher_cont?gno=${g.gno}&page=${page}">${g.gtitle}</a></b>
							
						</td>
						<th>${g.gname}</th>
						<th>${g.ghit}</th>
						<th>${fn:substring(g.gdate,0,10)}</th>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty glist}">
				<tr>
					<th colspant="5">공지 목록이 없습니다.</th>
				</tr>
			</c:if>
			
			<%-- 페이징 쪽번호 출력부분 --%>
			<tr>
				<th colspan="5">
					<c:if test="${page <=1}">
						[이전]&nbsp;
					</c:if>
					<c:if test="${page >1}">
						<a href="/gongji/gongji_teacher_list?page=${page-1}">[이전]</a>&nbsp;
					</c:if>
					
					<c:forEach var="a" begin="${startpage}" end="${endpage}" step="1">
						<c:if test="${a == page}">
							<${a}>
						</c:if>
						<c:if test="${a != page}">
							<a href="/gongji/gongji_teacher_list?page=${a}">[${a}]</a>&nbsp;
						</c:if>
					</c:forEach>
					
					<c:if test="${page >= maxpage}">
						[다음]
					</c:if>
					<c:if test="${page < maxpage}">
						<a href="/gongji/gongji_teacher_list?page=${page+1}">[다음]</a>
					</c:if>
				</th>
			</tr>
			
			
			<tr>
				<td colspan="5" align="right">
					<%-- 자바스크립트 location 객체에 의해서 이동하는 방식은 get이다 --%>
					<button type="button" onClick="location='../gongji_teacher_write?page=${page}';">공지사항 작성</button>
				</td>
			</tr>
		</table>
	</body>
</html>