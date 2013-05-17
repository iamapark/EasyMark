<%@page import="ch.qos.logback.core.joran.action.IncludeAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:if test="${sessionScope.MEMBERID == null}">
	<%
		response.sendRedirect("/EasyMark/");
	
	%>
</c:if>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>EasyMark-${MEMBERID}</title>
	<LINK REL="SHORTCUT ICON" HREF="http://sciactive.github.io/pnotify/includes/github-icon.png" />
	<link rel="stylesheet" type="text/css" href="css/bookmark/gridster/jquery.gridster.css">
	<link rel="stylesheet" type="text/css" href="css/bookmark/bookmark.css">
	<link rel="stylesheet" href="css/bootstrap/bootstrap.css" type="text/css" >
	<link rel="stylesheet" href="css/bookmark/jquery.contextmenu.css" type="text/css" >
	<!-- design:main -->
	<%
		String designType = (String)session.getAttribute("designType");
		if(designType.equals("MacOS")){
			%>
			<link href="css/main/MacOS.css" rel="stylesheet" type="text/css" id="designSelectedCss">
			<%
		}else{
			%>
			<link href="css/main/WindowsOS.css" rel="stylesheet" type="text/css" id="designSelectedCss">
			<%
		}
	%>

</head>
<body style="background:url(${MEMBERINFO.bgImgUrl}) no-repeat center center fixed;
             -webkit-background-size:cover; background-size:cover">
 	
 	<!-- Main Design Template -->
 	<jsp:include page="template/MacOS.jsp">
 		<jsp:param value="${designType}" name="flag"/>
	</jsp:include>	
	
	<jsp:include page="template/WindowsOS.jsp">
		<jsp:param value="${designType}" name="flag"/>
	</jsp:include>
	<!-- Main Design Template End-->
	
	<!-- BookMark List -->
	<div>
	<div id="gridster" class="gridster">
    	<ul>
			<c:forEach items="${sessionScope.bookMarkList}"	var="bookMark">
			<li style="" data-id="${bookMark.bookMarkId}" data-toggle="tooltip" title="${bookMark.bookMarkName}" data-row="${bookMark.posX}" data-col="${bookMark.posY}" data-id="${bookMark.bookMarkId}" data-sizex="1" data-sizey="1" data-bookmarkId="${bookMark.bookMarkId}" class="bookmarkIcon">
            	<img id="img" href="${bookMark.bookMarkUrl}" src="${bookMark.imgUrl}" style="width:100%; height:100%;border-radius:20px;">
            	<div class="bookmarkIconInfo">${bookMark.bookMarkName}</div>
            </li> 
			</c:forEach>
    	</ul>
    </div>
    </div>
	<!-- BookMark List END -->
	
	
	<!-- MODAL -->
	<!-- setting 메뉴를 클릭했을 때 MODAL -->
		<jsp:include page="modal/setting.jsp" />
	<!-- 북마크 추가 메뉴룰 클릭했을 때 MODAL -->
		<jsp:include page="modal/bookmarkAdd.jsp" />
	<!-- 북마크 아이콘 위에서 오른쪽 클릭 후 북마크 정보 탭을 클릭했을 때 MODAL -->
		<jsp:include page="modal/bookmarkInfo.jsp"></jsp:include>
	<!-- 친구 아이콘을 클릭했을 때 MODAL -->
		<jsp:include page="modal/friend.jsp"></jsp:include>
 	<!-- MODAL END -->
	
	
	<script src="js/jquery.js"></script>
	<script src="js/jquery.form.js"></script>
	<script src="js/bootstrap/bootstrap.js"></script>
	<script src="js/bookmark/jquery.gridster.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/bookmark/bookmark.js"></script>
	<script src="js/membership/membership.js"></script>
	<script src="js/friendship/friendship.js"></script>
	<script src="js/bookmark/jquery.contextmenu.js"></script>
	<!-- design:main -->
	<script type="text/javascript" src="js/main/MacOS.js" id="designSelectedJs"></script>
</body>
</html>
