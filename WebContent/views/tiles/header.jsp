<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div>
	<button onclick="location.href='${pageContext.request.contextPath}/'">Ȩ����</button>
	<%if(session.getAttribute("loginCheck")==null || !(Boolean)session.getAttribute("loginCheck") ){ %>
		<a href="${pageContext.request.contextPath}/login">�α���</a>
	<%} else if((Boolean)session.getAttribute("loginCheck")){%>
		<%=(String)session.getAttribute("userId") %>
		<a href="${pageContext.request.contextPath}/logout"> �α׾ƿ�</a>
	<%} %>
</div>
</body>
</html>