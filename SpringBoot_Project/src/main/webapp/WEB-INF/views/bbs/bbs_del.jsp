<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>자료실 삭제</title>
		<link rel="stylesheet" href="../css/bbs.css" />
		
	</head>
	<body>
		<div id="bsDel_wrap">
			<h2 class="bsDel_title">자료실 삭제</h2>
			<form method="post" action="bbs_del_ok?bbs_no=${b.bbs_no}" onsubmit="return del_check();">
				<%-- ?bbs_no=번호만 get방식으로 전송되고 나머지는 post방식으로 전송된다. --%>
				<input type="hidden" name="page" value="${page}" />
				<table id="bsDel_t">
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="del_pwd" id="del_pwd" size="14" /></td>
					</tr>
				</table>
				<div id="bsDel_menu">
					<button type="submit">삭제</button>
					<button type="reset" onclick="location='/bbs/bbs_list?page=${page}';">취소</button>
				</div>
			</form>
		</div>
		
		
		<script src="https://code.jquery.com/jquery-latest.min,js"></script>
		<script>
			function del_check() {
				if($.trim($('#del_pwd').val()) == '') {
					alert('비번을 입력하세요!');
					$('#del_pwd').val('').focus();
					return false;
				}
			}
		</script>
	</body>
</html>