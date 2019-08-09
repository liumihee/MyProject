<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	Model ==> Controller
--%>
<jsp:useBean id="model" class="com.sist.model.Model"/>
<%
	model.databoard_list(request);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<style type="text/css">
.row{
	margin: 0px auto;
	width: 900px;
}
</style>
</head>
<body>
	<div class="container">
		<h2 class="text-center">자료실</h2>
		<div class="row">
			<table class="table">
				<tr>
					<td>
						<a href="insert.jsp" class="btn btn-sm btn-success">새글</a>
					</td>
					<td class="text-right">
						${curpage } page / ${totalpage } pages
					</td>
				</tr>
				<tr class="danger">
					<td width=10% class="text-center">번호</td>
					<td width=45% class="text-center">제목</td>
					<td width=15% class="text-center">이름</td>
					<td width=20% class="text-center">작성일</td>
					<td width=10% class="text-center">조회수</td>
				</tr>
				<c:forEach var="vo" items="${list }" varStatus="s">
					<tr class="${s.index%2==0?'':'warning' }">
						<td width=10% class="text-center">${vo.no }</td>
						<td width=45% class="text-left"><a href="detail.jsp?no=${vo.no }&page=${curpage}">${vo.subject }</a></td>
						<td width=15% class="text-center">${vo.name }</td>
						<td width=20% class="text-center">${vo.regdate }</td>
						<td width=10% class="text-center">${vo.hit }</td>
					</tr>
				</c:forEach>
			</table>
			<table class="table">
				<tr>
					<td class="text-center">
						<ul class="pagination">
							<li><a href="list.jsp?page=1">&lt;&lt;</a></li>
							<li><a href="#">&lt;</a></li>
							<c:forEach var="i" begin="1" end="${totalpage }">
								<li class="${i==curpage?'active':'' }"><a href="list.jsp?page=${i }">${i }</a></li>
							</c:forEach>
							<li><a href="#">&gt;</a></li>
							<li><a href="list.jsp?page=${totalpage }">&gt;&gt;</a></li>
						</ul>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>