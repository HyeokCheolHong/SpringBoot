<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script src="https://code.jquery.com/jquery-latest.min.js"></script>
		<script>
			function check(){
				 if($.trim($('#gname').val()) == ''){
					 alert('공지 작성자를 입력하세요!');
					 $('#gname').val('').focus();
					 return false;
				 }
				 if($.trim($('#gtitle').val()) == ''){
					 alert('공지 제목을 입력하세요!');
					 $('#gtitle').val('').focus();
					 return false;
				 }
				 if($.trim($('#gcont').val()) == ''){
					 alert('공지 내용을 입력하세요!');
					 $('#gcont').val('').focus();
					 return false;
				 }
			 }
		</script>
	</head>
	<body>
		<h2>공지사항 입력폼</h2>
		<form method="post" onsubmit="return check();">
			<label for="gname">공지 작성자 : </label>
			<input name="gname" id="gname" size="14" /><br/><br/>
			
			<label for="gtitle">공지 제목 : </label>
			<input name="gtitle" id="gtitle" size="36" /><br/><br/>
			
			<label for="gcont">공지 내용 : </label>
			<textarea name="gcont" id="gcont" rows="10" cols="36"></textarea>
			<hr/>
			
			<button type="submit">저장</button>
			<button type="reset" onclick="$('#gname').focus();">취소</button>
			<button type="button" onclick="location='/gongji/gongji_teacher_list?page=${page}';">목록</button>
		</form>
	</body>
</html>