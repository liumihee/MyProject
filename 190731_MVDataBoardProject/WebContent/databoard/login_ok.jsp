<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model" class="com.sist.model.Model"></jsp:useBean>
<%
	model.login(request);
%>
<c:choose>
	<c:when test="${result=='NOID' }">
		<script>
			alert("아이디가 존재하지 않습니다.");
			history.back();
		</script>
	</c:when>
	<c:when test="${result=='NOPWD' }">
		<script>
			alert("비밀번호가 틀립니다.");
			history.back();
		</script>
	</c:when>
	<c:otherwise>
		<c:redirect url="list.jsp"/>
	</c:otherwise>
</c:choose>