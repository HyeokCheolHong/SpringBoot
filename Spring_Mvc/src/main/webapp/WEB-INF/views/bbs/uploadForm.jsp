<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<h2>동기식 방법의 첨부기능</h2>
		<form method="post" action="uploadFormOK" enctype="multipart/form-data">
			<%-- 파일 첨부된 파일(이진파일)을 binary mode파일이라고 한다.
			파일 첨부를 해서 서버에 업로드 하는 기능을 만들려면 반드시 method 방식을 post로 지정해야 하고 추가적으로 enctype="multipart/form-data" 속성을 지정해야 한다
			스프링 부트에서 이진파일 업로드 해주는 내장 API가 있다. 그러므로 외부 라이브러리를 셋팅을 하지 않아도 된다.
			action속성을 생략하면 이전 매핑주소가 해당 속성값이 된다. 그러면 동일 매핑주소인 경우 구분은 메서드 방식으로 구분한다 --%>
			
			<input type="file" name="uploadFile" multiple />
			<%-- 최근 브라우저는 multiple 속성을 지원하는데 이를 이용하면 하나의 input type="file"로 다중 이진파일을 동시에 업로드 할 수 있다.
			이 속성은 IE(인터넷익스플로워)10이상에서만 사용가능하다. --%>
			<button type="submit">파일 업로드</button>
		</form>
	</body>
</html>