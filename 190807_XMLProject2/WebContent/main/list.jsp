<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="main/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tr class="info">
					<th>학번</th>
					<th>이름</th>
					<th>국어</th>
					<th>영어</th>
					<th>수학</th>
					<th>총점</th>
					<th>평균</th>
				</tr>
				<c:forEach var="vo" items="${list }">
					<tr>
						<td>${vo.hakbun }</td>
						<td>${vo.name }</td>
						<td>${vo.kor }</td>
						<td>${vo.eng }</td>
						<td>${vo.math }</td>
						<td>${vo.total }</td>
						<td>${vo.avg }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>