<%@ page import="model.Post" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	List<Post> posts = (List<Post>)request.getAttribute("posts");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="post" items="${posts}">
		id: <c:out value="${post.id}"/><br/>
		title: <c:out value="${post.title}"/><br/>
		contents: <c:out value="${post.contents}"/><br/>
		created at: <c:out value="${post.createdAt}"/><br/>
		updated at: <c:out value="${post.updatedAt}"/><br/>
		deleted at: <c:out value="${post.deletedAt}"/><br/>
		is deleted: <c:out value="${post.isDeleted}"/><br/>
	</c:forEach>
	
	<button type="button" onclick="location.href='${pageContext.request.contextPath}/post'">게시물 작성</button>
</body>
</html>