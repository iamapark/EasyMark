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

$('#friendTab li:eq(3) a').click(function (e){	
	//$('.inwebtable').dataTable().fnClearTable();
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


$('#webSiteTab li:eq(0) a').click(function (e){	
	console.log("받은");
	//$('.inwebtable').dataTable().fnClearTable();
	$('.outwebtable').dataTable().fnClearTable();
	//$('.inwebtable').dataTable().fnClearTable();
	$('.inwebtable').dataTable().fnOpen();
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
	});
	
});

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







$('a[href="#messages"]').click(function(){
	$('.takemessagetable').dataTable().fnClearTable();
	memberId = null;
	$.ajax({
		url: 'inBox'
	}).done(function(data){
		console.log(data);
		
		var memberData = JSON.parse(data);
		
		for(var i=0; i<memberData.length; i++){
			//action = "<input type='checkbox'>";
			//	action = "<input type='checkbox'>"; // onclick="messageDetail('+item.messageId+');" href="#messageView" data-toggle="modal"
			$('.takemessagetable').dataTable().fnAddData([memberData[i].messageId, memberData[i].userId, "<a onclick='takeMessageDetail("+memberData[i].messageId+");' href='#messageView' data-toggle='modal'>"+memberData[i].contents+"</a>", memberData[i].messageDate2]);
		}
		
		$("#takeMessageTbl tbody tr").on('click', function( e ) {
			keke = $(this);
		    if ( $(this).hasClass('row_selected') ) {
		        $(this).removeClass('row_selected');
		    }
		    else {
		    	//$('.bookmarktable').dataTable().$('tr.row_selected').removeClass('row_selected');
		        $(this).addClass('row_selected');
		    }
		    
		    if ( $(this).find('td').hasClass('td_selected') ) {
		        $(this).find('td').removeClass('td_selected');
		    }
		    else {
		    	$('.takemessagetable').dataTable().$('tr.td_selected').find('td').removeClass('td_selected');
		        $(this).find('td').addClass('td_selected');
		    }
		});
	});
});

$('#messageTab li:eq(0) a').click(function (e){	
	$('.takemessagetable').dataTable().fnClearTable();
	memberId = null;
	$.ajax({
		url: 'inBox'
	}).done(function(data){
		console.log(data);
		
		var memberData = JSON.parse(data);
		/*var action = null;
		var action2 = null;*/
		for(var i=0; i<memberData.length; i++){
			//action = "<input type='checkbox'></input>";
			$('.takemessagetable').dataTable().fnAddData([memberData[i].messageId, memberData[i].userId, "<a onclick='takeMessageDetail("+memberData[i].messageId+");' href='#messageView' data-toggle='modal'>"+memberData[i].contents+"</a>", memberData[i].messageDate2]);
		}
		
		$("#takeMessageTbl tbody tr").on('click', function( e ) {
			//keke = $(this);
		    if ( $(this).hasClass('row_selected') ) {
		        $(this).removeClass('row_selected');
		    }
		    else {
		    	//$('.bookmarktable').dataTable().$('tr.row_selected').removeClass('row_selected');
		        $(this).addClass('row_selected');
		    }
		    
		    if ( $(this).find('td').hasClass('td_selected') ) {
		        $(this).find('td').removeClass('td_selected');
		    }
		    else {
		    	$('.takemessagetable').dataTable().$('tr.td_selected').find('td').removeClass('td_selected');
		        $(this).find('td').addClass('td_selected');
		    }
		});
	});
});


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
	//kaka = data;
	$('#messageViewFriend').val(data[0].userId);
	$('#messageViewDate').text(data[0].messageDate2);
	$('#messageViewText').text(data[0].contents);
};


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
	//kaka = data;
	$('#messageViewFriend').val(data[0].friendId);
	$('#messageViewDate').text(data[0].messageDate2);
	$('#messageViewText').text(data[0].contents);
};

$('#messageTab li:eq(1) a').click(function (e){	
	$('.sendmessagetable').dataTable().fnClearTable();
	
	$.ajax({
		url: 'outBox',
	}).done(function(data){
		console.log(data);
		
		var memberData = JSON.parse(data);
	
		for(var i=0; i<memberData.length; i++){
			//action = "<input type='checkbox'>";
			$('.sendmessagetable').dataTable().fnAddData([memberData[i].messageId, memberData[i].friendId, "<a onclick='sendMessageDetail("+memberData[i].messageId+");' href='#messageView' data-toggle='modal'>"+memberData[i].contents+"</a>", memberData[i].messageDate2]);
		}
		
		$("#sendMessageTbl tbody tr").on('click', function( e ) {
			keke = $(this);
		    if ( $(this).hasClass('row_selected') ) {
		        $(this).removeClass('row_selected');
		    }
		    else {
		    	//$('.bookmarktable').dataTable().$('tr.row_selected').removeClass('row_selected');
		        $(this).addClass('row_selected');
		    }
		    
		    if ( $(this).find('td').hasClass('td_selected') ) {
		        $(this).find('td').removeClass('td_selected');
		    }
		    else {
		    	$('.sendmessagetable').dataTable().$('tr.td_selected').find('td').removeClass('td_selected');
		        $(this).find('td').addClass('td_selected');
		    }
		});
	});
});

var messageDelete = function(e){
	
	// 선택된 row를 읽어온다.
	selectedRow = $('.takemessagetable').dataTable().$('tr.row_selected');
	selectedId = new Array();
	
	// 선택된 북마크의 아이디를 배열에 삽입한다.
	for(var i=0; i<selectedRow.length; i++){
		selectedId.push($(selectedRow[i]).find('td:first').text());
	}
	
	$.ajax({
		url:'deleteMessage',
		dataType:'json',
		data:{
			messageId:selectedId.toString()
		}
	}).done(function(data){
		for(var i=0; i<selectedRow.length; i++){
			$('.takemessagetable').dataTable().fnDeleteRow(selectedRow[i]);
		}
	});
};

var sendmessageDelete = function(e){
	
	// 선택된 row를 읽어온다.
	selectedRow = $('.sendmessagetable').dataTable().$('tr.row_selected');
	selectedId = new Array();
	
	// 선택된 북마크의 아이디를 배열에 삽입한다.
	for(var i=0; i<selectedRow.length; i++){
		selectedId.push($(selectedRow[i]).find('td:first').text());
	}
	
	$.ajax({
		url:'deleteMessage',
		dataType:'json',
		data:{
			messageId:selectedId.toString()
		}
	}).done(function(data){
		for(var i=0; i<selectedRow.length; i++){
			$('.sendmessagetable').dataTable().fnDeleteRow(selectedRow[i]);
		}
	});
};


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
		//$(this).hide();

		//$('#messageSendingButton').show();
		
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


/*$('#messageTableAllSelect').click(function(e){
	if($(this).text() == '전체 선택'){
		$('input[name=messageSelector]').attr('checked', true);
		$(this).text('선택 해제');
	}else{
		$('input[name=messageSelector]').attr('checked', false);
		$(this).text('전체 선택');
	}
});*/

/*$(document).ready(function(){
	
		//var $checkTbl = $('#checkboxTbl');
	var $checkTbl = $('#checkboxTestTbl');
	//var $checkTbl = $('#checkboxTestTbl');
		//$('.table table-hovor');
	var num = 1;
  	$('#checkAll').click(function(){
  		console.log("클릭!!");
  		console.log("전:"+num);          		
  		
  		if(num==1){
  			
  			//$('input[name=select]').attr('checked','checked');
  			for(var i=0; i<messageForm.select.length; i++) {
  	  			messageForm.select[i].checked = true;
  	  		}
  			$($('input:checkbox'),$checkTbl).attr('checked', 'checked');
  			$('#checkAll').text('선택 해제');
  			num = num+1;
  		}
  		else{
  			
  			//$('input[name=select]').attr('checked',false);
  			for(var i=0; i<messageForm.select.length; i++) {
  	  			messageForm.select[i].checked = false;
  	  		}
  			$($('input:checkbox'),$checkTbl).attr('checked', false);
  			$('button#messageTableAllSelect').text('전체 선택');
  			num = num-1;
  		}
  		
  	});
  	
  	$("button#messageDelete").click(function(){
    	var checkCount = $($("input:checkbox"), $(".table table-hovor>tbody>tr")).filter(":checked").length;
    	if(checkCount==0){
    		alert("삭제할 리스트가 없습니다.");
    		
    	}
    	
    	// 1. 메시지 상세보기
    	// 2. 메시지 보내기
    	// 3. 메시지 선택, 전체 삭제
    	
    	// 4. 북마크 추천에서 친구 아이디 선택하기
    	// 5. 북마크 이미 추천 되어있는 것 구분(alert)
    	// 6. 미투데이 친구 불러오기
    	
    	else {
    		var select = confirm("삭제하시겠습니까?");
    		if(select){
    			var deleteId = "";
            	$($("input:checkbox:checked"), $(".table table-hovor>tbody>tr")).each(function(){
            	
            		$(this).closest('tr').fadeOut(400,function(){
            			$(this).closest('tr').remove();
            		
            		});
            		var messageId = $(this).closest('tr').attr("id")+",";
            		deleteId = deleteId + messageId;
            		
            	});
            	console.log("찍혀라2 : "+deleteId);
            	messageDelete(deleteId); 
            	
    		}
       	}
    });
         	
  	
    function messageDelete(messageId){
    	
    	$.ajax({
			url: 'deleteMessage',
			dataType:'json',
			data: {
					messageId: messageId,
		   		  }
		}).done(function(data){
			
		});
    };
});*/


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
	}); 
};

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
		$('.sendfriendtable').dataTable().fnDeleteRow(count);
		$('.friendtable').dataTable().fnDeleteRow(count);
	}); 
};

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
		$('.takefriendtable').dataTable().fnDeleteRow(count);
		$('.friendtable').dataTable().fnDeleteRow(count);
	}); 
};

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
		$('.takefriendtable').dataTable().fnDeleteRow(count);
		$('.friendtable').dataTable().fnDeleteRow(count);
	}); 
};

var addFriend = function(e){
	var friendId = $(e).data('id');
	var count = $(e).data('count');
	
	
	$.ajax({
		url: 'requestFriend',
		data: {
			friendId: friendId,
		}
	}).done(function(data){
		$('.friendtable').dataTable().fnDeleteRow(count);
	}); 
};


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



function searchMember(userId) {
	var keyword = $('input[name="keyword"]').val();
	$('.friendtable').dataTable().fnClearTable();
	memberId = null;
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