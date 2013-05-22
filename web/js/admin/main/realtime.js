var loginMemberArray = new Array(); // 로그인 중인 사용자 정보를 담는 배열 변수

$(document).ready(function(){
	initRealtime();
	getLoginMemberList();
	getDashboardCount();
});


var initRealtime = function(){
	registerServer(); // admin 계정을 socket io에 connect 하는 함수
};

var registerServer = function(){
	var adminId = 'a';
	
	socket = io.connect('http://localhost:9091/admin', {'sync disconnect on unload' : true});
	socket.emit('adminId', {id:adminId});
	
	
	/*사용자가 로그인 할 때마다
	 * 현재 접속중인 회원 수를 push 받아서 #memberCount에 출력한다.*/
	socket.on('loginMemberCount', function(data){
		console.log('현재 접속중인 회원 수: ' + data.count);	
	});
	
	/*사용자가 로그인 할 때마다 해당 사용자의 정보를 push 받아서  memberInfoList에 출력한다.*/
	socket.on('loginMemberInfo', function(data){
		//data를 arrayList로 받아서 처리한다.
		if(loginMemberArray.indexOf(data) == -1){
			loginMemberArray.push(data);
			fillLoginMemberInfoTable(data); //테이블에 채우는 것은 tipJS를 사용할 것!
		}
	});
	
	/*사용자가 로그아웃 할 때마다
	 * 해당 사용자를 memberInfoList에서 삭제한다.*/
	socket.on('refreshLogoutMember', function(data){
		loginMemberArray.pop(data.userId);
		
	});
};

// 현재 로그인 중인 사용자 리스트를 받아온 후 memberInfoList에 출력한다.
var getLoginMemberList = function(){
	$.ajax({
		url:'getLoginMemberList',
		dataType:'json'
	}).done(function(data){
		// 처리 부분
	});
};

//대시보드 상단 네 개 카운트 정보를 채우는 함수.
var getDashboardCount = function(){
	
};