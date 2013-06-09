/*document.write("<script src='js/admin/jquery-1.7.2.min.js'></script>");
*/
var socket;

/**
<!-- login id 가져오기 -->*/
$(document).ready(function(){
	$.ajax({
		url: 'isContains',
		dataType:'json',
	}).done(function(data){
		userId = data.user;
		console.log("loginId:"+userId);
		socketioConnection(userId);
	});
	
});
var socketioConnection = function(userId){ // 시작 : 
	socket = io.connect('http://localhost:9090/message', {'sync disconnect on unload' : true}); // 이 주소로 커넥션 맺기
	
	/* 사용자가 로그인 할 때마다
	 * 로그인 한 아이디를 받아서 userId 로 보낸다. */
	socket.emit("userId", {id:userId}); 
	
	/* 사용자가 받은 메시지를 "message"로 받아 상단에 메시지 알림을 뿌려준다.
	 * 로그인 한 아이디를 받아서 userId 로 보낸다. */
	socket.on("message", function(data){ 
		$.noty.consumeAlert({
			layout : 'topRight', 
			type : 'success',
			dismissQueue : true
		});
		console.log("쪽지도착");
		alert("쪽지가 도착했습니다. \n friendId"+data.friend+"message : "+data.msg+"");
		$.noty.stopConsumeAlert();
		
		$('#messageCount').text("쪽지("+data.num+")"); // messageCount에 읽지 않은 새 메시지의 갯수를 출력		
	});
	
};

$('a[data-toggle="modal"]').click(function(){
	clearForm();
});

/**
<!-- friend 메뉴를 클릭했을 때 MODAL을 채울 정보를 가져온다. -->*/
$('a[href="#friendInfo"]').click(function(){
	$('.friendtable').dataTable().fnClearTable();
	memberId = null;
	$.ajax({
		url: 'getFriendList'
	}).done(function(data){
		console.log(data);
		
		var memberData = JSON.parse(data);
		var action = null;
		
		for(var i=0; i<memberData.length; i++){
			memberId = memberData[i].userId;
			
			
			action = "<a id='delete' onclick='deleteFriend(this)' class='btn btn-small btn-danger' data-id='"+ memberData[i].userId + "' data-count='"+ i + "'>"+
					 "친구 삭제"+
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
		url: 'getFriendList'
	}).done(function(data){
		console.log(data);
		
		var memberData = JSON.parse(data);
		var action = null;
		
		for(var i=0; i<memberData.length; i++){
			action = "<a id='delete' onclick='deleteFriend(this)' class='btn btn-small btn-danger' data-id='"+ memberData[i].userId + "' data-count='"+ i + "'>"+
					 "친구 삭제"+
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
		url: 'sendFriendReqList',
	}).done(function(data){
		console.log(data);
		
		var memberData = JSON.parse(data);
		var action = null;
		
		for(var i=0; i<memberData.length; i++){
			
			action = "<a id='cancel' onclick='cancel(this)' class='btn btn-small btn btn-warning' data-id='"+ memberData[i].friendId + "' data-count='"+ i + "'>"+
			 		 "친구 요청 취소"+
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
		url: 'takeFriendReqList',
	}).done(function(data){
		console.log(data);
		
		var memberData = JSON.parse(data);
		var action = null;
		
		for(var i=0; i<memberData.length; i++){
			action = "<a id='accept' onclick='acceptFriend(this)' class='btn btn-small btn btn-primary' data-id='"+ memberData[i].userId + "' data-count='"+ i + "'>"+
					 "수락"+
					 "</a>"+"&nbsp&nbsp&nbsp"+
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
	
	$.ajax({
		url: 'recommendInWeb',
	}).done(function(data){
		console.log(data);

		var memberData = JSON.parse(data);
		var action = null;
		for(var i=0; i<memberData.length; i++){
			action = "<a id='accept' onclick='recommendAccept(this)' class='btn btn-small btn btn-primary' data-id='"+memberData[i].bookMarkId+"' data-url='"+memberData[i].bookMarkUrl+"' data-name='"+memberData[i].bookMarkName+"' data-descript='"+memberData[i].bookMarkDescript+"' data-friend='"+memberData[i].friendId +"' data-count='"+i+"'>"+
			 		 "수락"+
			 		 "</a>"+"&nbsp&nbsp&nbsp"+
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
					 "</a>"+"&nbsp&nbsp&nbsp"+
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
	$(this).tab('show');
	$.ajax({
		url: 'recommendOutWeb',
	}).done(function(data){
		console.log(data);
		
		var memberData = JSON.parse(data);
		var action = null;
		for(var i=0; i<memberData.length; i++){
			action = "<a id='cancel' onclick='recommendCancel(this)' class='btn btn-small btn btn-warning' data-id='"+ memberData[i].bookMarkId + "' data-count='"+ i + "'>"+
	 		 		 "추천 취소"+
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
<!-- 북마크 추천 전송 -->*/
$('#sendButton').click(function(){
	
	var recommend_friendId = $('#recommend_friendId').val();
	var recommend_url = $('#recommend_url').val();
	var recommend_name = $('#recommend_name').val();
	var recommend_descript = $('#recommend_descript').val();
	
	$.ajax({
		url:'recommend',
		dataType : 'json',
		type:'POST',
		data:{
			recommend_friendId:recommend_friendId,
			recommend_url: recommend_url,
			recommend_name: recommend_name,
			recommend_descript: recommend_descript
		}
	}).done(function(data){
		console.log("data :"+data);
		
		if(data.toString() == "false"){
			alert('친구와의 북마크 추천을 확인하세요.');
		}
		else {
			alert('즐겨찾기를 친구에게 추천했습니다.');
			$('#bookMarkRecommand').modal('hide');
		}
	});
});


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
	var newLi;
	var id, x, y, url;
	
	dataInfo = {
		name:       $(e).data('name'),
		url: 		$(e).data('url'),
		description:$(e).data('descript')
	};
	console.log(bookMarkId+":"+bookMarkUrl+":"+bookMarkName+":"+bookMarkDescript);
	$.ajax({
		url: 'recommendAccept',
		dataType:'json',
		data: {
			bookMarkId: bookMarkId,
			bookMarkUrl : bookMarkUrl,
			bookMarkName : bookMarkName,
			bookMarkDescript : bookMarkDescript,
			
		}
	}).done(function(data){
		$('.inwebtable').dataTable().fnDeleteRow(count);
		console.log(data);
		id = data.id; x = data.x; y = data.y; url = data.url;
		alert('북마크가 추가되었습니다!!');
		newLi = '<li data-id="' + id + '" data-toggle="tooltip" title="'+dataInfo.name+'" data-row="'+x+'" data-col="'+y+'" data-sizex="1" data-sizey="1" class="bookmarkIcon gs_w">';
			newLi += '<img id="img" href="'+ url +'" src="images/Bookmark.png" style="width:100%; height:100%;border-radius:20px;">';
			newLi += '<div class="bookmarkIconInfo">' + dataInfo.name +'</div>';
		newLi += '</li>';
		gridster.add_widget(newLi, 1, 1);
		init();
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
							 "친구 삭제"+
							 "</a>";
			
					$('.friendtable').dataTable().fnAddData([memberData[i].friendId, memberData[i].name, memberData[i].email, action]);
				}
				else {
					action = "<a id='delete' onclick='deleteFriend(this)' class='btn btn-small btn-danger' data-id='"+ memberData[i].userId + "' data-count='"+ i + "'>"+
							 "친구 삭제"+
							 "</a>";
			
					$('.friendtable').dataTable().fnAddData([memberData[i].userId, memberData[i].name, memberData[i].email, action]);
				}
			}
			
			else if(memberData[i].status == "친구요청"){
				if(memberData[i].userId == userId){
					action = "<a id='cancel' onclick='cancel(this)' class='btn btn-small btn btn-warning' data-id='"+ memberData[i].friendId + "' data-count='"+ i + "'>"+
							 "친구 요청 취소"+
							 "</a>";
			
					$('.friendtable').dataTable().fnAddData([memberData[i].friendId, memberData[i].name,  memberData[i].email, action]);
				}
				else {
					action = "<a id='accept' onclick='acceptFriend(this)' class='btn btn-small btn btn-primary' data-id='"+ memberData[i].userId + "' data-count='"+ i + "'>"+
							 "수락"+
							 "</a>"+"&nbsp&nbsp&nbsp"+
							 "<a id='reject' onclick='rejectFriend(this)' class='btn btn-small btn btn-danger' data-id='"+ memberData[i].userId + "' data-count='"+ i + "'>"+
							 "거절"+
							 "</a>";
					$('.friendtable').dataTable().fnAddData([memberData[i].userId, memberData[i].name,  memberData[i].email, action]);
				}
			}
			
			else if(memberData[i].status == "친구아님"){
				action = "<a id='add' onclick='addFriend(this)' class='btn btn-small btn btn-primary' data-id='"+ memberData[i].friendId + "' data-count='"+ i + "'>"+
						 "친구 요청"+
						 "</a>";
				$('.friendtable').dataTable().fnAddData([memberData[i].friendId, memberData[i].name,  memberData[i].email, action]);
				
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
					if(memberData[i].userId == me2dayId){
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
					if(memberData[i].userId == me2dayId){
						action = "<a id='cancel' onclick='cancel(this)' class='btn btn-small btn btn-warning' data-id='"+ memberData[i].friendId + "' data-count='"+ i + "'>"+
								 "친구 요청 취소"+
								 "</a>";
				
						$('.me2friendtable').dataTable().fnAddData([memberData[i].friendId, action]);
					}
					else {
						action = "<a id='accept' onclick='acceptFriend(this)' class='btn btn-small btn btn-primary' data-id='"+ memberData[i].userId + "' data-count='"+ i + "'>"+
								 "수락"+
								 "</a>"+"&nbsp&nbsp&nbsp"+
								 "<a id='reject' onclick='rejectFriend(this)' class='btn btn-small btn btn-danger' data-id='"+ memberData[i].userId + "' data-count='"+ i + "'>"+
								 "거절"+
								 "</a>";
						$('.me2friendtable').dataTable().fnAddData([memberData[i].userId, action]);
					}
				}
				
				else if(memberData[i].status == "친구아님"){
					action = "<a id='add' onclick='addFriend(this)' class='btn btn-small btn btn-primary' data-id='"+ memberData[i].friendId + "' data-count='"+ i + "'>"+
							 "친구 요청"+
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
			select = "<input type='checkbox' onchange='takeMessageSelect(this)' name='selector_take' value='" + memberData[i].messageId + "'></input>";
			action = "<img src='images/new_image.GIF' />";
			if(memberData[i].readNum==0){
				$('.takemessagetable').dataTable().fnAddData([select, memberData[i].userId, "<a onclick='takeMessageDetail("+memberData[i].messageId+");' href='#messageView' data-toggle='modal'>"+memberData[i].contents+action+"</a>", memberData[i].messageDate2]);
			}else{
				$('.takemessagetable').dataTable().fnAddData([select, memberData[i].userId, "<a onclick='takeMessageDetail("+memberData[i].messageId+");' href='#messageView' data-toggle='modal'>"+memberData[i].contents+"</a>", memberData[i].messageDate2]);
			}
			
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
			console.log(memberData[i].readNum);
			select = "<input type='checkbox' name='selector_take' onchange='takeMessageSelect(this)' value='" + memberData[i].messageId + "'></input>";
			action = "<img src='images/new_image.GIF' />";
			if(memberData[i].readNum==0){
				$('.takemessagetable').dataTable().fnAddData([select, memberData[i].userId, "<a onclick='takeMessageDetail("+memberData[i].messageId+");' href='#messageView' data-toggle='modal'>"+memberData[i].contents+action+"</a>", memberData[i].messageDate2]);
			}else{
				$('.takemessagetable').dataTable().fnAddData([select, memberData[i].userId, "<a onclick='takeMessageDetail("+memberData[i].messageId+");' href='#messageView' data-toggle='modal'>"+memberData[i].contents+"</a>", memberData[i].messageDate2]);
			}
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
			select = "<input type='checkbox' onchange='sendMessageSelect(this)' name='selector_send' value='" + memberData[i].messageId + "'></input>";
			$('.sendmessagetable').dataTable().fnAddData([select, memberData[i].friendId, "<a onclick='sendMessageDetail("+memberData[i].messageId+");' href='#messageView' data-toggle='modal'>"+memberData[i].contents+"</a>", memberData[i].messageDate2]);
		}
	});
});

/**
<!-- friend MODAL의 세번째 tab : 메시지 보내기 - 친구 목록 불러오기 -->*/
$('#messageTab li:eq(2) a').click(function (e){	
	var optionTag = '';
	$('#messageFriendId').empty();
	
	$.ajax({
		url : 'getMessageFriendInfo',
		dataType : 'json',
	}).done(function(data) {
		for(var i=0; i<data.length; i++){
			optionTag += "<option value='" + data[i].userId +"'>";
				optionTag += data[i].userId;
			optionTag += "</option>";
		}
		$('#messageFriendId').append(optionTag);
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
		
		$.ajax({
			url:'messageCount',
			dataType : 'json',
			type:'POST'
		}).done(function(data){
			console.log("data :"+data.toString());
			$('#messageCount').text("쪽지("+data.toString()+")");		
			
		});
		
		$.ajax({
			url: 'inBox'
		}).done(function(data){
			$('.takemessagetable').dataTable().fnClearTable();
			var memberData = JSON.parse(data);
			for(var i=0; i<memberData.length; i++){
				console.log(memberData[i].readNum);
				select = "<input type='checkbox' name='selector' onchange='messageSelect(this)' value='" + memberData[i].messageId + "'></input>";
				action = "<img src='images/new_image.GIF' />";
				if(memberData[i].readNum==0){
					$('.takemessagetable').dataTable().fnAddData([select, memberData[i].userId, "<a onclick='takeMessageDetail("+memberData[i].messageId+");' href='#messageView' data-toggle='modal'>"+memberData[i].contents+action+"</a>", memberData[i].messageDate2]);
				}else{
					$('.takemessagetable').dataTable().fnAddData([select, memberData[i].userId, "<a onclick='takeMessageDetail("+memberData[i].messageId+");' href='#messageView' data-toggle='modal'>"+memberData[i].contents+"</a>", memberData[i].messageDate2]);
				}
			}
		});
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
<<<<<<< HEAD
=======
	messageContents = encodeURI(messageContents);
>>>>>>> 89241af2339ee60312316a600e2a9688606224d8
	
	console.log('받는 사람: ' + messageFriendId);
	console.log('내용: ' + messageContents);
		
	if(messageFriendId == ""){
		alert("받는 아이디를 입력하세요.");
	}
	
	else if(messageContents == ""){
		alert("메시지 내용을 입력하세요.");
	}
	
	else {
		alert("메시지를 전송하였습니다.");
		$.ajax({
			url: 'sendMessage',
			dataType:'json',
			data: {
					messageFriendId:messageFriendId,
					messageContents:messageContents
		   		  }
		}).done(function(data){
			friendId = data.friendId; message = data.contents; num = data.num;
			/** 전송하는 메시지를 보낸사람, 메시지 내용, 받는 사람의 새 메시지 갯수를 
			  "send"로 보낸다. */
			socket.emit("send", {friendId:friendId,message:message,num:num});
		});
	}
	
});


/**
<!-- 받은 메시지 선택 삭제 -->*/
$('#takeMessageAllSelect').click(function(e){
	if($(this).text() == '전체 선택'){
		$($('input:checkbox'), $('#takeMessageTbl')).prop('checked', true);
		$('#takeMessageTbl').find('tr').addClass('take_row_selected_message');
		$(this).text('선택 해제');
	}else{
		$($('input:checkbox'), $('#takeMessageTbl')).prop('checked', false);
		$('#takeMessageTbl').find('tr').removeClass('take_row_selected_message');
		$(this).text('전체 선택');
	}
});

$('#takeMessageDelete').click(function(e){
	
	var data = $('input[name=selector_take]').serialize();
	var selectedRow = $('#takeMessageTbl').dataTable().$('tr.take_row_selected_message');
	
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
			
			for(var i=0; i<selectedRow.length; i++){
				$('.takemessagetable').dataTable().fnDeleteRow(selectedRow[i]);
			}
			
			$.ajax({
				url:'messageCount',
				dataType : 'json',
				type:'POST'
			}).done(function(data){
				console.log("data :"+data.toString());
				$('#messageCount').text("쪽지("+data.toString()+")");		
			});
		});
	}
});

/**
<!-- 받은 메시지 체크박스 선택 -->*/
var takeMessageSelect = function(selected){
	console.log('selec');
	if ( $(selected).parent().parent().hasClass('take_row_selected_message') ) {
		$(selected).parent().parent().removeClass('take_row_selected_message');
    }
    else {
    	$(selected).parent().parent().addClass('take_row_selected_message');
    }
};

/**
<!-- 보낸 메시지 선택 삭제 -->*/

$('#sendMessageAllSelect').click(function(e){
	if($(this).text() == '전체 선택'){
		$($('input:checkbox'), $('#sendMessageTbl')).prop('checked', true);
		$('#sendMessageTbl').find('tr').addClass('send_row_selected_message');
		$(this).text('선택 해제');
	}else{
		$($('input:checkbox'), $('#sendMessageTbl')).prop('checked', false);
		$('#sendMessageTbl').find('tr').removeClass('send_row_selected_message');
		$(this).text('전체 선택');
	}
});

$('#sendMessageDelete').click(function(e){
	
	var data = $('input[name=selector_send]').serialize();
	var selectedRow = $('.sendmessagetable').dataTable().$('tr.send_row_selected_message');
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
			for(var i=0; i<selectedRow.length; i++){
				$('.sendmessagetable').dataTable().fnDeleteRow(selectedRow[i]);
			}
		});
	}
});

/**
<!-- 보낸 메시지 체크박스 선택 -->*/
var sendMessageSelect = function(selected){
	console.log('selec');
	if ( $(selected).parent().parent().hasClass('send_row_selected_message') ) {
		$(selected).parent().parent().removeClass('send_row_selected_message');
    }
    else {
    	$(selected).parent().parent().addClass('send_row_selected_message');
    }
};
