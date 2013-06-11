<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE HTML>
<html lang="en-US" class="no-js">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" media="screen" href="css/sleepPage/style.css"/>
<title>${MEMBERINFO.userId}님 계정이 휴면 상태입니다.</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/admin/jquery-ui-1.8.21.custom.js"></script>
<script type="text/javascript" src="js/modernizr.js"></script>
<script type="text/javascript" src="js/sleepPage/fix-and-clock.js"></script>
</head>

<body>

<!-- LOGIN -->
<div id="pageLogin">
    <div class="user-avatar">
        <div id="avatar">
        <a href="#hide" class="hide" id="hide"></a>
        <a href="#show" class="show" id="show"></a>
        <div id="cover"></div>
        <div class="ava-css">
        <c:choose>
        	<c:when test="${MEMBERINFO.imgUrl != null}">
        		<img src="${MEMBERINFO.imgUrl}" />
        	</c:when>
        	<c:otherwise>
        		<img src="images/defaultProfile.jpg" />
        	</c:otherwise>
        </c:choose>
        </div>
        <div class="logName">
        <p>${MEMBERINFO.userId}</p>
        </div>
        <div id="switch">
        <div class="validate">
        <form action="login" id="loginForm" method="POST">
        	<input type="hidden" name="loginId" id="userId" value="${MEMBERINFO.userId}">
        	<input type="password" name="loginPassword" id="password" placeholder="비밀번호" />
            <input type="submit" class="submit" />
            <div class="tooltip-pass">
            <p>비밀번호를 입력하세요.</p>
            </div>
            </form>
        </div>
        </div>
        </div>
        </div>
    </div>
</body>
</html>
