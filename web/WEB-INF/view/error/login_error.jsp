<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en"> <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Dark Login Form</title>
  <link rel="stylesheet" href="css/error/style.css">
  <!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>
<body>

	<div id="errorMsg" style="text-align: center;
margin-top: 55px;
font-size: 25px;
background-color: #ffebe8;
border: 1px solid #dd3c10;
width: 50%;
left: 25%;
margin-left: 25%;
margin-right: 25%; padding:10px;">
		아이디 또는 비밀번호가 잘못되었습니다.
	</div>

  <form name="loginForm" method="post" action="login" class="login">
    <p>
      <label for="login">Id:</label>
      <input type="text" name="loginId" id="loginId" placeholder="사용자 아이디">
    </p>

    <p>
      <label for="password">Password:</label>
      <input type="password" name="loginPassword" id="loginPassword" placeholder="비밀번호">
    </p>

    <p class="login-submit">
      <button onclick="javascript:loginButtonClicked(); return false;" id="loginButton" class="login-button">Login</button>
    </p>

<!--     <p class="forgot-password"><a href="index.html">Forgot your password?</a></p> -->
  </form>
  
  <script src="js/jquery.js"></script>
  <script type="text/javascript">
  
  var loginButtonClicked = function(){
		$loginId = $('#loginId');
		$loginPassword = $('#loginPassword');
		
		if($loginId.val() == ''){
			alert('아이디를 입력하세요.');
			$loginId.focus();
			return;
		}else if($loginPassword.val() == ''){
			alert('비밀번호를 입력하세요.');
			$loginPassword.focus();
			return;
		}else{
			document.loginForm.submit();
		}
	};
  </script>
</body>
</html>
