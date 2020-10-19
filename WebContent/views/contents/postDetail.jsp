<%@page import="util.Paging"%>
<%@page import="java.sql.Timestamp"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Date" %>


<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<%
	ResultSet post = (ResultSet)request.getAttribute("postDetail");
	post.next();
	
	String postWriter = post.getString("writer");//���� �Խñۿ��� ����������ư ����ȭ
	String userId = (String)session.getAttribute("userId");
	
	ResultSet comments = (ResultSet)request.getAttribute("comments");
	Paging paging = (Paging)request.getAttribute("paging");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Post Detail Page</title>
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
		<td id="name">����</td>
		<td><%=post.getString("title")%></td>
	</tr>
	<tr>
 		<td id="name">�ۼ���</td>
		<td><%=post.getString("writer")%></td>
	</tr>
	<tr>
		<td id="name">�ۼ���</td>
		<td><%=post.getTimestamp("updated_at")%></td>
	</tr>
	
	<tr>
		<td id="name">����</td>
		<td id="contents"><%=post.getString("contents")%></td>
	</tr>
</table>

<% if(postWriter.equals(userId)){ %>
<div id="divUpdDelBtns">
	<button type="button" onclick="location.href='${pageContext.request.contextPath}/update?postid=' + <%=post.getInt("post_id")%>">����</button>
	<button type="button" onclick="location.href='${pageContext.request.contextPath}/delete?postid=' + <%=post.getInt("post_id")%>">����</button>
</div>
<%} %>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/'">���</button>


<div>
	<%if(session.getAttribute("loginCheck")!=null && (Boolean)session.getAttribute("loginCheck")){ //�α��λ��¸� ��� �� �� ����%>
	<div>
		<label>��۴ޱ�</label>
		<form action="${pageContext.request.contextPath}/createComment" method="post">
			<input type="text" name="comment" placeholder="����� �Է��ϼ���.">
			<input type="hidden" name="postId" value="<%=post.getInt("post_id")%>">
			<input type="submit" value="���">	
		</form>
	</div>
	<%} %>
	<%if(comments!=null && paging!=null){
		while(comments.next()){%>
	<div>
		<label>�ۼ���:<%=comments.getString("writer") %>/</label>
		<label>�ۼ���:<%=comments.getTimestamp("updated_at") %></label><br/>
		<label><%=comments.getString("comment") %></label>
	</div>
	<%} %>
	
	<div>
		<button type="button" onclick="goBeginPage()">ó��</button>
		<button type="button" onclick="goPreviousBlock()">����</button>
		<c:forEach var="page" begin="${paging.startPageNo}" end="${paging.endPageNo}">
			<a href="${pageContext.request.contextPath}/?page=${page}">${page}</a>
		</c:forEach>
		<button type="button" onclick="goNextBlock()">����</button>	
		<button type="button" onclick="goFinalPage()">��</button>
	</div>
	<%} %>
</div>

</body>
<script type="text/javascript">
	function goBeginPage(){
			location.href="${pageContext.request.contextPath}/postDetail?postid=<%=post.getInt("post_id")%>&page=1";
		}
	function goPreviousBlock(){
			location.href="${pageContext.request.contextPath}/postDetail?postid=<%=post.getInt("post_id")%>&page=" + (${paging.startPageNo}-1);
		}
	function goNextBlock(){
			location.href="${pageContext.request.contextPath}/postDetail?postid=<%=post.getInt("post_id")%>&page=" + (${paging.endPageNo}+1);
		}
	function goFinalPage(){
			location.href="${pageContext.request.contextPath}/postDetail?postid=<%=post.getInt("post_id")%>&page=" + ${paging.totalPages};
		}

	function checkSearchOption(){
			document
		}
	
</script>
</html>