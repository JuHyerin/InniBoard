<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/login" method="post">
		<label>���̵�</label>
		<input type="text" name="id"> <br>
		
		<label>��й�ȣ</label>
		<input type="password" name="pwd"> <br>
		
		<input type="submit" value="�α���">
	</form>
</body>
</html>