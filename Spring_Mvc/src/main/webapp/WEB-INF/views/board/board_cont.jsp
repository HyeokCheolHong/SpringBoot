<!-- 2024-11-29 게시판 내용 작성을 위한 코드 -->
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>스프링 MVC 게시판 내용보기</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				let formObj = $("form[role='form']");
				
				// 수정폼으로 매핑이동 ($표시는 전부 jQuery)
				$('.btn-edit').on("click", function() {
					formObj.attr("action", "/board/board_edit");
					// form action 속성 부여하기
					formObj.attr("method", "get");
					// 속성을 지정
					
					formObj.submit();
					// 히든 번호값 제출
				});
				
				
				// 삭제 매핑으로 이동
				$('.btn-del').on('click', function() {
					formObj.attr("action", "/board/board_del");
					// 액션 속성에 매핑주소 설정
					
					formObj.submit();
					// 별도의 메서드 방식을 지정하지 않아 폼태그에서 지정된 post방식으로 전송된다.
				});
			});
		</script>
	</head>
	<body>
		<form method="post" role="form">
			<input type="hidden" name="bno" value="${bc.bno}" />
			<%-- hidden은 사용자 뷰 화면에는 보이지 않지만 bno 파라미터 이름에 value속성 값인 게시판 번호를 담아서 post로 전달한다. --%>
			<%-- hidden은 Page Source 에서는 보여지므로 보안산 중요한 Data는 hidden으로 보내면 안된다. --%>
			
			<input type="hidden" name="page" value="${page}"/>
			<%-- 페이징에서 책갈피 기능 구현 때문에 쪽번호 생성--%>
		</form>
		<table border="1">
			<tr>
				<th colspan="2">스프링 MVC 게시판 내용</th>
			</tr>
			<tr>
				<th>제목</th>
				<td>${bc.title}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${bcont}</td>
				<!-- bc.cont는 줄바꿈이 되어있지 않기때문에 bcont를 가져옴 -->
			</tr>
			<tr>
				<th>조회수</th>
				<td>${bc.viewcnt}</td>
			</tr>
			<tr>
				<th colspan="2">
					<button type="submit" class="btn-edit">수정</button>
					<button type="submit" class="btn-del">삭제</button>
					<button type="button" onclick="location='board_list?page=${page}';">목록</button>
				</th>
			</tr>
		</table>
	</body>
</html>