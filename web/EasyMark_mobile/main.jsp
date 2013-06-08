<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<script src="../js/jquery.js"></script>
<script src="../js/mobile/jquery.mobile-1.3.1.js"></script>
<link href="../css/mobile/jquery.mobile-1.3.1.css" rel="stylesheet" media="screen">
<!-- <script type="text/javascript" src="//cdn.jsdelivr.net/knockout/2.2.1/knockout-2.2.1.js"></script>
 -->


</head>
<body>
<div data-role="page">
	<div data-role="header" data-theme="b">
		<a href="#myInfo" data-icon="bars" data-shadow="false" data-iconshadow="false">Menu</a>
		<h1>${MEMBERID} - 메인 페이지</h1>
		<a onclick="javascript:logout('${MEMBERID}');">로그아웃</a>
	</div>
	
	<div data-role="content">
		<ul data-role="listview" data-inset="true">
			<c:forEach var="bookmark" items="${bookMarkList}">
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


<div data-role="panel" id="myInfo" data-theme="b">

	<form action="../updateMemberInfo" id="updateMemberInfoForm" method="POST"
						enctype="multipart/form-data">
	<div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
      	 	<label for="myInfo_password" class="ui-input-text">ID:</label>
       	<input readonly type="text" value="${MEMBERINFO.userId}" id="myInfo_userId" name="userId" class="ui-input-text ui-body-null ui-corner-all ui-shadow-inset ui-body-c">
	</div>
	
      <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
        <label for="myInfo_userName" class="ui-input-text">Username:</label>
        <input type="text" value="${MEMBERINFO.name}" id="myInfo_userName" name="setting_name" class="ui-input-text ui-body-null ui-corner-all ui-shadow-inset ui-body-c">
	</div>
	
	<div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
        <label for="myInfo_email" class="ui-input-text">email:</label>
        <input type="text" value="${MEMBERINFO.email}" id="myInfo_email" name="setting_email" class="ui-input-text ui-body-null ui-corner-all ui-shadow-inset ui-body-c">
	</div>

	<div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
        <label for="myInfo_password" class="ui-input-text">개인 이미지</label>
        <input type="file" id="myInfo_imgFile" name="file">
        <img style="width:75%; " src="../${MEMBERINFO.imgUrl}" id="myInfo_img">
	</div>

	</form>
	<fieldset class="ui-grid-a">
		<div class="ui-block-a"><button onclick="javascript:memberInfoModify()" id="loginButton" data-theme="b" type="button" class="ui-btn-hidden" aria-disabled="false">수정</button></div>
    </fieldset>
  </div><!-- /panel -->

</div>

</body>

</html>
