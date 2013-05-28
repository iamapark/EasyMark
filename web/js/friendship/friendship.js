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
			
			//action = "<button id='accept' class='btn btn-small btn btn-primary' onclick='acceptFriend('"+memberData[i].userId+"');'>수락</button>"+
			//		 "<button id='reject' class='btn btn-small btn btn-danger'  onclick='rejectFriend('"+memberData[i].userId+"');'>거절</button>";
			
			$('.takefriendtable').dataTable().fnAddData([memberData[i].userId, action]);
		}
	});
});

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
			 		 "</a>"+
			 		 "<a id='reject' onclick='recommendReject(this)' class='btn btn-small btn btn-danger' data-id='"+ memberData[i].bookMarkId + "' data-count='"+ i + "'>"+
			 		 "거절"+
			 		 "</a>";
			$('.inwebtable').dataTable().fnAddData([memberData[i].userId, memberData[i].bookMarkUrl, memberData[i].bookMarkName, action]);
		}
	});
});
//recommendReject
$('#webSiteTab li:eq(0) a').click(function (e){	
	
	$('.inwebtable').dataTable().fnClearTable();
	
	$.ajax({
		url: 'recommendInWeb',
	}).done(function(data){
		console.log(data);
		
		var memberData = JSON.parse(data);
		var action = null;
		
		for(var i=0; i<memberData.length; i++){
			/*bookMarkUrl: bookMarkUrl,
			bookMarkName: bookMarkName,
			bookMarkDescript: bookMarkDescript,
			friendId: friendId,*/
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
		var action = null;
		
		for(var i=0; i<memberData.length; i++){
			action = "<input name='select' type='checkbox' value='" + memberData[i].userId + "'></input>";

			//	action = "<input type='checkbox'>";
			$('.takemessagetable').dataTable().fnAddData([action, memberData[i].userId, memberData[i].contents, memberData[i].messageDate2]);
		}
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
		var action = null;
		var action2 = null;
		for(var i=0; i<memberData.length; i++){
			action = "<input name='select' type='checkbox' value='" + memberData[i].userId + "'></input>";
			$('.takemessagetable').dataTable().fnAddData([action, memberData[i].userId, memberData[i].contents, memberData[i].messageDate2]);
		}
	});
});

$('#messageTab li:eq(1) a').click(function (e){	
	$('.sendmessagetable').dataTable().fnClearTable();
	
	$.ajax({
		url: 'outBox',
	}).done(function(data){
		console.log(data);
		
		var memberData = JSON.parse(data);
		var action = null;
		
		for(var i=0; i<memberData.length; i++){
			action = "<input name='select' type='checkbox'>";
			$('.sendmessagetable').dataTable().fnAddData([action, memberData[i].friendId, memberData[i].contents, memberData[i].messageDate2]);
		}
	});
});


$('#messageSendButton').click(function(e){
	var messageFriendId = $('#messageFriendId').val();
	var messageContents = $('#messageContents').val();

	console.log('받는 사람: ' + friendId);
	console.log('내용: ' + message);
	
	$(this).hide();

	$('#messageSendingButton').show();
	
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
});


/*$('#messageTableAllSelect').click(function(e){
	if($(this).text() == '전체 선택'){
		$('input[name=messageSelector]').attr('checked', true);
		$(this).text('선택 해제');
	}else{
		$('input[name=messageSelector]').attr('checked', false);
		$(this).text('전체 선택');
	}
});*/

$(document).ready(function(){
	
		//var $checkTbl = $('#checkboxTbl');
	var $checkTbl = $('.table table-striped table-bordered bootstrap-datatable takemessagetable');
		//$('.table table-hovor');
	var num = 1;
  	$('button#messageTableAllSelect').click(function(){
  		console.log("클릭!!");
  		console.log("전:"+num);          		
  		if(num==1){
  			$($('input:checkbox'),$checkTbl).attr('checked','checked');
  			//$('input[name=select]').attr('checked','checked');
  			num = num+1;
  			$('button#messageTableAllSelect').text('선택 해제');
  		}
  		else{
  			$($('input:checkbox'),$checkTbl).attr('checked', false);
  			//$('input[name=select]').attr('checked',false);
  			num = num-1;
  			$('button#messageTableAllSelect').text('전체 선택');
  		}
  		console.log("후:"+num);     
  	});
  	
  	$("button#messageDelete").click(function(){
    	var checkCount = $($("input:checkbox"), $(".table table-hovor>tbody>tr")).filter(":checked").length;
    	if(checkCount==0){
    		alert("삭제할 리스트가 없습니다.");
    		
    	}
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
});


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
	}); 
};



function searchMember(userId) {
	var keyword = $('input[name="keyword"]').val();
	$('.friendtable').dataTable().fnClearTable();
	memberId = null;
	console.log(userId);
	
	$.ajax({
		url: 'memberList', //// javascript same origin policy를 해결하기 위한 프록시
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