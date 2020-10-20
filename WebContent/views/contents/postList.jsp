<%@page import="util.StringUtil"%>
<%@page import="util.Paging"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Date" %>

<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ResultSet posts = (ResultSet)request.getAttribute("posts");
	Paging paging = (Paging)request.getAttribute("paging");
	
	String option = (String)request.getAttribute("option");
	String word = (String)request.getAttribute("word");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>InniBoard Post List</title>
</head>
<body>
<div>
	<form id="search" name="search" action="${pageContext.request.contextPath}/search" method="get">
		<select name="search_option">
			<option value="writer" <%if(!StringUtil.isEmpty(option) && option.equals("writer")) {%> 
					selected="selected"<%} %>>작성자</option>
			<option value="title" <%if(!StringUtil.isEmpty(option) && option.equals("title")) {%> 
					selected="selected"<%} %>>제목</option>
			<option value="updated_at" <%if(!StringUtil.isEmpty(option) && option.equals("updated_at")) {%> 
					selected="selected"<%} %>>작성일</option>
		</select>
			<input type="text" name="search_word" <%if(!StringUtil.isEmpty(word)){ %> placeholder="<%=word%>" <%} %>>	
		<input type="submit" value="검색">
	</form>
</div>
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
			    <td><%=posts.getDate("updated_at")%></td>
			</tr>
		<%} %>
		</table>
	</div>
	<div>
		<button type="button" onclick="location.href='${pageContext.request.contextPath}/create'">게시물 작성</button> <br/>
	</div>
	
	
	<button type="button" onclick="goBeginPage()">처음</button>
	<%if(paging.getStartPageNo()!=1 ){ %>
		<button type="button" onclick="goPreviousBlock()">이전</button>
	<%} %>
	<c:forEach var="page" begin="${paging.startPageNo}" end="${paging.endPageNo}">
		<%if(StringUtil.isEmpty(option) && StringUtil.isEmpty(word)){ //전체데이터%>
			<a href="${pageContext.request.contextPath}/?page=${page}">${page}</a>
		<%} else{ //검색데이터%>
			<a href="${pageContext.request.contextPath}/search?page=${page}&search_option=<%=option %>&search_word=<%=word%>">${page}</a>
		<%} %>
	</c:forEach>
	<%if(paging.getEndPageNo()!=paging.getTotalPages()){ %>
		<button type="button" onclick="goNextBlock()">이후</button>
	<%} %>		
	<button type="button" onclick="goFinalPage()">끝</button>
</div>
</body>
<script type="text/javascript">
	function showDetail(postId){
			<%if(StringUtil.isEmpty(word)){%>
				location.href="${pageContext.request.contextPath}/postDetail?postid=" + postId;
			<%}else{%>
				location.href="${pageContext.request.contextPath}/postDetail?postid=" + postId 
								+ "&page=${paging.pageNo}&search_option=<%=option%>&search_word=<%=word%>";
			<%}%>
		}
	function goBeginPage(){
			<%if(StringUtil.isEmpty(word)){%>
				location.href="${pageContext.request.contextPath}/?page=1";
			<%}else{%>
				location.href="${pageContext.request.contextPath}/search?page=1&search_option=<%=option%>&search_word=<%=word%>";
			<%}%>
		}
	function goPreviousBlock(){
			<%if(StringUtil.isEmpty(word)){%>
				location.href="${pageContext.request.contextPath}/?page=" + (${paging.startPageNo}-1);
			<%}else{%>
				location.href="${pageContext.request.contextPath}/search?page=" + (${paging.startPageNo}-1) + "&search_option=<%=option%>&search_word=<%=word%>";
			<%}%>
		}
	function goNextBlock(){
			<%if(StringUtil.isEmpty(word)){%>
				location.href="${pageContext.request.contextPath}/?page=" + (${paging.endPageNo}+1)
			<%}else{%>
				location.href="${pageContext.request.contextPath}/search?page=" + (${paging.endPageNo}+1) + "&search_option=<%=option%>&search_word=<%=word%>";
			<%}%>
		}
	function goFinalPage(){
			<%if(StringUtil.isEmpty(word)){%>
				location.href="${pageContext.request.contextPath}/?page=" + ${paging.totalPages};
			<%}else{%>
				location.href="${pageContext.request.contextPath}/search?page=" + ${paging.totalPages} + "&search_option=<%=option%>&search_word=<%=word%>";
			<%}%>
		}

	function checkSearchOption(){
			document
		}
	
</script>
</html>