
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% String contentPage = (String)request.getAttribute("contentPage");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>

<body>
<%if(!contentPage.equals("/views/contents/postForm.jsp")){//create,update할땐 로그아웃 불가%>
<jsp:include page="/views/tiles/header.jsp"></jsp:include>
<%} %>
<jsp:include page="<%=contentPage %>"></jsp:include>
</body>
</html>