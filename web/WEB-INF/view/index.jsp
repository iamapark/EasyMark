<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
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
			<div class="content-2">
				<p>
					<a href="#" class="media tw">Twitter</a><a href="#" class="media fb">Facebook</a>
				</p>	
				<form  action="" autocomplete="on">
				  <p>
					<label for="usernamesignup" class="uname" data-icon="u">Your username</label>
					<input class="field" name="usernamesignup" required="required" type="text" placeholder="myusername" />
				  </p>
				  <p>
					<label for="emailsignup" class="youmail" data-icon="e" > Your email</label>
					<input class="field" name="emailsignup" required="required" type="email" placeholder="myusername@gmail.com"/>
				  </p>
				  <p>
					<label for="passwordsignup" class="youpasswd" data-icon="p">Your password </label>
					<input class="field" name="passwordsignup" required="required" type="password" placeholder="mypassword"/>
				  </p>
				  <p>
					<label for="passwordsignup_confirm" class="youpasswd" data-icon="p">Please confirm your password </label>
					<input class="field" name="passwordsignup_confirm" required="required" type="password" placeholder="mypassword"/>
				  </p>
				  <p class="signin button">
					<input type="checkbox" required="required" /> I agree with terms and conditions 
					<input type="submit" value="Sign up"/>
				  </p>
				</form>
			</div>
			<div class="content-1">
				<p>
					<a href="#" class="media tw">Facebook</a>
					<a href="#" class="media fb">Twitter</a>
					<a href="#" class="media me">Me2Day</a>
				</p>
				<form  action="" autocomplete="on">
				  <p>
					<label for="username" class="uname" data-icon="u" > Your email or username </label>
					<input class="field" name="username" required="required" type="text" placeholder="myusername or myusername@gmail.com"/>
				  </p>
				  <p>
					<label for="password" class="youpasswd" data-icon="p"> Your password </label>
					<input class="field" name="password" required="required" type="password" placeholder="mypassword" />
				  </p>
				  <p class="keeplogin">
					<input type="checkbox" name="loginkeeping" id="loginkeeping" value="loginkeeping" /> Keep me logged in
					<input type="submit" value="Login" />
				  </p>
				</form>
			</div>
			<div class="content-3">
				<form  action="" autocomplete="on">
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

<div>
	<ul>
		<li><a href="go.do?page=main">메인 페이지</a></li>
		<li><a href="go.do?page=main2">메인 페이지2</a></li>
	</ul>
</div>
</body>
</html>