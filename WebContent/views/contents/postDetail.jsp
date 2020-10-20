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
	
	//��� ��ư ������ ���� ������ �����ϸ鼭 ���ư���
	String listPage = (String)request.getAttribute("page");
	String option = (String)request.getAttribute("option");
	String word = (String)request.getAttribute("word");

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
<%if(option!=null || word!=null){ %>
	<button type="button" 
	onclick="location.href='${pageContext.request.contextPath}/search?page=<%=listPage%>&search_option=<%=option%>&search_word=<%=word%>'">
	���</button>
<%} else{%>
	<button type="button" onclick="location.href='${pageContext.request.contextPath}/'">���</button>
<%} %>


<div>
	<%if(session.getAttribute("loginCheck")!=null && (Boolean)session.getAttribute("loginCheck")){ //�α��λ��¸� ��� �� �� ����%>
	<div>
		<label>����ۼ�</label>
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
		<% if(comments.getString("writer").equals(userId)){ %>
			<button onclick="location.href='${pageContext.request.contextPath}/deleteComment?commentid=<%=comments.getInt("comment_id")%>'">
			��ۻ���</button>
		<%} %>
	</div>
	<%} %>
	
	<div>
		<button type="button" onclick="goBeginPage()">ó��</button>
		<%if(paging.getTotalPages()!=1){ %>
			<button type="button" onclick="goPreviousBlock()">����</button>
		<%} %>
		<c:forEach var="page" begin="${paging.startPageNo}" end="${paging.endPageNo}">
			<a href="${pageContext.request.contextPath}/postDetail?postid=<%=post.getInt("post_id")%>&commentpage=${page}">${page}</a>
		</c:forEach>
		<%if(paging.getEndPageNo()!=paging.getTotalPages()){ %>
		<button type="button" onclick="goNextBlock()">����</button>
		<%} %>	
		<button type="button" onclick="goFinalPage()">��</button>
	</div>
	<%} %>
</div>

</body>
<script type="text/javascript">
	function goList(page, option, word){
			/*if(option==null || word==null){
				location.href="${pageContext.request.contextPath}/";
			}else{
				location.href="${pageContext.request.contextPath}/search?page="+page+"&search_option="+option+"&search_word="+word;
			}*/

			
		location.href="${pageContext.request.contextPath}/";
		
		}
	function goBeginPage(){
			location.href="${pageContext.request.contextPath}/postDetail?postid=<%=post.getInt("post_id")%>&commentpage=1";
		}
	function goPreviousBlock(){
			location.href="${pageContext.request.contextPath}/postDetail?postid=<%=post.getInt("post_id")%>&commentpage=" + (${paging.startPageNo}-1);
		}
	function goNextBlock(){
			location.href="${pageContext.request.contextPath}/postDetail?postid=<%=post.getInt("post_id")%>&commentpage=" + (${paging.endPageNo}+1);
		}
	function goFinalPage(){
			location.href="${pageContext.request.contextPath}/postDetail?postid=<%=post.getInt("post_id")%>&commentpage=" + ${paging.totalPages};
		}

	function checkSearchOption(){
			document
		}
	
</script>
</html>