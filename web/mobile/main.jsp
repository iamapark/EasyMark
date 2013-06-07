<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<div data-role="page" id="bookmarkListPage" >
	<div data-role="header" data-theme="b">
		<a href="#myInfo" data-icon="bars" data-iconpos="notext" data-shadow="false" data-iconshadow="false">Menu</a>
		<h1>${MEMBERID}님 환영합니다</h1>
		<a href="#indexPage" id="logoutButton">로그아웃</a>
	</div>
	
	<div data-role="content">
		<ul data-role="listview" data-inset="true">
			<c:forEach var="bookmark" items="${bookMarkList}">
				<li data-role="list-driver" data-theme="b">
					${bookmark.bookMarkName}
					<span class="ui-li-count">${bookmark.frequency}</span>
				</li>
				<li>
					<c:if test="${bookmark.status=='category'}">
						<a href="category_page.html" data-categoryid="${bookmark.category}">
							<img src="../${bookmark.imgUrl}">
							<h3>${bookmark.bookMarkUrl}</h3>
							<p>
								${bookmark.bookMarkDescript}
							</p>
						</a>
					</c:if>
					
					<c:if test="${bookmark.status=='bookmark'}">
						<a target="_blank" href="${bookmark.bookMarkUrl}">
							<img src="../${bookmark.imgUrl}">
							<h3>${bookmark.bookMarkUrl}</h3>
							<p>
								${bookmark.bookMarkDescript}
							</p>
						</a>
					</c:if>
				</li>
			</c:forEach>
		</ul>
	</div>
	
	<div data-role="footer" data-position="fixed" data-theme="b">
		<div data-role="navbar">
			<ul>
				<li>
					<a href="#friend">친구</a>
				</li>
				<li>
					<a href="#message">쪽지</a>
				</li>
			</ul>
		</div>
	</div>
	<div data-role="panel" id="myInfo" data-theme="b">
		<div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
       	 	<label for="myInfo_password" class="ui-input-text">ID:</label>
        	<input readonly type="text" value="${MEMBERINFO.userId}" id="myInfo_userId" name="userId" class="ui-input-text ui-body-null ui-corner-all ui-shadow-inset ui-body-c">
		</div>
		
       <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
         <label for="myInfo_userName" class="ui-input-text">Username:</label>
         <input type="text" value="${MEMBERINFO.name}" id="myInfo_userName" name="userName" class="ui-input-text ui-body-null ui-corner-all ui-shadow-inset ui-body-c">
		</div>
		
		<div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
         <label for="myInfo_email" class="ui-input-text">email:</label>
         <input type="text" value="${MEMBERINFO.email}" id="myInfo_email" name="email" class="ui-input-text ui-body-null ui-corner-all ui-shadow-inset ui-body-c">
		</div>

		<div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
         <label for="myInfo_password" class="ui-input-text">개인 이미지</label>
         <img style="width:75%; " src="../${MEMBERINFO.imgUrl}" id="myInfo_img">
		</div>

		<fieldset class="ui-grid-a">
			<div class="ui-block-a"><button id="loginButton" data-theme="b" type="button" class="ui-btn-hidden" aria-disabled="false">수정</button></div>
	    </fieldset>
   </div><!-- /panel -->
   
   
	<div data-role="page" id="categoryPage">
		<div data-role="header" data-theme="b">
			카테고리 상세보기 페이지
		</div>
		
		<div data-role="content" data-theme="b">
			카테고리 안에 있는 카테고리 및 북마크 아이콘 리스트가 들어갈 공간입니다.
		</div>
	</div>
   
</div>



 <script src="../js/mobile/main.js"></script>