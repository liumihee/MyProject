<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id = "model" class="com.sist.model.Model"/>
<%
	model.databoard_update_ok(request);
%>
<c:if test="${bCheck==true }">
	<c:redirect url="detail.jsp?no=${no }&page=${page }"/>
</c:if>
<c:if test="${bCheck==false }">
	<script>
		alert("비밀번호가 틀립니다.");
		history.back();
	</script>
</c:if>