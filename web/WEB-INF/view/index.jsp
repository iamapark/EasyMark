<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>EasyMark</title>
	<link href="css/index.css" rel="stylesheet" media="screen">
</head>
<body>
<div id="wrapper">
  <h1>EasyMark</h1>
  <div id="container">
	<section class="tabs">
	
		<input id="tab-1" type="radio" name="radio-set" class="tab-selector-1" checked="checked"/>
		<span for="tab-1" class="tab-label-1">Login</span>
		
		<input id="tab-2" type="radio" name="radio-set" class="tab-selector-2"  />
		<span for="tab-2" class="tab-label-2">Signup</span>

		<input id="tab-3" type="radio" name="radio-set" class="tab-selector-3" />
		<span for="tab-3" class="tab-label-3">Forget Password</span>
	
		<div class="clear-shadow"></div>
		
		<div id="content">
			<!-- 회원가입 양식 -->
			<div class="content-2">
				<p>
					<a href="#" class="media tw">Twitter</a>
					<a href="#" class="media me" onclick="me2dayRegister(); return false;">me2day</a>
				</p>	
				<form action="register" autocomplete="on" name="registerForm" method="POST">
				  <p>
					<label for="usernamesignup" class="uname" data-icon="i">Your ID</label> <label id="userIdCheck" style="float:right"></label>
					<input class="field" name="userId" id="userId" required="required" type="text" placeholder="사용자 아이디" />
				  </p>
				  <p>
					<label for="usernamesignup" class="uname" data-icon="u">Your name</label>
					<input class="field" name="name" id="name" required="required" type="text" placeholder="사용자 이름" />
				  </p>
				  <p>
					<label for="emailsignup" class="youmail" data-icon="e" > Your email</label>
					<input class="field" name="email" id="email" required="required" type="email" placeholder="이메일 주소"/>
				  </p>
				  <p>
					<label for="passwordsignup" class="youpasswd" data-icon="p">Your password </label>
					<input class="field" name="password" id="password" required="required" type="password" placeholder="비밀번호"/>
				  </p>
				  <p>
					<label for="passwordsignup_confirm" class="youpasswd" data-icon="p">Please confirm your password </label>
					<input class="field" name="passwordsignup_confirm" id="confirmPassword" required="required" type="password" placeholder="비밀번호 확인"/>
				  </p>
				  <p class="signin button">
					<input type="checkbox" value="registerCheck" required="required" name="registerCheck"/> 회원가입에 동의합니다.
					<input type="button" value="Sign up" id="registerButton" />
				  </p>
				</form>
			</div>
			
			<!-- 로그인 양식 -->
			<div class="content-1">
				<p>
					<a href="#" class="media tw">Twitter</a>
					<a href="#" class="media me" onclick="me2dayLogin(); return false;">me2day</a>
				</p>
				<form  action="login" autocomplete="on" method="POST" name="loginForm">
				  <p>
					<label for="username" class="uname" data-icon="u" > Your email or username </label>
					<input class="field" name="loginId" required="required" type="text" placeholder="사용자 아이디" id="loginId"/>
				  </p>
				  <p>	
					<label for="password" class="youpasswd" data-icon="p"> Your password </label>
					<input class="field" name="loginPassword" required="required" type="password" placeholder="비밀번호" id="loginPassword" />
				  </p>
				  <p class="keeplogin">
					<input type="checkbox" name="loginkeeping" id="loginkeeping" value="loginkeeping" /> Keep me logged in
					<input type="button" value="Login" id="loginButton" />
				  </p>
				</form>
			</div>
			
			<!-- 비밀번호 찾기 양식 -->
			<div class="content-3">
				<form  action="password" autocomplete="on">
				  <p>
					<label for="emailsignup" class="youmail" data-icon="e" > Your email</label>
					<input class="field" name="emailsignup" required="required" type="email" placeholder="myusername@gmail.com"/>
				  </p>
				  <p class="signin button">
					<input type="submit" value="Get New Password"/>
				  </p>
				</form>
			</div>
			
		</div>
	</section>
  </div>
</div>

<script src="js/jquery.js"></script>
<script src="js/membership/index.js"></script>
<script>
	
	/*모바일로 접속할 경우 모바일 페이지로 이동*/
	var connectionFilter = "win16|win32|win64|mac";
	 
	if( navigator.platform  ){
	    if( connectionFilter.indexOf(navigator.platform.toLowerCase())<0 ){
	        location.href="mobile";
	    }
	}
	
	var mobileKeyWords = new Array('iPhone', 'iPod', 'BlackBerry', 'Android', 'Windows CE', 'LG', 'MOT', 'SAMSUNG', 'SonyEricsson');
	for (var word in mobileKeyWords){
	    if (navigator.userAgent.match(mobileKeyWords[word]) != null){
	        location.href = "mobile";
	        break;
	    }
	}
</script>
<c:if test="${sessionScope.MEMBERID != null}">
	<% response.sendRedirect("loginSession"); %>
</c:if>
</body>
</html>