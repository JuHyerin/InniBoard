<%@page import="java.sql.Timestamp"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Date" %>


<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%
	ResultSet post = (ResultSet)request.getAttribute("postDetail");
	post.next();
	String writer = post.getString("writer");
	String userId = (String)session.getAttribute("userId");
	
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
		<%if((Timestamp)post.getTimestamp("updated_at")==null 
				|| post.getTimestamp("updated_at").equals("0000-00-00 00:00:00")){ %>
		<td><%=post.getTimestamp("created_at")%></td>
		
		<%}else { %>
		<td><%=post.getTimestamp("updated_at")%></td>
		<%} %>
	</tr>
	
	<tr>
		<td id="name">내용</td>
		<td id="contents"><%=post.getString("contents")%></td>
	</tr>
</table>

<% if(writer.equals(userId)){ %>
<div id="divUpdDelBtns">
	<button type="button" onclick="location.href='${pageContext.request.contextPath}/update?postid=' + <%=post.getInt("post_id")%>">수정</button>
	<button type="button" onclick="location.href='${pageContext.request.contextPath}/delete?postid=' + <%=post.getInt("post_id")%>">삭제</button>
</div>
<%} %>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/'">목록</button>

</body>
<script type="text/javascript">
<%-- if(<%=(String)session.getAttribute("userId")%>!=<%=post.getString("writer")%>){
	document.getElementById("divUpdDelBtns").style.display = "none"; // Hide
}
 --%>

</script>
</html>