<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html> 
<html> 
    <head> 
    <title>메세지</title> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <script src="../js/jquery.js"></script>
	<script src="../js/mobile/jquery.mobile-1.3.1.js"></script>
	<link href="../css/mobile/jquery.mobile-1.3.1.css" rel="stylesheet" media="screen">
</head> 
<body> 
<!-- Start of second page -->
<div data-role="page" id="bar">
 
    <div data-role="header">
        <h1>MessageList</h1>
    </div><!-- /header -->
 
    <div data-role="content"> 
  <ul data-role="listview" data-inset="true">
  <c:forEach var="message" items="${messageList}" >
 
    <li data-role="list-divider"> <fmt:formatDate value="${message.messageDate}" pattern="yyyy-MM-dd"/> </li>
    <li><a href="index.html">
        <h2>${message.title}</h2>
         <p><strong>보낸사람 : ${message.userId}</strong></p>
        <p>${message.contents}</p>
        <p class="ui-li-aside"></p>
    </a></li>
    </c:forEach>
</ul>

    </div><!-- /content -->
 
   <div data-role="footer" data-position="fixed" data-theme="b">
		<div data-role="navbar">
			<ul>
				<li>
					<a href="#friends" id="friends">친구</a>
				</li>
				<li>
					<a href="#message" id="message">메세지</a>
				</li>
			</ul>
		</div>
	</div><!-- /footer -->
</div><!-- /page -->
</body>
<script src="../js/mobile/index.js"></script>
</html>