<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, dto.Message, controller.MobileController" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String userId = request.getParameter("userId");
	ArrayList<Message> messageList = new MobileController().getMessageList(userId);
	
	pageContext.setAttribute("messageList", messageList);
%>
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
 
    <div data-role="header" data-theme="b">
    	<a href="#" data-icon="bars"  data-shadow="false" data-iconshadow="false" data-rel="back" data-transition="slide" data-direction="reverse">뒤로</a>
        <h1>받은 쪽지함</h1>
    </div><!-- /header -->
 
    <div data-role="content"> 
  <ul data-role="listview" data-inset="true">
  <c:forEach var="message" items="${messageList}" >
 
    <li data-theme="b"data-role="list-divider"> <fmt:formatDate value="${message.messageDate}" pattern="yyyy-MM-dd HH:mm:ss"/> </li>
    <li>
        <h2>${message.title}</h2>
         <p><strong>보낸사람 : ${message.userId}</strong></p>
        <p>${message.contents}</p>
        <p class="ui-li-aside"></p>
    </li>
    </c:forEach>
</ul>

    </div><!-- /content -->
 
   <div data-role="footer" data-position="fixed" data-theme="b">
		<div data-role="navbar">
			<ul>
				<li>
					<a href="main.jsp?currentCategory=0" id="friends">북마크</a>
				</li>
				<li>
					<a href="friends.jsp?userId=${MEMBERINFO.userId}">친구</a>
				</li>
			</ul>
		</div>
	</div><!-- /footer -->
</div><!-- /page -->
</body>
<script src="../js/mobile/index.js"></script>
</html>