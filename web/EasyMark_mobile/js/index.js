$(document).ready(function(){
	init();
});

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
			url: 'idCheck',
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
		url: 'mobile_register',
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
		url: 'mobile_login',
		dataType: 'json',
		type: 'POST',
		data:{
			loginId: loginId,
			loginPassword: loginPassword
		}
	}).done(function(data){
		
		if(data[0].flag == true){
			
			//tipJS.action('EasyMark.fillMemberInfo',data[1]);
			$.mobile.changePage('Easymark_mobile/main.jsp',{
				transition:'slideup',
				reverse:true
			});
			/*tipJS.action('EasyMark.fillBookmarkList',data[2]);
			$('#bookmarkListView').listview("refresh");*/
		}
			
		else
			alert('아이디와 비밀번호를 확인하세요.');

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

// 회원정보 수정 버튼을 클릭했을 때 호출
var memberInfoModify = function(){
	var userId = $('#myInfo_userId').val();
	var name = $('#myInfo_userName').val();
	var email = $('#myInfo_email').val();
	var img = $('#myInfo_imgFile').val();
	
	if (img == '') {
		$.ajax({
			url : 'updateMemberInfo',
			dataType : 'json',
			type : 'POST',
			data : {
				userId : userId,
				setting_name : name,
				setting_email : email
			}
		}).done(function(data) {
			alert('회원 정보를 수정하였습니다.');
		});
	} else {
		console.log('submit');
		$("#updateMemberInfoForm").ajaxSubmit({
			dataType : 'html',
			success : function(data, rst) {
				var imgUrl = JSON.parse(data).imgUrl;
				$('#myInfo_img').attr('src', '../'+imgUrl);
				alert('회원 정보를 수정하였습니다.');
			}
		});
	}
};

var logout = function(userId){
	console.log('logout: ' + userId);
	// 로그아웃하는 코드를 넣는다.
	
	$.ajax({
		url:'mobile_logout'
	}).done(function(data){
		$.mobile.changePage('index.html');
	});
};