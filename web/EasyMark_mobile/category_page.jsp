<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ page import="controller.MobileController, dto.BookMark, java.util.*" %>    
<%
	MobileController mc = new MobileController();

	int categoryId = Integer.parseInt(request.getParameter("currentCategory"));
	int beforeCategoryId = mc.getParentId(categoryId);
	String userId = (String) session.getAttribute("MEMBERID");
	String pageName = "";
	
	
	
	ArrayList<BookMark> bookmarkList = mc.getBookMarkList(categoryId, userId);
	String categoryName = mc.getCategoryName(categoryId);
	
	pageContext.setAttribute("categoryName", categoryName);
	pageContext.setAttribute("bookmarkList", bookmarkList);
	
	if(beforeCategoryId == 0){
		pageName = "main.jsp";	
		System.out.println("메인으로 페이지로");
	}else{
		pageName = "category_page.jsp";
		System.out.println("카테고리 페이지로");
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div data-role="page">
		<div data-role="header" data-theme="b">
			<a href="#" data-icon="bars"  data-shadow="false" data-iconshadow="false" data-rel="back" data-transition="slide" data-direction="reverse">뒤로</a>
			<h1>${categoryName}</h1>
			<a href="#indexPage" id="logoutButton">로그아웃</a>
		</div>
		
		<div data-role="content">
			<ul data-role="listview" data-inset="true">
				<li data-role="list-driver" data-theme="b">
					상위 카테고리로
				</li>
				<li>
					<a href="<%=pageName%>?currentCategory=<%=beforeCategoryId%>" data-categoryid="${bookmark.category}" data-transition="slide">
						<img src="../images/uparrow.png">
						<h3>${bookmark.bookMarkName}</h3>
					</a>
				</li>
			<c:forEach var="bookmark" items="${bookmarkList}">
				<c:if test="${bookmark.status=='category'}">
					<li data-role="list-driver" data-theme="b">
						카테고리
						<span class="ui-li-count">${bookmark.frequency}</span>
					</li>
					<li>
						<a href="category_page.jsp?currentCategory=${bookmark.category}" data-categoryid="${bookmark.category}" data-transition="slide">
							<img src="../${bookmark.imgUrl}">
							<h3>${bookmark.bookMarkName}</h3>
						</a>
					</li>
				</c:if>
				
				<c:if test="${bookmark.status=='bookmark'}">
					<li data-role="list-driver" data-theme="b">
						${bookmark.bookMarkName}
						<span class="ui-li-count">${bookmark.frequency}</span>
					</li>
					<li>
						<a target="_blank" href="${bookmark.bookMarkUrl}">
							<img src="../${bookmark.imgUrl}">
							<h3>${bookmark.bookMarkUrl}</h3>
							<p>
								${bookmark.bookMarkDescript}
							</p>
						</a>
					</li>
				</c:if>
			</c:forEach>
		</ul>
		</div>
		
		<div data-role="footer" data-position="fixed" data-theme="b">
			<div data-role="navbar">
				<ul>
					<li>
						<a href="friends.jsp?userId=${MEMBERINFO.userId}">친구</a>
					</li>
					<li>
						<a href="message.jsp?userId=${MEMBERINFO.userId}">쪽지</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>