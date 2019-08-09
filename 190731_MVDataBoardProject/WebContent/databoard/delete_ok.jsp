<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model" class="com.sist.model.Model"></jsp:useBean>
<%
	model.databoard_delete_ok(request);
%>
<c:if test="${bCheck==true }">
	<c:redirect url="list.do"/>
</c:if>
<c:if test="${bCheck==false }">
	<script>
		alert("비밀번호가 틀립니다.");
		history.back();
	</script>
</c:if>