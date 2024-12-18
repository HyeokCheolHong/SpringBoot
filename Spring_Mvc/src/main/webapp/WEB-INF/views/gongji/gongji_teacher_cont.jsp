<%@ page contentType="text/html; charset=UTF-8"%>
<!-- 2024-12-04 문제) 공지사항 만들기 -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>스프링 MVC 공지사항 내용보기</title>
	</head>
	<body>
		<table border="1">
			<tr>
				<th colspan="2">스프링 MVC 공지사항 공지글</th>
			</tr>
			<tr>
				<th>제목</th>
				<td>${gc.gtitle}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${gc.gcont}</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${gc.ghit}</td>
			</tr>
			<tr>
				<th colspan="2">
					<button type="submit" onclick="location='gongji_teacher_edit?gno=${gc.gno}&page=${page}';">수정</button>
					<button type="submit" onclick="if(confirm('정말로 삭제할까요?') == true) {
						location='gongji_teacher_del?gno=${gc.gno}&page=${page}';} else {
							return;
							<%-- 자바스크립트에서 confirm() 내장함수는 확인/취소 버튼을 가지고 메시지를 담은 팝업창
							확인 클릭시 true를 반환하고 / 취소 클릭시 false를 반환한다. --%>
						}">삭제
					</button>
					<button type="button" onclick="location='/gongji/gongji_teacher_list?page=${page}';">목록</button>
				</th>
			</tr>
		</table>
	</body>
</html>