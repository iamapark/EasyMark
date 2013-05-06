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
<body style="background:url(${MEMBERINFO.bgImgUrl});background-position: 100% 100%;">
 	
 	<jsp:include page="template/MacOS.jsp">
 		<jsp:param value="${designType}" name="flag"/>
	</jsp:include>	
	
	<jsp:include page="template/WindowsOS.jsp">
		<jsp:param value="${designType}" name="flag"/>
	</jsp:include>
	
	
	<!-- BookMark List -->
	<div>
	<div id="gridster" class="gridster">
    	<ul>
			<c:forEach items="${sessionScope.bookMarkList}"	var="bookMark">
			<li style= "vertical-align: middle;border-radius:20px;" data-toggle="tooltip" title="${bookMark.bookMarkName}" data-row="${bookMark.posX}" data-col="${bookMark.posY}" data-id="${bookMark.bookMarkId}" data-sizex="1" data-sizey="1" class="bookmarkIcon">
            	<a id='info' data-id="${bookMark.bookMarkId}" role="button" data-toggle="modal"  class='close' href=#bookMarkInfo  data-dismiss='modal' aria-hidden='true' style="position:absolute; right:4px;top:4px;">
            		<img id='wheel' src='images/wheel.png'>
           		</a>
            	<img id="img" href="http://${bookMark.bookMarkUrl}" src="${bookMark.imgUrl}" style="width:100%; height:100%;border-radius:20px;">
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
	<!-- 북마크 아이콘 우측 상단의 톱니바퀴를 클릭했을 때 MODAL -->
		<jsp:include page="modal/bookmarkInfo.jsp"></jsp:include>
 	<!-- MODAL END -->
	
	
	<script src="js/jquery.js"></script>
	<script src="js/jquery.form.js"></script>
	<script src="js/bootstrap/bootstrap.js"></script>
	<script src="js/bookmark/jquery.gridster.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/bookmark/bookmark.js"></script>
	<script src="js/membership/membership.js"></script>
	<!-- design:main -->
	<script type="text/javascript" src="js/main/MacOS.js" id="designSelectedJs"></script>
	<script type="text/javascript">
	  var gridster;
	
	  $(function(){
	    gridster = $(".gridster > ul").gridster({
	        widget_margins: [10, 10],
	        widget_base_dimensions: [140, 140],
	        min_cols: 6,
	        avoid_overlapped_widgets: true,
	       serialize_params: function($w, wgd) { return { col: wgd.col, row: wgd.row }; },
	    }).data('gridster');
	
	  });
	</script>
</body>
</html>