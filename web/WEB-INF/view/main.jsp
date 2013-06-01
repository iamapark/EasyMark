<%@page import="ch.qos.logback.core.joran.action.IncludeAction, java.util.ArrayList, dto.BookMark"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>EasyMark-${MEMBERID}</title>
<<<<<<< HEAD
	<!-- bm <link href="http://jschr.github.io/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" /> -->
=======
>>>>>>> 76f24967d182d67c335ba65ace6c098714c227af
	<LINK REL="SHORTCUT ICON" HREF="http://sciactive.github.io/pnotify/includes/github-icon.png" />
	<link rel="stylesheet" href="css/superslide/superslide.css">
  	<link rel="stylesheet" href="css/superslide/layout.css">
	<link rel="stylesheet" type="text/css" href="css/bookmark/gridster/jquery.gridster.css">
	<link rel="stylesheet" type="text/css" href="css/bookmark/bookmark.css">
	
	<link rel="stylesheet" href="css/bootstrap/bootstrap.css" type="text/css" >
	<link rel="stylesheet" href="css/bootstrap/bootstrap-modal.css" type="text/css" >
	
	<link rel="stylesheet" href="css/bookmark/jquery.contextmenu.css" type="text/css" >
	<link href="css/dataTables/jquery.dataTables_themeroller.css" rel="stylesheet">
	<link href="css/dataTables/jquery.dataTables.css" rel="stylesheet">
	<link href="css/main.css" rel="stylesheet">
	
	<!-- design:main -->
	<%
		String designType = (String)request.getAttribute("designType");
		if(designType.equals("MacOS")){
			%>
			<link href="css/main/MacOS.css" rel="stylesheet" type="text/css" id="designSelectedCss">
			<%
		}else{
			%>
			<link href="css/main/WindowsOS.css" rel="stylesheet" type="text/css" id="designSelectedCss">
			<%
		}
		
		// 북마크 리스트의 개수를 체크하여 슬라이드 개수를 계산한다.
		// 한 화면 당 북마크 아이콘이 18개 들어간다고 가정.
		ArrayList<BookMark> bookmarkList = (ArrayList<BookMark>)request.getAttribute("bookMarkList");
		int numOfSlides = bookmarkList.size() / 24;
		if(bookmarkList.size() % 24 != 0)
			numOfSlides++;
		if(numOfSlides==0)
			numOfSlides = 1;
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
	
	<c:set var="numOfSlides" value="<%=numOfSlides-1 %>"></c:set>
	<!-- BookMark List -->
	<div id="slides">
	<ul class="slides-container">
		<c:forEach begin="0" end="${numOfSlides}" varStatus="status">
		<li>
			<div id="gridster${status.count}" class="gridster">
				<ul>
					<c:forEach items="${requestScope.bookMarkList}"	var="bookMark" begin="${(status.count-1)*24}" end="${(status.count-1)*24 + 23}">
					<li style="" data-id="${bookMark.bookMarkId}" data-toggle="tooltip" title="${bookMark.bookMarkName}" data-row="${bookMark.posX}" data-col="${bookMark.posY}" data-id="${bookMark.bookMarkId}" data-sizex="1" data-sizey="1" data-bookmarkId="${bookMark.bookMarkId}" class="bookmarkIcon">
		            	<img id="img" href="${bookMark.bookMarkUrl}" src="${bookMark.imgUrl}" style="width:100%; height:100%;border-radius:20px;">
		            	<div class="bookmarkIconInfo">${bookMark.bookMarkName}</div>
		            </li> 
					</c:forEach>
				</ul>
			</div>
		</li>
	</c:forEach>
	</ul>
	<nav class="slides-navigation">
      <a href="#" class="next">
        &gt;
      </a>
      <a href="#" class="prev">
        &lt;
      </a>
    </nav>
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
	<!-- 북마크 아이콘 위에서 오른쪽 클릭 후 북마크  추천했을 때  MODAL -->
		<jsp:include page="modal/bookmarkRecommand.jsp"></jsp:include>
	<!-- 메세지 아이콘을 클릭했을 때 MODAL -->	
		<jsp:include page="modal/message.jsp"></jsp:include>		
		
 	<!-- MODAL END -->
		
	<script src="js/jquery.js"></script>
	<script src="js/superslide/jquery.easing.1.3.js"></script>
  	<script src="js/superslide/jquery.superslide.js" type="text/javascript" charset="utf-8"></script>
  	<script src="js/superslide/application.js"></script>
	<script src="js/jquery.form.js"></script>
	<script src="js/dataTables/jquery.dataTables.min.js"></script>
	<script src="js/bootstrap/bootstrap.js"></script>
	<script src="js/bootstrap/bootstrap-modalmanager.js"></script>
	<script src="js/bootstrap/bootstrap-modal.js"></script>
	<script src="js/bookmark/jquery.gridster.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/bookmark/bookmark.js"></script>
	<script src="js/membership/membership.js"></script>
	<script src="js/friendship/friendship.js"></script>
	<script src="js/bookmark/jquery.contextmenu.js"></script>
	
	<script type="text/javascript" src="//datatables.net/download/build/jquery.dataTables.nightly.js"></script>
	
	<!-- bm <script src="http://jschr.github.io/bootstrap-modal/js/bootstrap-modalmanager.js"></script>
	<script src="http://jschr.github.io/bootstrap-modal/js/bootstrap-modal.js"></script>
	<script src="http://twitter.github.io/bootstrap/assets/js/bootstrap.js"></script> -->
	
	
	<!-- design:main -->
	<script type="text/javascript" src="js/main/MacOS.js" id="designSelectedJs"></script>
	<script src="tipJS/tipJS-MVC.js"></script>
	<script>
		var sleepTime = 0;
	
		var timer = function(){
			sleepTime++;
			if(sleepTime === 1000000){
				location.replace('sleepPage');
			}
		};
	
		setInterval("timer()", 1000);
	
		$(document).mousemove(function(e){
			sleepTime = 0;
		});
	</script>
</body>
</html>
