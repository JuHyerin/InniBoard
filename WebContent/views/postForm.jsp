<%@ page import="java.sql.ResultSet" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>

<%	request.setCharacterEncoding("utf-8"); %>
<%	ResultSet post = (ResultSet)request.getAttribute("postData");

	String title="제목작성";
	String contents="내용작성";
	if(post!=null){
		post.next();
		title = post.getString("title");
		contents = post.getString("contents");
	}					
%>

<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head> 
<body>

<form action="${pageContext.request.contextPath}/update" name="postForm" method="post">
	<p>
		<label>제목</label>
		<input type="text" name="title" value="<%=title%>">
		<%if(post!=null){ %>
		<input type="hidden" name="postid" value="<%=post.getInt("post_id")%>">
		<%}else{%>
		<input type="hidden" name="postid" value="0">
		<%} %>
	</p>
	<p>
		<label>내용</label>
		<input type="text" name="contents" value="<%=contents%>">
		
	</p>
	
	<p>
		<input type="submit" value="등록">
	</p>
</form>

</body>

</html>