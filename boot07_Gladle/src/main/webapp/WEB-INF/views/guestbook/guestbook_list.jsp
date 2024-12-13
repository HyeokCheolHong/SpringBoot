<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
</head>
<body>
	<table border="1">
		<tr>
			<td colspan="5" align="right">총 게시물 수: ${totalCount} 개</td>
		</tr>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>등록날짜</th>
		</tr>
		<c:if test="${!empty glist}">
			<c:forEach var="g" items="${glist}">
				<tr>
					<th>${g.guest_no}</th>
					<th><a href="/guestbook/guestbook_cont?gno=${g.guest_no}&page=${page}">
							${g.guest_title}</a>				
						<%--매핑주소?bno=번호값&page=쪽번호 &기호로
구분해서 2개의 피라미터이름게 각 번호와 쪽번호를 담아서
전달한다.--%></th>
					<th>${g.guest_name}</th>
					<th>${g.guest_hit}</th>
					<th>${g.guest_date}</th>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty glist}">
			<tr>
				<th colspan="5">목록이 없습니다!</th>
		</c:if>
		<%--페이징 쪽번호 출력부분 --%>
		<tr>
			<th colspan="5">
				<%-- begin --%> <c:if test="${page <= 1}">
   [이전]&nbsp;
  </c:if> <c:if test="${page > 1}">
					<a href="/guestbook/guestbook_list?page=${page-1}">[이전]</a>&nbsp;
  </c:if> <%--쪽번호 출력 --%> <c:forEach var="a" begin="${startpage}"
					end="${endpage}" step="1">
					<c:if test="${a == page}">
						<%--현재 쪽번호가 선택된 경우 --%>
     <${a}>
    </c:if>
					<c:if test="${a != page}">
						<%--현재 쪽번호가 선택 안된 경우 --%>
						<a href="/guestbook/guestbook_list?page=${a}">[${a}]</a>&nbsp;
    </c:if>
				</c:forEach> <c:if test="${page >= maxpage}">
   [다음]
  </c:if> <c:if test="${page < maxpage}">
					<a href="/guestbook/guestbook_list?page=${page+1}">[다음]</a>
				</c:if> <%-- end --%>
			</th>
		</tr>
		<tr>
			<td colspan="5" align="right"><input type="button" value="글쓰기"
				onclick="location='/guestbook/guestbook_write?page=${page}';" /> <%--location객체로 이동하는 get방식 --%>
			</td>
		</tr>
	</table>	
</body>
</html>