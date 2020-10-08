<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Date" %>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ResultSet posts = (ResultSet)request.getAttribute("posts");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<style>
.changeColor {
	background-color: #bff0ff;
}
</style>
<body>
	<table id=boardList border="1">
	<th>제목</th>
	<th>작성자</th>
	<th>작성일</th>
	
	<%while(posts.next()){ %>
		<tr><!-- 첫번째 줄 시작 -->
		    <td><%=posts.getString("title")%></td>
		    <td><%=posts.getString("writer")%></td>
		    <td><%=posts.getDate("created_at")%></td>
		</tr><!-- 첫번째 줄 끝 -->
	<%} %>
	</table>
	
	<button type="button" onclick="location.href='${pageContext.request.contextPath}/post'">게시물 작성</button>
	
</body>
<script type="text/javascript">
	$(document).ready(function(){
		changeColor();
		clickTd();
		clickTr();
	})
		
	function changeColor(){
		$('#boardList tr').mouseover(function(){
				$(this).addClass('changeColor');
			}).mouseout(function() {
				$(this).removeClass('changeColor');
			});
	}
</script>
</html>