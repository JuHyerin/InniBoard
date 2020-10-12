<%@ page import="java.sql.ResultSet" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>

<%	request.setCharacterEncoding("utf-8"); %>
<%-- <%	ResultSet post = (ResultSet)request.getAttribute("postData");%> --%>

<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head> 
<body>
	<form action="${pageContext.request.contextPath}/create" method="post">
	<p>
		<label>제목</label>
		<input type="text" name="title" value="제목입력">
		<%-- <input type="text" name="title" value="<%=post.getString("title") %>"> --%>
	</p>
	<p>
		<label>내용</label>
		<input type="text" name="contents" value="제목입력">
		<%-- <input type="text" name="contents" value="<%=post.getString("contents") %>"> --%>
	</p>
	
	<p>
		<input type="submit" value="등록">
	</p>
</form>
</body>
</html>