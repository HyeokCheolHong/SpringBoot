<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 2024-12-17 Spring Project 실습 -->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>사용자 자료실 목록</title>
		<link rel="stylesheet" href="../css/board.css" />
	</head>
	<body>
		<form method="get" action="bbs_list">
			<%-- 검색기능 때문에 form태그 추가 --%>
			<div id="bList_wrap">
				<h2 class="bList_title">사용자 자료실 목록</h2>
				<div class="bList_count">글 개수: <b>${totalCount}</b>개</div>
				<table id="bList_t">
					<tr>
						<th width="6%" height="26">번호</th>
						<th width="50%">글제목</th>
						<th width="14%">글쓴이</th>
						<th width="17">작성일</th>
						<th width="14%">조회수</th>
					</tr>
					<tr>
						<c:if test="${!empty blist}">
							<c:forEach var="b" items="${blist}">
								<tr>
									<td align="center">
										<c:if test="${b.bbs_step == 0}">
											<%-- 원본글 일때만 실행 --%>
											
											${b.bbs_ref} 
											<%-- 원본글과 답변글을 묶어주는 글 그룹번호를 출력 --%>
										</c:if>
									</td>
									<td>
										<c:if test="${b.bbs_step != 0}">
											<%-- 담변글일때만 실행 --%>
											
											<c:forEach begin="1" end="${b.bbs_step}" step="1">
												&nbsp; <%-- 답변글 들여쓰기 --%>
											</c:forEach>
											<img src="../images/AnswerLine.gif" />
											<%-- 담변글 이미지 --%>
										</c:if>
										<a href="/bbs/bbs_cont?bbs_no=${b.bbs_no}&state=cont&page=${page}">${b.bbs_title}</a>
										<%-- bbs_cont?bbs_no=번호&state=cont&page=쪽번호 &기호로 구분하면서 3개의 파라미터값이 get방식으로 전달된다. --%>
									</td>
									<td align="center">${b.bbs_name}</td>
									<td align="center">${fn:substring(b.bbs_date,0,10)}</td><%-- 0이상 10미만 사이의 년월일만 반환 --%>
									<td align="center">${b.bbs_hit}</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty blist }">
							<tr>
							<th colspan="5">자료실 목록이 없습니다.</th>
						</c:if>	
					</tr>
				</table>
				
				<%-- 페이징(쪽나누기) --%>
				<div id="bList_paging">
					<%-- 검색전 페이징 --%>
					<c:if test="${(empty find_field) && (empty find_name)}">
						<c:if test="${page <= 1}">
							[이전]&nbsp;
						</c:if>
						<c:if test="${page > 1 }">
							<a href="bbs_list?page=${page-1}">[이전]</a>&nbsp;
						</c:if>
						
						<%-- 쪽번호 출력 --%>
						<c:forEach var="a" begin="${startpage}" end="${endpage}" step="1">
							<c:if test="${a==page}"><${a}></c:if> <%-- 현재 페이지가 선택된 경우 --%>
							
							<c:if test="${a != page}"> <%-- 현재 페이지가 선택 안된 경우 --%>
								<a href="bbs_list?page=${a}">[${a}]</a>&nbsp; 
							</c:if>
						</c:forEach>
						
						<c:if test="${page >= maxpage}">[다음]</c:if>
						<c:if test="${page < maxpage}">
							<a href="bbs_list?page=${page+1}">다음</a>
						</c:if>
					</c:if>
					<%-- 검색전 페이징 --%>
					
					<%-- 검색후 페이징 --%>
					<c:if test="${(!empty find_field) || (!empty find_name)}">
						<c:if test="${page <= 1}">
							[이전]&nbsp;
						</c:if>
						<c:if test="${page > 1 }">
							<a href="bbs_list?page=${page-1}&find_field=${find_field}&find_name=${find_name}">[이전]</a>&nbsp;
						</c:if>
						
						<%-- 쪽번호 출력 --%>
						<c:forEach var="a" begin="${startpage}" end="${endpage}" step="1">
							<c:if test="${a==page}"><${a}></c:if> <%-- 현재 페이지가 선택된 경우 --%>
							
							<c:if test="${a != page}"> <%-- 현재 페이지가 선택 안된 경우 --%>
								<a href="bbs_list?page=${a}&find_field=${find_field}&find_name=${find_name}">[${a}]</a>&nbsp; 
							</c:if>
						</c:forEach>
						
						<c:if test="${page >= maxpage}">[다음]</c:if>
						<c:if test="${page < maxpage}">
							<a href="bbs_list?page=${page+1}&find_field=${find_field}&find_name=${find_name}">다음</a>
						</c:if>
					</c:if>
					<%-- 검색후 페이징 --%>
							
				</div>
	
				<%-- 검색 후 --%>
				<div id="bList_menu">
					<button type="button" onclick="location='/bbs/bbs_write?page=${page}';">글쓰기</button>
					<c:if test="${(!empty find_filed) && (!empty find_name)}">
						<button type="button" onclick="location='/bbs_list?page=${page}';">전체목록</button>
					</c:if>
				</div>				
				
				<%-- 검색 폼 --%>
				<div id="bFind_wrap">
					<select name="find_field">
						<option value="bbs_title"
							<c:if test="${find_field == 'bbs_title'}">
							${'selected'}
							</c:if>>제목</option>
						<option value="bbs_cont">
							<c:if test="$find_field == 'bbs_cont'}">
							$('selected')</c:if>>내용</option>
					</select>
					<input type="search" name="find_name" size="14" value="${find_name}" />
					<button type="submit">검색</button>
				</div>
				
			</div>
		</form>
	</body>
</html>

















