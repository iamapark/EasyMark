$(document).ready(function(){
	init();
	$('#registerButton').click(registerButtonClicked);
	$('#loginButton').click(loginButtonClicked);
	$('#userId').focusout(userIdFocusOut);
});


/**초기화 처리*/
var init = function(){
	$('#registerButton').attr('disabled', 'disabled'); // 회원가입 버튼을 비활성화 시킨다. 유효성 검사 후 통과되면 그 때 활성화
	$('#registerButton').css('background-color', 'gray');
};

/**
 * 'Sign up' 버튼을 눌렀을 경우 호출되는 함수
 * 회원가입 입력 양식에 대한 유효성 검사를 수행한다.
 * */
var registerButtonClicked = function(){
	console.log('dd');
	var $userId = $('#userId');
	var $name = $('#name');
	var $password = $('#password');
	var $confirmPassword = $('#confirmPassword');
	var $email = $('#email');
	var $firstName = $('#firstName');
	var $lastName = $('#lastName');
	
	if($userId.val() == ''){
		alert("아이디를 입력하세요.");
		$userId.focus();
		return;
	}else if($name.val() == ''){
		alert('이름을 입력하세요.');
		$name.focus();
		return;
	}else if($email.val() == ''){
		alert("이메일 주소를 입력해주세요.");
		$email.focus();
		return;
	}else if($password.val() == ''){
		alert("비밀번호를 입력하세요.");
		$password.focus();
		return;
	}else if($confirmPassword.val() == ''){
		alert("비밀번호 확인을 위해 한 번 더 입력해주세요.");
		$confirmPassword.focus();
		return;
	}else if($password.val() != $confirmPassword.val()){
		alert("비밀번호와 비밀번호 확인은 동일하게 입력해야 합니다.");
		$confirmPassword.focus();
		return;
	}else if($('input:checkbox[name="registerCheck"]').is(":checked") == false){
		alert('회원가입에 동의해 주세요.');
		return;
	}else{
		document.registerForm.submit();	
	}
};

var userIdFocusOut = function(){
	var userId = this.value;
	
	$.ajax({
		url: 'checkId',
		data:{
			userId : userId
		}
	}).done(function(data){ // true: 이미 가입된 아이디, false: 가입 가능한 아이디
		kaka = data;
		if(data != 'true'){ // 가입 가능한 아이디
			$('#registerButton').removeAttr('disabled'); 
			$('#registerButton').css('background-color', '#039bfc');
			$('#userIdCheck').text('가입 가능한 아이디입니다.');
			$('#userId').css('border', '1px solid #B2B2B2');
			
		}else{
			$('#registerButton').attr('disabled', 'disabled'); // 회원가입 버튼을 비활성화 시킨다. 유효성 검사 후 통과되면 그 때 활성화
			$('#registerButton').css('background-color', 'gray');
			$('#userIdCheck').text('이미 가입된 아이디입니다.');
			$('#userId').css('border', '1px solid red');
		}
	});
};

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

/**
 * '미투데이 계정으로 등록하기' 링크를 눌렀을 경우 호출되는 함수
 * */
var me2dayRegister = function(){
	console.log('me2dayRegister');
	// jQuery Ajax Call
	$.ajax({
		url: 'Proxy/proxy.jsp', // javascript same origin policy를 해결하기 위한 프록시
		dataType:'json',
		data: {
				url: 'http://me2day.net/api/get_auth_url.json?akey=415a2f7e4b506b19bc290a2061c05df5',
	   		  }
	}).done(function(data){
		location.href = data.url;
	});
};

/**
 * '미투데이 계정으로 로그인하기' 링크를 눌렀을 경우 호출되는 함수
 * */
var me2dayLogin = function(){
	// jQuery Ajax Call
	$.ajax({
		url: 'Proxy/proxy.jsp', //// javascript same origin policy를 해결하기 위한 프록시
		dataType:'json',
		data: {
				url: 'http://me2day.net/api/get_auth_url.json?akey=41e2b7d7b9e290113b43c2870a1373e9',
	   		  }
	}).done(function(data){
		location.href = data.url;
	});
};