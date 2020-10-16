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
		<label>아이디</label>
		<input type="text" name="id"> <br>
		
		<label>비밀번호</label>
		<input type="password" name="pwd"> <br>
		
		<input type="submit" value="로그인">
	</form>
</body>
</html>