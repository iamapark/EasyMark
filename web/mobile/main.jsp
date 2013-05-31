<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<div data-role="page" id="bookmarkListPage" >
	<div data-role="header" data-theme="b">
	<button data-icon="gear">설정</button>
		<h1>${MEMBERID}님 환영합니다</h1>
	</div>
	
	<div data-role="content">
		<ul data-role="listview" data-inset="true">
			<c:forEach var="bookmark" items="${bookMarkList}">
				<li data-role="list-driver" data-theme="b">
					${bookmark.bookMarkUrl}
					<span class="ui-li-count">${bookmark.frequency}</span>
				</li>
				<li>
					<a href="${bookmark.bookMarkUrl}">
						<img src="../${bookmark.imgUrl}">
						<h3>${bookmark.bookMarkName}</h3>
						<p>
							${bookmark.bookMarkDescript}
						</p>
					</a>
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
</div>