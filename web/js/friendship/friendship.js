/*document.write("<script src='js/admin/jquery-1.7.2.min.js'></script>");
*/
$('a[data-toggle="modal"]').click(function(){
	clearForm();
});

/**
<!-- friend 메뉴를 클릭했을 때 MODAL을 채울 정보를 가져온다. -->*/
$('a[href="#friendInfo"]').click(function(){
	$('.friendtable').dataTable().fnClearTable();
	memberId = null;
	$.ajax({
		url: 'friend'
	}).done(function(data){
		console.log(data);
		
		var memberData = JSON.parse(data);
		var action = null;
		
		for(var i=0; i<memberData.length; i++){
			memberId = memberData[i].userId;
			
			
			action = "<a id='delete' onclick='deleteFriend(this)' class='btn btn-small btn-danger' data-id='"+ memberData[i].userId + "' data-count='"+ i + "'>"+
					 "친구삭제"+
					 "</a>";
			$('.friendtable').dataTable().fnAddData([memberData[i].userId, memberData[i].name, memberData[i].email, action]);
		}
	});
});

/**
<!-- friend MODAL의 첫번째 tab : 친구 목록 -->*/
$('#friendTab li:eq(0) a').click(function (e){	
	$('.friendtable').dataTable().fnClearTable();
	
	$.ajax({
		url: 'friend'
	}).done(function(data){
		console.log(data);
		
		var memberData = JSON.parse(data);
		var action = null;
		
		for(var i=0; i<memberData.length; i++){
			action = "<a id='delete' onclick='deleteFriend(this)' class='btn btn-small btn-danger' data-id='"+ memberData[i].userId + "' data-count='"+ i + "'>"+
					 "친구삭제"+
					 "</a>";
			$('.friendtable').dataTable().fnAddData([memberData[i].userId, memberData[i].name, memberData[i].email, action]);
		}
	});
});

/**
<!-- friend MODAL의 두번째 tab : 친구 요청 보낸 목록 -->*/
$('#friendTab li:eq(1) a').click(function (e){	
	$('.sendfriendtable').dataTable().fnClearTable();
	
	$.ajax({
		url: 'sendFriendReq',
	}).done(function(data){
		console.log(data);
		
		var memberData = JSON.parse(data);
		var action = null;
		
		for(var i=0; i<memberData.length; i++){
			
			action = "<a id='cancel' onclick='cancel(this)' class='btn btn-small btn btn-warning' data-id='"+ memberData[i].friendId + "' data-count='"+ i + "'>"+
			 		 "요청취소"+
			 		 "</a>";
			
			$('.sendfriendtable').dataTable().fnAddData([memberData[i].friendId, action]);
		}
	});
});

/**
<!-- friend MODAL의 세번째 tab : 친구 요청 받은 목록 -->*/
$('#friendTab li:eq(2) a').click(function (e){	
	$('.takefriendtable').dataTable().fnClearTable();
	
	$.ajax({
		url: 'takeFriendReq',
	}).done(function(data){
		console.log(data);
		
		var memberData = JSON.parse(data);
		var action = null;
		
		for(var i=0; i<memberData.length; i++){
			action = "<a id='accept' onclick='acceptFriend(this)' class='btn btn-small btn btn-primary' data-id='"+ memberData[i].userId + "' data-count='"+ i + "'>"+
					 "수락"+
					 "</a>"+
					 "<a id='reject' onclick='rejectFriend(this)' class='btn btn-small btn btn-danger' data-id='"+ memberData[i].userId + "' data-count='"+ i + "'>"+
					 "거절"+
					 "</a>";
			
			$('.takefriendtable').dataTable().fnAddData([memberData[i].userId, action]);
		}
	});
});

/**
<!-- friend MODAL의 네번째 tab(첫번째 tab) : 북마크 추천 받은 목록 -->*/
$('#friendTab li:eq(3) a').click(function (e){	
	$('.inwebtable').dataTable().fnClearTable();
	console.log("받은");
	$.ajax({
		url: 'recommendInWeb',
	}).done(function(data){
		console.log(data);

		var memberData = JSON.parse(data);
		var action = null;
		for(var i=0; i<memberData.length; i++){
			action = "<a id='accept' onclick='recommendAccept(this)' class='btn btn-small btn btn-primary' data-id='"+memberData[i].bookMarkId+"' data-url='"+memberData[i].bookMarkUrl+"' data-name='"+memberData[i].bookMarkName+"' data-descript='"+memberData[i].bookMarkDescript+"' data-friend='"+memberData[i].friendId +"' data-count='"+i+"'>"+
			 		 "수락"+
			 		 "</a>"+
			 		 "<a id='reject' onclick='recommendReject(this)' class='btn btn-small btn btn-danger' data-id='"+ memberData[i].bookMarkId + "' data-count='"+ i + "'>"+
			 		 "거절"+
			 		 "</a>";
			$('.inwebtable').dataTable().fnAddData([memberData[i].userId, memberData[i].bookMarkUrl, memberData[i].bookMarkName, action]);
		
		}
	});
});


/**
<!-- friend MODAL의 네번째 tab(첫번째 tab) : 북마크 추천 받은 목록 -->*/
$('#webSiteTab li:eq(0) a').click(function (e){	
	console.log("받은");
	//$('.outwebtable').dataTable().fnClearTable();
	$('.inwebtable').dataTable().fnClearTable();
	
	$.ajax({
		url: 'recommendInWeb',
	}).done(function(data){
		console.log(data);
		
		var memberData = JSON.parse(data);
		var action = null;
		
		for(var i=0; i<memberData.length; i++){
			action = "<a id='accept' onclick='recommendAccept(this)' class='btn btn-small btn btn-primary' data-id='"+ memberData[i].bookMarkId + "' data-url='"+ memberData[i].bookMarkUrl + "' data-name='"+ memberData[i].bookMarkName + "' data-descript='"+ memberData[i].bookMarkDescript+"' data-count='"+ i + "'>"+
					 "수락"+
					 "</a>"+
					 "<a id='reject' onclick='recommendReject(this)' class='btn btn-small btn btn-danger' data-id='"+ memberData[i].bookMarkId + "' data-count='"+ i + "'>"+
					 "거절"+
					 "</a>";
			$('.inwebtable').dataTable().fnAddData([memberData[i].userId, memberData[i].bookMarkUrl, memberData[i].bookMarkName, action]);
		}
		$('.inwebtable').dataTable().fnOpen();
	});
	
});


/**
<!-- friend MODAL의 네번째 tab(두번째 tab) : 북마크 추천 보낸 목록 -->*/
$('#webSiteTab li:eq(1) a').click(function (e){	
	console.log("보낸");
	$('.outwebtable').dataTable().fnClearTable();
	
	$.ajax({
		url: 'recommendOutWeb',
	}).done(function(data){
		console.log(data);
		
		var memberData = JSON.parse(data);
		var action = null;
		for(var i=0; i<memberData.length; i++){
			action = "<a id='cancel' onclick='recommendCancel(this)' class='btn btn-small btn btn-warning' data-id='"+ memberData[i].bookMarkId + "' data-count='"+ i + "'>"+
	 		 		 "취소"+
	 		 		 "</a>";
			$('.outwebtable').dataTable().fnAddData([memberData[i].friendId, memberData[i].bookMarkUrl, memberData[i].bookMarkName, action]);
		}
	});
});






/**
<!-- 친구 삭제 -->*/

var deleteFriend = function(e){
	var friendId = $(e).data('id');
	var count = $(e).data('count');
	
	console.log(friendId);
	
	$.ajax({
		url: 'deleteFriend',
		data: {
			friendId: friendId,
		}
	}).done(function(data){
		$('.friendtable').dataTable().fnDeleteRow(count);
		$('.me2friendtable').dataTable().fnDeleteRow(count);
	}); 
};

/**
<!-- 친구 요청 취소 -->*/
var cancel = function(e){
	var friendId = $(e).data('id');
	var count = $(e).data('count');
	
	console.log(friendId);
	$.ajax({
		url: 'cancel',
		data: {
			friendId: friendId,
		}
	}).done(function(data){
		$('.me2friendtable').dataTable().fnDeleteRow(count);
		$('.sendfriendtable').dataTable().fnDeleteRow(count);
		$('.friendtable').dataTable().fnDeleteRow(count);
	}); 
};

/**
<!-- 친구 요청 수락 -->*/
var acceptFriend = function(e){
	var userId = $(e).data('id');
	var count = $(e).data('count');
	
	console.log(userId);
	$.ajax({
		url: 'accept',
		data: {
			userId: userId,
		}
	}).done(function(data){
		$('.me2friendtable').dataTable().fnDeleteRow(count);
		$('.takefriendtable').dataTable().fnDeleteRow(count);
		$('.friendtable').dataTable().fnDeleteRow(count);
	}); 
};

/**
<!-- 친구 요청 거절 -->*/
var rejectFriend = function(e){
	var userId = $(e).data('id');
	var count = $(e).data('count');
	
	console.log(userId);
	$.ajax({
		url: 'reject',
		data: {
			userId: userId,
		}
	}).done(function(data){
		$('.me2friendtable').dataTable().fnDeleteRow(count);
		$('.takefriendtable').dataTable().fnDeleteRow(count);
		$('.friendtable').dataTable().fnDeleteRow(count);
	}); 
};

/**
<!-- 친구 요청 보내기 -->*/
var addFriend = function(e){
	var friendId = $(e).data('id');
	var count = $(e).data('count');
	
	
	$.ajax({
		url: 'requestFriend',
		data: {
			friendId: friendId,
		}
	}).done(function(data){
		$('.me2friendtable').dataTable().fnDeleteRow(count);
		$('.friendtable').dataTable().fnDeleteRow(count);
	}); 
};

/**
<!-- 북마크 추천 취소 -->*/
var recommendCancel = function(e){
	var bookMarkId = $(e).data('id');
	var count = $(e).data('count');
	
	console.log(bookMarkId);
	$.ajax({
		url: 'recommendCancel',
		data: {
			bookMarkId: bookMarkId,
		}
	}).done(function(data){
		$('.outwebtable').dataTable().fnDeleteRow(count);
	}); 
};

/**
<!-- 북마크 추천 거절 -->*/
var recommendReject = function(e){
	var bookMarkId = $(e).data('id');
	var count = $(e).data('count');
	
	console.log(bookMarkId);
	$.ajax({
		url: 'recommendCancel',
		data: {
			bookMarkId: bookMarkId,
		}
	}).done(function(data){
		$('.inwebtable').dataTable().fnDeleteRow(count);
	}); 
};

/**
<!-- 북마크 추천 수락 -->*/
var recommendAccept = function(e){
	var bookMarkId = $(e).data('id');
	var bookMarkUrl = $(e).data('url');
	var bookMarkName = $(e).data('name');
	var bookMarkDescript = $(e).data('descript');
	
	var count = $(e).data('count');
	
	console.log(bookMarkId+":"+bookMarkUrl+":"+bookMarkName+":"+bookMarkDescript);
	$.ajax({
		url: 'recommendAccept',
		data: {
			bookMarkId: bookMarkId,
			bookMarkUrl : bookMarkUrl,
			bookMarkName : bookMarkName,
			bookMarkDescript : bookMarkDescript,
			
		}
	}).done(function(data){
		$('.inwebtable').dataTable().fnDeleteRow(count);
		
		// 바탕화면에 북마크 아이콘을 생성하는 부분이 들어가야 함.
	}); 
};


/**
<!-- 회원 검색 -->*/
function searchMember(userId) {
	var keyword = $('input[name="keyword"]').val();
	$('.friendtable').dataTable().fnClearTable();
	console.log(userId);
	
	$.ajax({
		url: 'memberList', 
		data: {
			keyword:keyword,
		}
	}).done(function(data){
		var memberData = JSON.parse(data);
		var action = null;
		console.log(userId);
		for(var i=0; i<memberData.length; i++){
			
			if(memberData[i].status == "친구"){
				if(memberData[i].userId == userId){
					action = "<a id='delete' onclick='deleteFriend(this)' class='btn btn-small btn-danger' data-id='"+ memberData[i].userId + "' data-count='"+ i + "'>"+
							 "친구삭제"+
							 "</a>";
			
					$('.friendtable').dataTable().fnAddData([memberData[i].friendId, memberData[i].name, "", action]);
				}
				else {
					action = "<a id='delete' onclick='deleteFriend(this)' class='btn btn-small btn-danger' data-id='"+ memberData[i].userId + "' data-count='"+ i + "'>"+
							 "친구삭제"+
							 "</a>";
			
					$('.friendtable').dataTable().fnAddData([memberData[i].userId, memberData[i].name, "", action]);
				}
			}
			
			else if(memberData[i].status == "친구요청"){
				if(memberData[i].userId == userId){
					action = "<a id='cancel' onclick='cancel(this)' class='btn btn-small btn btn-warning' data-id='"+ memberData[i].friendId + "' data-count='"+ i + "'>"+
							 "취소"+
							 "</a>";
			
					$('.friendtable').dataTable().fnAddData([memberData[i].friendId, memberData[i].name, "", action]);
				}
				else {
					action = "<a id='accept' onclick='acceptFriend(this)' class='btn btn-small btn btn-primary' data-id='"+ memberData[i].userId + "' data-count='"+ i + "'>"+
							 "수락"+
							 "</a>"+
							 "<a id='reject' onclick='rejectFriend(this)' class='btn btn-small btn btn-danger' data-id='"+ memberData[i].userId + "' data-count='"+ i + "'>"+
							 "거절"+
							 "</a>";
					$('.friendtable').dataTable().fnAddData([memberData[i].userId, memberData[i].name, "", action]);
				}
			}
			
			else if(memberData[i].status == "친구아님"){
				action = "<a id='add' onclick='addFriend(this)' class='btn btn-small btn btn-primary' data-id='"+ memberData[i].friendId + "' data-count='"+ i + "'>"+
						 "친구요청"+
						 "</a>";
				$('.friendtable').dataTable().fnAddData([memberData[i].friendId, memberData[i].name, "", action]);
				
			}
		}
	}); 
};


/**
<!-- 미투데이 연동 : 미투데이친구 + EasyMark 회원 불러오기 -->*/
var friendInfo;

function me2dayConnect(me2dayId){
	console.log('me2dayConnect6');
	var userId = me2dayId.split("@"); 
	console.log(userId[0]);
	
	console.log(userId);
	$.ajax({
		url: 'Proxy/proxy.jsp',
		dataType:'json',
		async:'false',
		data:{
			url:'http://me2day.net/api/get_friends/'+userId[0]+'.json?akey=8d41ef461f4c6e08dc8e235c2337db37'
		}
	}).done(function(data){
		
		friendInfo = data;
		console.log(data);
		var friendsId="";
		for(i=0; i<data.friends.length; i++)
		{
			friendsId = friendsId + data.friends[i].id+",";
		}
		console.log(friendsId);
		
		$('.me2friendtable').dataTable().fnClearTable();
		
		$.ajax({
			url: 'me2dayFriend',
			data: {
				friendsId:friendsId,
			}	
		}).done(function(data){
			console.log("data : "+data);
			
			var memberData = JSON.parse(data);
			var action = null;
			
			for(var i=0; i<memberData.length; i++){
				
				if(memberData[i].status == "친구"){
					if(memberData[i].userId == me2Id){
						action = "<a id='delete' onclick='deleteFriend(this)' class='btn btn-small btn-danger' data-id='"+ memberData[i].friendId + "' data-count='"+ i + "'>"+
								 "친구삭제"+
								 "</a>";
				
						$('.me2friendtable').dataTable().fnAddData([memberData[i].friendId, action]);
					}
					else {
						action = "<a id='delete' onclick='deleteFriend(this)' class='btn btn-small btn-danger' data-id='"+ memberData[i].userId + "' data-count='"+ i + "'>"+
								 "친구삭제"+
								 "</a>";
				
						$('.me2friendtable').dataTable().fnAddData([memberData[i].userId, action]);
					}
				}
				
				else if(memberData[i].status == "친구요청"){
					if(memberData[i].userId == me2Id){
						action = "<a id='cancel' onclick='cancel(this)' class='btn btn-small btn btn-warning' data-id='"+ memberData[i].friendId + "' data-count='"+ i + "'>"+
								 "취소"+
								 "</a>";
				
						$('.me2friendtable').dataTable().fnAddData([memberData[i].friendId, action]);
					}
					else {
						action = "<a id='accept' onclick='acceptFriend(this)' class='btn btn-small btn btn-primary' data-id='"+ memberData[i].userId + "' data-count='"+ i + "'>"+
								 "수락"+
								 "</a>"+
								 "<a id='reject' onclick='rejectFriend(this)' class='btn btn-small btn btn-danger' data-id='"+ memberData[i].userId + "' data-count='"+ i + "'>"+
								 "거절"+
								 "</a>";
						$('.me2friendtable').dataTable().fnAddData([memberData[i].userId, action]);
					}
				}
				
				else if(memberData[i].status == "친구아님"){
					action = "<a id='add' onclick='addFriend(this)' class='btn btn-small btn btn-primary' data-id='"+ memberData[i].friendId + "' data-count='"+ i + "'>"+
							 "친구요청"+
							 "</a>";
					$('.me2friendtable').dataTable().fnAddData([memberData[i].friendId, action]);
					
				}
				
			}
		});
	});
}



/********************  <!-- 메세지 modal --> ********************/


/**
<!-- message 메뉴를 클릭했을 때 MODAL을 채울 정보를 가져온다. 
	 첫번째 tab : 받은 메시지 목록 -->*/

$('a[href="#messages"]').click(function(){
	$('.takemessagetable').dataTable().fnClearTable();
	$.ajax({
		url: 'inBox'
	}).done(function(data){
		console.log(data);
		
		var memberData = JSON.parse(data);
		
		for(var i=0; i<memberData.length; i++){
			select = "<input type='checkbox' name='selector' value='" + memberData[i].messageId + "'></input>";
			$('.takemessagetable').dataTable().fnAddData([select, memberData[i].userId, "<a onclick='sendMessageDetail("+memberData[i].messageId+");' href='#messageView' data-toggle='modal'>"+memberData[i].contents+"</a>", memberData[i].messageDate2]);
		}
	});
});


/**
<!-- friend MODAL의 첫번째 tab : 받은 메시지 목록 -->*/
$('#messageTab li:eq(0) a').click(function (e){	
	$('.takemessagetable').dataTable().fnClearTable();
	$.ajax({
		url: 'inBox'
	}).done(function(data){
		console.log(data);
		
		var memberData = JSON.parse(data);
		for(var i=0; i<memberData.length; i++){
			select = "<input type='checkbox' name='selector' value='" + memberData[i].messageId + "'></input>";
			$('.takemessagetable').dataTable().fnAddData([select, memberData[i].userId, "<a onclick='sendMessageDetail("+memberData[i].messageId+");' href='#messageView' data-toggle='modal'>"+memberData[i].contents+"</a>", memberData[i].messageDate2]);
		}
	});
});


/**
<!-- friend MODAL의 두번째 tab : 보낸 메시지 목록 -->*/
$('#messageTab li:eq(1) a').click(function (e){	
	$('.sendmessagetable').dataTable().fnClearTable();
	
	$.ajax({
		url: 'outBox',
	}).done(function(data){
		console.log(data);
		
		var memberData = JSON.parse(data);
	
		for(var i=0; i<memberData.length; i++){
			select = "<input type='checkbox' name='selector' value='" + memberData[i].messageId + "'></input>";
			$('.sendmessagetable').dataTable().fnAddData([select, memberData[i].friendId, "<a onclick='sendMessageDetail("+memberData[i].messageId+");' href='#messageView' data-toggle='modal'>"+memberData[i].contents+"</a>", memberData[i].messageDate2]);
		}
	});
});


/**
<!-- 받은 메시지 상세 보기 -->*/
function takeMessageDetail(messageId){
	
	$.ajax({
		url: 'messageView',
		dataType:'json',
		data: {
			messageId: messageId,
		}
	}).done(function(data){
		fillTakeMessageDetail(data);
		$('#contents').remove();
	});
};

var fillTakeMessageDetail = function(data){
	$('#messageViewFriend').val(data[0].userId);
	$('#messageViewDate').text(data[0].messageDate2);
	$('#messageViewText').text(data[0].contents);
};

/**
<!-- 보낸 메시지 상세 보기 -->*/
function sendMessageDetail(messageId){
	
	$.ajax({
		url: 'messageView',
		dataType:'json',
		data: {
			messageId: messageId,
		}
	}).done(function(data){
		fillSendMessageDetail(data);
		$('#contents').remove();
	});
};

var fillSendMessageDetail = function(data){
	$('#messageViewFriend').val(data[0].friendId);
	$('#messageViewDate').text(data[0].messageDate2);
	$('#messageViewText').text(data[0].contents);
};


/**
<!-- 메시지 전송 -->*/
$('#messageSendButton').click(function(e){
	var messageFriendId = $('#messageFriendId').val();
	var messageContents = $('#messageContents').val();

	console.log('받는 사람: ' + messageFriendId);
	console.log('내용: ' + messageContents);
	
	if(messageFriendId == ""){
		alert("받는 아이디를 입력하세요.");
	}
	
	else if(messageContents == ""){
		alert("메시지 내용을 입력하세요.");
	}
	
	else {
		$.ajax({
			url: 'sendMessage',
			dataType:'json',
			data: {
					messageFriendId:messageFriendId,
					messageContents:messageContents
		   		  }
		}).done(function(data){
			console.log(data);
			$('#messageSendingButton').hide();
			$(this).show();
		});
	}
	
});

var socket;

$(window).load(function(){
	var userId = $('li[id="memberId"]').text().trim();
	if(userId){
		/**
		사용자는 로그인할때만 메시지 서버에 등록한다.*/
		$.ajax({
			url: 'isContains',
			dataType:'json',
			data:{
				userId:userId,
			}
		}).done(function(data){
			if(data == false){ // 로그인 시
				socketioConnection(userId);
			}
		});
	}
	
	window.onbeforeunload = goodbye;

	function goodbye() {
	    socket.emit('exit', {id:userId});
	}
	
});	


var socketioConnection = function(userId){
	socket = io.connect('http://localhost:9090/message', {'sync disconnect on unload' : true});
	socket.emit('userId', {id:userId});
	socket.on('message', function(data){
		console.log('쪽지: ' + data.msg);
		$.noty.consumeAlert({
			layout : 'topRight',
			type : 'success',
			dismissQueue : true
		});
		
		alert('쪽지가 도착했습니다.');
		$.noty.stopConsumeAlert();
	});
};
/**
<!-- 메시지 전송 종료 -->*/




/**
<!-- 받은 메시지 선택 삭제 -->*/

$('#takeMessageAllSelect').click(function(e){
	if($(this).text() == '전체 선택'){
		$($('input:checkbox'), $('#takeMessageTbl')).attr('checked', 'checked');
		$(this).text('선택 해제');
	}else{
		$($('input:checkbox'), $('#takeMessageTbl')).attr('checked', false);
		$(this).text('전체 선택');
	}
});

$('#takeMessageDelete').click(function(e){
	
	var data = $('input[name=selector]').serialize();
	
	if(data == ""){
		alert("삭제 할 메시지를 선택하세요.");
	}
	else {
		console.log(data);
		$.ajax({
			url:'deleteMessage',
			data:{
				messageId:data
			}
		}).done(function(data){
			
			for(var i=0; i<$('input[name=selector]').length; i++){
				if($('input[name=selector]')[i].checked){
					$('.takemessagetable').dataTable().fnDeleteRow(i);
				}
			}
		});
	}
});


/**
<!-- 보낸 메시지 선택 삭제 -->*/

$('#sendMessageAllSelect').click(function(e){
	if($(this).text() == '전체 선택'){
		$($('input:checkbox'), $('#sendMessageTbl')).attr('checked', 'checked');
		$(this).text('선택 해제');
	}else{
		$($('input:checkbox'), $('#sendMessageTbl')).attr('checked', false);
		$(this).text('전체 선택');
	}
});

$('#sendMessageDelete').click(function(e){
	
	var data = $('input[name=selector]').serialize();
	
	if(data == ""){
		alert("삭제 할 메시지를 선택하세요.");
	}
	else {
		console.log(data);
		$.ajax({
			url:'deleteMessage',
			data:{
				messageId:data
			}
		}).done(function(data){
			
			for(var i=0; i<$('input[name=selector]').length; i++){
				if($('input[name=selector]')[i].checked){
					$('.sendmessagetable').dataTable().fnDeleteRow(i);
				}
			}
		});
	}
});
	
