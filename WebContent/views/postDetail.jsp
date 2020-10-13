<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Date" %>


<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%
	ResultSet post = (ResultSet)request.getAttribute("postDetail");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<style>
td#name{
	width: 60px;
}
td#contents{
	width: 80px;
}
</style>
<body>
<h1>post detail page</h1>
<%post.next(); %>
<table border="1">
	<tr>
		<td id="name">제목</td>
		<td><%=post.getString("title")%></td>
	</tr>
	<tr>
 		<td id="name">작성자</td>
		<td><%=post.getString("writer")%></td>
	</tr>
	<tr>
		<td id="name">작성일</td>
		<td><%=post.getTimestamp("created_at")%></td>
	</tr>
	<tr>
		<td id="name">내용</td>
		<td id="contents"><%=post.getString("contents")%></td>
	</tr>
</table>

<button type="button" onclick="location.href='${pageContext.request.contextPath}/update?postid=' + <%=post.getInt("post_id")%>">수정</button>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/delete?postid=' + <%=post.getInt("post_id")%>">삭제</button>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/'">목록</button>

</body>
</html>