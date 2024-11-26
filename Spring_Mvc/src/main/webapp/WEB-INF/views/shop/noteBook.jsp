<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		상품이름 : <strong>${p.name}</strong><hr/>
		<%-- ${p.name}을 자바코드로 표현하면 p.getName()과 같다. --%>
		
		상품가격 : <strong>${p.price}</strong>
		
	</body>
</html>