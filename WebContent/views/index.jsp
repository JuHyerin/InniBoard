<%@page import="util.Paging"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Date" %>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ResultSet posts = (ResultSet)request.getAttribute("posts");
	Paging paging = (Paging)request.getAttribute("paging");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>

<body>
<div>
	<div>	
		<table border="1">
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		
		<%while(posts.next()){ %>
			<tr onclick=showDetail(<%=posts.getInt("post_id")%>) style="cursor:pointer">
			    <td><%=posts.getString("title")%></td>
			    <td><%=posts.getString("writer")%></td>
			    <td><%=posts.getDate("created_at")%></td>
			</tr>
		<%} %>
		</table>
	</div>
	<button type="button" onclick="location.href='${pageContext.request.contextPath}/create'">게시물 작성</button> <br/>
	
	
	<button type="button" onclick="goBeginPage()">처음</button>
	<button type="button" onclick="goPreviousBlock()">이전</button>
	<c:forEach var="page" begin="${paging.startPageNo}" end="${paging.endPageNo}">
		<a href="${pageContext.request.contextPath}/?page=${page}">${page}</a>
	</c:forEach>
	<button type="button" onclick="goNextBlock()">이후</button>	
	<button type="button" onclick="goFinalPage()">끝</button>
</div>
</body>

<script type="text/javascript">
	function showDetail(postId){
			location.href="${pageContext.request.contextPath}/postDetail?postid=" + postId;
		}
	function goBeginPage(){
		location.href="${pageContext.request.contextPath}/?page=1";
	}
	function goPreviousBlock(){
		location.href="${pageContext.request.contextPath}/?page=" + (${paging.startPageNo}-1);
	}
	function goNextBlock(){
		location.href="${pageContext.request.contextPath}/?page=" + (${paging.endPageNo}+1)
	}
	function goFinalPage(){
		location.href="${pageContext.request.contextPath}/?page=" + ${paging.totalPages};
	}
	
</script>
</html>