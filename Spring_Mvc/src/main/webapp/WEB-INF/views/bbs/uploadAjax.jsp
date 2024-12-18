<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>비동기식 이진파일 업로드 폼</title>
		<script src="https://code.jquery.com/jquery-latest.min.js"></script>
		<script>
			$(document).ready(function() {
				$('#uploadBtn').on('click', function(e){
					let formData = new FormData();
					/*
					 * 첨부파일을 업로드 하는 또 다른 방식은 비동기식 아작스를 이용한 파일 데이터만을 전송.
					 * Ajax를 이용하는 첨부파일 처리는 FormData라는 객체를 이용하는데 IE의 경우 10이상 버전에서만 지원된다.
					*/
					
					var inputFile = $("input[name='uploadFile']");
					// file 객체를 구함
					
					var files = inputFile[0].files;
					// 첫번째 파일객체에서 첨부한 파일을 배열로 구한다.
					console.log(files);
					
					// 첨부파일을 formData에 추가
					for(let i = 0; i < files.length; i++) {
						formData.append("uploadFile", files[i])
					}
					
					$.ajax({
						 url: '/bbs/uploadAjaxOK',//서버 매핑주소
						 processData : false, //processData 데이터를 컨텐트 타입에 맞게 변환여부
						 contentType: false, //요청 컨텐트 타입
						 data : formData, //formData 자체를 전송
						 type : 'POST', //보내는 방식은 post
						 success: function(result){
							 //받아오는 것이 성공시 호출되는 콜백함수
						 }// success click
					});// ajax())
					
				});//#uplodBtn Click()
			});// document ()
		</script>
	</head>
	<body>
		<h2>UpLoad with Ajax</h2>
		<input type="file" name="uploadFile" multiple />
		<button type="button" id="uploadBtn">업로드</button>
	</body>
</html>