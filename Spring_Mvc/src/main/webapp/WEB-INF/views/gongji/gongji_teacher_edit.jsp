<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>스프링 MVC 게시판 글수정</title>
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>
	<script>
	 function gongji_check(){
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
	<form method="post" action="gongji_teacher_edit_ok" onsubmit="return gongji_check()">
	
		<input type="hidden" name="gno" value="${gc.gno}" />
		<input type="hidden" name="page" value="${page}" />
		
		<label for="gname">공지 작성자 : </label>
		<input name="gname" id="gname" size="14" value="${gc.gname}" /><br/><br/>
		
		<label for="gtitle">공지 제목 : </label>
		<input name="gtitle" id="gtitle" size="36" value="${gc.gtitle}" /><br/><br/>
		
		<label for="gcont">공지 내용 : </label>
		<textarea name="gcont" id="gcont" rows="10" cols="36">${gc.gcont}</textarea>
		<hr/>
		
		<button type="submit" >수정</button>
		<button type="reset" onclick="location='gongji_teacher_cont?gno=${gc.gno}&page=${page}';">삭제	</button>
		<button type="button" onclick="location='/gongji/gongji_teacher_list?page=${page}';">목록</button>

	</form>
</body>
</html>