
var init = function(){
	$('#loginButton').click(login);
	$('#registerButton').click(register);
	
	$('#userId').focusout(idCheck);
	$('#friends').click(friends);
	$('#message').click(message);
	
};
    
var idCheck = function(){
	console.log('idCheck');
	
	var userId = $('#userId').val();
	
	if(userId == ''){
		$('#idCheckResult').text('아이디를 입력해주세요.').css('color', 'red');
		return false;
	}else{
		$('#idCheckResult').empty();
		
		$.ajax({
			url: '../idCheck',
			dataType: 'json',
			type: 'POST',
			data: {
				userId: userId
			}
		}).done(function(data){
			console.log(data);
			
			if(data == true){ // 가입 가능한 아이디를 입력했을 경우
				$('#idCheckResult').text('가입 가능한 아이디입니다.').css('color', 'blue');
			}else{ // 이미 존재하는 아이디를 입력했을 경우
				$('#idCheckResult').text('이미 존재하는 아이디입니다.').css('color', 'red');
			}
		});
	}
};

var register = function(){
	
	var userId = $('#userId').val();
	var name = $('#name').val();
	var email = $('#email').val();
	var password = $('#password').val();
	var passConfirm = $('#passwordConfirm').val();
	
	if(userId == ''){
		alert('아이디를 입력하세요.');
		$('#userId').focus();
		return false;
	}else if(name == ''){
		alert('이름을 입력하세요.');
		$('#name').focus();
		return false;
	}else if(email == ''){
		alert('이메일 주소를 입력하세요.');
		$('#email').focus();
		return false;
	}else if(password == ''){
		alert('비밀번호를 입력하세요.');
		$('#password').focus();
		return false;
	}else if(passConfirm == ''){
		alert('비밀번호 확인 창을 입력하세요.');
		$('#passwordConfirm').focus();
		return false;
	}else if(password != passConfirm){
		alert('비밀번호와 비밀번호 확인을 동일하게 입력하세요.');
		$('#passwordConfirm').focus();
		return false;
	}
	
	$.ajax({
		url: '../mobile_register',
		dataType: 'json',
		type: 'POST',
		data: {
			userId: userId,
			name: name,
			email: email,
			password: password
		}
	}).done(function(data){
		console.log(data);
		keke = data;
		
		if(data == true){
			console.log('회원가입에 성공했습니다.');
		}else
			console.log('회원가입에 실패했습니다.');
	});
	
};

var login = function(){
	
	var loginId = $('#loginId').val();
	var loginPassword = $('#loginPassword').val();
	
	if(loginId == ''){
		alert('아이디를 입력하세요');
		$('#loginId').focus();
		return false;
	}else if(loginPassword == ''){
		alert('비밀번호를 입력하세요.');
		$('#loginPassword').focus();
		return false;
	}
	
	$.ajax({
		url: '../mobile_login',
		dataType: 'json',
		type: 'POST',
		data:{
			loginId: loginId,
			loginPassword: loginPassword
		}
	}).done(function(data){
		
		if(data[0].flag == true){
			console.log('로그인 성공');
			$.mobile.changePage('main.jsp');
		}
			
		else
			console.log('로그인 실패');
		
		console.log(data);
		keke = data;
	});
};


var friends=function(){
	console.log('친구 눌럿당!');
	$.ajax({
		url:'../friends',
		dataType:'json',
			type:'Post',			
	});//.done(function(data){
		$.mobile.changePage('friends.jsp');
	//});
};

var message=function(){
	console.log('메세지 눌럿당!');
	$.ajax({
		url:'../message',
		dataType:'json',
			type:'Post',			
	});//.done(function(data){
		$.mobile.changePage('message.jsp');
	//});
};


// 초기화 함수
init();