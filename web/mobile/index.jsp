<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<script src="../js/jquery.js"></script>
<script src="../js/mobile/jquery.mobile-1.3.1.js"></script>
<link href="../css/mobile/jquery.mobile-1.3.1.css" rel="stylesheet" media="screen">
<!-- <script type="text/javascript" src="//cdn.jsdelivr.net/knockout/2.2.1/knockout-2.2.1.js"></script> -->


</head>
<body>
	<div data-role="page" data-theme="b" id="indexPage">

		<div data-role="header" data-theme="b" data-icon="home">
			<h1>EasyMark 로그인</h1>
		</div><!-- /header -->


		<div data-role="content" data-theme="b">	
		<br>
		<!--Content start-->
			<!--Login form start-->
			<form id="loginForm" action="mobile_login" method="post" data-ajax="false">
	
				<div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
		         <label for="name" class="ui-input-text">Username:</label>
		         <input type="text" value="" id="loginId" name="loginId" class="ui-input-text ui-body-null ui-corner-all ui-shadow-inset ui-body-c">
				</div>
	
				<div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
		         <label for="name" class="ui-input-text">Password:</label>
		         <input type="password" value="" id="loginPassword" name="loginPassword" class="ui-input-text ui-body-null ui-corner-all ui-shadow-inset ui-body-c">
				</div>
	
				<fieldset class="ui-grid-a">
						<div class="ui-block-a"><button id="loginButton" data-theme="b" type="button" class="ui-btn-hidden" aria-disabled="false">Login</button></div>
						<div class="ui-block-b"><button data-theme="b" type="reset" class="ui-btn-hidden" aria-disabled="false">Clear</button></div>
		
			    </fieldset>

			</form>
			<!-- /Login form end-->
		<hr>
		계정이 없으신가요? <a data-rel="dialog" data-transition="pop" href="#registerPage">가입하기</a>
	<!--Content end-->
	</div>

	<!-- /page -->
	</div>
	
	
	<div data-role="page" data-theme="b" id="registerPage">
	<div data-role="header" data-theme="b">
		<h1>EasyMark 회원가입</h1>
	</div>
	<div data-role="content" data-theme="b">
		<!--Register form start-->
		<form id="registerForm" action="mobile_register" method="post" data-ajax="false">

			<div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
	        	<label for="name" class="ui-input-text">ID:</label>
	        	<input type="text" value="" id="userId" name="userId" class="ui-input-text ui-body-null ui-corner-all ui-shadow-inset ui-body-c">
	        	<span id="idCheckResult"></span>
			</div>
			
			<div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
	        	<label for="name" class="ui-input-text">user name:</label>
	        	<input type="text" value="" id="name" name="name" class="ui-input-text ui-body-null ui-corner-all ui-shadow-inset ui-body-c">
			</div>
			
			<div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
	        	<label for="name" class="ui-input-text">email:</label>
	        	<input type="email" value="" id="email" name="email" class="ui-input-text ui-body-null ui-corner-all ui-shadow-inset ui-body-c">
			</div>
			
			<div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
	        	<label for="name" class="ui-input-text">Password:</label>
	        	<input type="password" value="" id="password" name="password" class="ui-input-text ui-body-null ui-corner-all ui-shadow-inset ui-body-c">
			</div>
			
			<div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
	        	<label for="name" class="ui-input-text">Password 확인:</label>
	        	<input type="password" value="" id="passwordConfirm" name="passwordConfirm" class="ui-input-text ui-body-null ui-corner-all ui-shadow-inset ui-body-c">
			</div>

			<fieldset class="ui-grid-a">
				<div class="ui-block-a"><button id="registerButton" data-theme="b" type="button" class="ui-btn-hidden" aria-disabled="false">가입</button></div>
				<div class="ui-block-b"><button data-theme="b" type="reset" class="ui-btn-hidden" aria-disabled="false">Clear</button></div>
		    </fieldset>

		</form>
			<!-- /Register form end-->
		</div>
	</div>

</body>

<script src="../js/mobile/index.js"></script>
</html>