<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%if(session.getAttribute("loginCheck")==null || !(Boolean)session.getAttribute("loginCheck") ){ %>
<div>
	<a href="${pageContext.request.contextPath}/login">로그인</a>
</div>
<%} else if((Boolean)session.getAttribute("loginCheck")){%>
<div>
	<%=(String)session.getAttribute("userId") %>
	<a href="${pageContext.request.contextPath}/logout"> 로그아웃</a>
</div>
<%} %>
</body>
</html>