<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
 
    <div data-role="header">
        <h1>FriendList</h1>
    </div><!-- /header -->
 
    <div data-role="content"> 
   <ul data-role="listview" data-inset="true" data-filter="true" data-filter-placeholder="Search friend">
   <c:forEach var="friendList" items="${friendList}">
    <li><a href="#">
        <img src="../${friendList.imgUrl}" id="${friendList.userId }">
        <h2>${friendList.name}</h2></a>
    </li>
    
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
					<a href="#message" id="message">쪽지</a>
				</li>
			</ul>
		</div>
	</div><!-- /footer -->
</div><!-- /page -->
</body>
<script src="../js/mobile/index.js"></script>
</html>