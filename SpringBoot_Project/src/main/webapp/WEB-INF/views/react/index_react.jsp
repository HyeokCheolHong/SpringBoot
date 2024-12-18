<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
	function check() {
		if ($.trim($('#name').val()) == '') {
			alert('글쓴이를 입력하세요!');
			$('#name').val('').focus();
			return false;
		}

		if ($.trim($('#title').val()) == '') {
			alert('글제목을 입력하세요!');
			$('#title').val('').focus();
			return false;
		}

		if ($.trim($('#pwd').val()) == '') {
			alert('비번을 입력하세요!');
			$('#pwd').val('').focus();
			return false;
		}

		if ($.trim($('#content').val()) == '') {
			alert('글내용을 입력하세요!');
			$('#content').val('').focus();
			return false;
		}

		$name = $("#name").val();
		$title = $("#title").val();
		$pwd = $("#pwd").val();
		$content = $("#content").val();

		$.ajax({
			type : 'post',
			url : '/board_FormData_ok2',
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			dataType : 'text',
			data : JSON.stringify({
				name : $name,
				title : $title,
				pwd : $pwd,
				content : $content
			}),
			success : function(result) {
				if (result == 'SUCCESS') {
					alert("등록 되었습니다.");					
				}
			}
		});
	}
</script>
<style>
#index_wrap {
	position: relative;
	width: 800px;
	height: 1000px;
	margin-left: auto;
	margin-right: auto;
	margin-top: 100px;
	border: 0px solid red;
	border-radius: 15px;
	box-shadow: 10px 10px 10px gray;
	display: flex; /* 내부 속성을 가로로 배치*/
	justify-content: center; /* 수평 가운데 정렬 */
	align-items: center; /* 수직 가운데 정렬 */
}

#index_wrap iframe {
	border: none;
	width: 80%; /* 원하는 크기로 조정 */
	height: 80%; /* 원하는 크기로 조정 */
}

#index_wrap2 {
	position: relative;
	width: 500px;
	height: 500px;
	margin-left: auto;
	margin-right: auto;
	margin-top: 30px;
	border: 1px solid red;
	border-radius: 10px;
	box-shadow: 10px 10px 10px gray;
	display: flex; /* 내부 속성을 가로로 배치*/
	justify-content: center; /* 수평 가운데 정렬 */
	align-items: center; /* 수직 가운데 정렬 */
}
#index_wrap div {
	width: 80%; /* 원하는 크기로 조정 */
	height: 80%; /* 원하는 크기로 조정 */
}
</style>
</head>
<body>
	<div id="index_wrap">
		<iframe src="http://localhost:3000" width="600px" height="700px"></iframe>
	</div>
	<br />
	<br />
	<hr>
	<br />
	<br />
	
  <div id="index_wrap2">
    <div>
	<h2>리액트가 아닌 일반 jQuery비동기식 연습</h2>
	이름:
	<input name='name' id='name' size='14' />
	<br />
	<br /> 제목:
	<input name='title' id='title' size='36' />
	<br />
	<br /> 비밀번호:
	<input type='password' name='pwd' id='pwd' size='14' />
	<br />
	<br /> 내용:
	<textarea name='content' id='content' rows='10' cols='36'></textarea>
	<br />
	<br />
	<button type='submit' onClick="check();">저장</button>
	<button type='reset' onclick="$('#name').focus();">취소</button>
	</div>
    </div>
</body>
</html>