<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model" class="com.sist.model.Model"></jsp:useBean>
<%
	//Model => 메소드로 전송
	model.reply_insert(request);
	//이동 => detail.jsp
%>
<c:redirect url="detail.jsp?no=${bno }&page=${page }"/>