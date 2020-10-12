<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>

<% request.setCharacterEncoding("utf-8"); %>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/post" method="post">
	<p>
		<label>제목</label>
		<input type="text" name="title" value="제목 입력">
	</p>
	<p>
		<label>내용</label>
		<input type="text" name="contents" value="내용 입력">
	</p>
	
	<p>
		<input type="submit" value="등록">
	</p>
</form>
</body>
</html>