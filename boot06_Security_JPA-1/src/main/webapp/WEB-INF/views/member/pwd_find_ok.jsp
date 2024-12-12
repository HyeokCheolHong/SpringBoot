<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>비번찾기 결과</title>
		<link rel="stylesheet" type="text/css" href="./css/member.css" />
	</head>
	<body>
		<div id="pOK_wrap">
			<h2 class="pOK_title">비번검색 결과</h2>
			<table id="pOK_t">
				<tr>
					<th>임시비번</th>
					<td>${ran_pwd}</td>
				</tr>
				<tr>
					<th colspan="2">(*임시비번은 로그인 후 수정하세요!)</th>
				</tr>
			</table>
			<div id="pOK_menu">
				<button type="button" onclick="self.close();">닫기</button>
				<%-- JS에서 self.close();는 자기 자신 윈도우창을 닫는다. --%>
			</div>
		</div>
		
		<script src="https://code.jquery.com/jquery-latest.min,js"></script>
	</body>
</html>