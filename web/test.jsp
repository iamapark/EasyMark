<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="util.SessionManager" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	session.setAttribute("users", "park"); 
%>
<h1>세션 확인</h1>
<p>
	현재 로그인 유저(5분 이내): <%=SessionManager.getInstance().count() %> / 
	전체 세션 <%=SessionManager.getInstance().totalCount() %>
</p>
</body>
</html>