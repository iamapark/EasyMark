<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, dto.Member, controller.MobileController" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String userId = (String) session.getAttribute("MEMBERID");
	ArrayList<Member> friendList = new MobileController().getFriendList(userId);
	
	pageContext.setAttribute("friendList", friendList);
			
%>
<!DOCTYPE html> 
<html> 
    <head> 
    <title>친구목록</title> 
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
        <h1>친구 목록</h1>
    </div><!-- /header -->
 
    <div data-role="content"> 
   <ul data-role="listview" data-inset="true" data-filter="true" data-filter-placeholder="Search friend">
   <c:forEach var="friendList" items="${friendList}">
	   <li data-role="list-driver" data-theme="b">
			${friendList.userId}
	   </li>
	   <li>
		   <img src="../${friendList.imgUrl}">
		   <h3>${friendList.name}</h3>
		   <p>
		   	  ${friendList.email}
		   </p>
	   </li>
   </c:forEach>
</ul>

    </div><!-- /content -->
 
   <div data-role="footer" data-position="fixed" data-theme="b">
		<div data-role="navbar">
			<ul>
				<li>
					<a href="main.jsp?currentCategory=0">북마크</a>
				</li>
				<li>
					<a href="message.jsp?userId=${MEMBERID}">쪽지</a>
				</li>
			</ul>
		</div>
	</div><!-- /footer -->
</div><!-- /page -->
</body>
</html>