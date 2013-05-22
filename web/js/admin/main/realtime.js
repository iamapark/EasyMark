var loginMemberArray = new Array(); // 로그인 중인 사용자 정보를 담는 배열 변수
Array.prototype.removeElement = function(index){
	this.splice(index, 1);
	return this;
};

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
	 * 현재 접속중인 회원 수를 push 받아서 #loginMemberCount에 출력한다.*/
	socket.on('loginMemberCount', function(data){
		console.log('현재 접속중인 회원 수: ' + data.count);	
		$('#loginMemberCount').text(data.count);
	});
	
	/*사용자가 로그인 할 때마다 해당 사용자의 정보를 push 받아서  memberInfoList에 출력한다.*/
	socket.on('loginMemberInfo', function(data){
		console.log('loginMemberInfo');
		kaka = data;
		//data를 arrayList로 받아서 처리한다.
		if(loginMemberArray.indexOf(data) == -1){
			loginMemberArray.push(data);
			fillLoginMemberInfoTable(); //테이블에 채우는 것은 tipJS를 사용할 것!
		}
	});
	
	/*사용자가 로그아웃 할 때마다
	 * 해당 사용자를 memberInfoList에서 삭제한다.*/
	socket.on('refreshLogoutMember', function(data){
		
		console.log('refreshLogoutMember');
		
		for(var i=0; i<loginMemberArray.length; i++){
			if(loginMemberArray[i].userId == data.userId)
				loginMemberArray.removeElement(i);
		}
		
		//loginMemberArray.pop(data.userId);
		fillLoginMemberInfoTable(); // 테이블을 다시 그리게 한다.
	});
	
	/**사용자가 가입할 때마다
	 * 회원 수 카운트를 dashboard에 push 한다.*/
	socket.on('pushRegisterMemberCount', function(data){
		$('#totalMemberCount').text(data.memberCount);
		$('#todayRegisterCount').text(data.todayRegisterCount);
	});
};

// 현재 로그인 중인 사용자 리스트를 받아온 후 memberInfoList에 출력한다.
var getLoginMemberList = function(){
	$.ajax({
		url:'getLoginMemberInfoList',
		dataType:'json'
	}).done(function(data){
		console.log('getLoginMemberInfoList');
		kaka = data;
		for(var i=0; i<data.length; i++){
			loginMemberArray.push(data[i]);
		}
		fillLoginMemberInfoTable();
	}).fail(function(e){
		console.log('getLoginMemberInfoList fail');
		kaka = e;
	});
};

//대시보드 상단 네 개 카운트 정보를 채우는 함수.
var getDashboardCount = function(){
	$.ajax({
		url:'getDashboardCount',
		dataType:'json'
	}).done(function(data){
		$('#totalMemberCount').text(data.memberCount);
		$('#todayRegisterCount').text(data.todayRegisterCount);
		$('#loginMemberCount').text(data.loginMemberCount);
		$('#messageCount').text(data.messageCount);
	}).fail(function(e){
		$('#totalMemberCount').text('데이터를 불러올 수 없습니다.');
		$('#todayRegisterCount').text('데이터를 불러올 수 없습니다.');
		$('#loginMemberCount').text('데이터를 불러올 수 없습니다.');
		$('#messageCount').text('데이터를 불러올 수 없습니다.');
	});
};


// 현재 접속 중인 회원 리스트를 채우는 함수
var fillLoginMemberInfoTable = function(){
	
	$('#loginMemberListUl li').remove();
	
	var li = '';
	
	for(var i=0; i<loginMemberArray.length && i<10; i++){
		li += '<li>';
			li += '<a href="#">';
				li += '<span>' + loginMemberArray[i].userId + '</span>';
				li += '<span class="green">' + loginMemberArray[i].name + '</span>';
				li += '<span>' + loginMemberArray[i].email + '</span>';
			li += '</a>';
		li += '</li>';
	}
	
	$('#loginMemberListUl').append(li);
};