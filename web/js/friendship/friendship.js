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
			/*action = "<a id='delete' onclick='deleteFriend("+memberId+")' class='btn btn-danger'>"
					 +"Delete"+
					 "</a>";*/
			action = "<button id='delete' class='btn btn-small btn btn-danger' onclick='deleteFriend("+memberId+");'>친구삭제</button>";
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
			action = "<button id='delete' class='btn btn-small btn btn-danger' onclick='deleteFriend('"+memberData[i].userId+"');'>친구삭제</button>";
			$('.friendtable').dataTable().fnAddData([memberData[i].userId, memberData[i].name, memberData[i].email, action]);
		}
	});
});

$('#friendTab li:eq(1) a').click(function (e){	
	$('.sendfriendtable').dataTable().fnClearTable();
	
	$.ajax({
		url: 'sendFriendReq',
	}).done(function(data){
	//	tipJS.action('EasyMark.sendfill', data);
		console.log(data);
		
		var memberData = JSON.parse(data);
		var action = null;
		
		for(var i=0; i<memberData.length; i++){
			action = "<button id='cancel' class='btn btn-small btn btn-warning' onclick='cancel('"+memberData[i].friendId+"');>요청취소</button>";
			
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
			action = "<button id='accept' class='btn btn-small btn btn-primary' onclick='acceptFriend('"+memberData[i].userId+"');'>수락</button>"+
					 "<button id='reject' class='btn btn-small btn btn-danger'  onclick='rejectFriend('"+memberData[i].userId+"');'>거절</button>";
			
			$('.takefriendtable').dataTable().fnAddData([memberData[i].userId, action]);
		}
	});
});

$('#friendTab li:eq(3) a').click(function (e){	
	/*$('.inwebtable').dataTable().fnClearTable();
	*/
	$.ajax({
		url: 'recommendInWeb',
	}).done(function(data){
		console.log(data);

		var memberData = JSON.parse(data);
		var action = null;
		for(var i=0; i<memberData.length; i++){
			action = "<button id='accept' class='btn btn-small btn btn-primary' onclick='acceptFriend('"+memberData[i].userId+"');'>수락</button>"+
					 "<button id='reject' class='btn btn-small btn btn-danger'  onclick='rejectFriend('"+memberData[i].userId+"');'>거절</button>";
			
			$('.inwebtable').dataTable().fnAddData([memberData[i].userId, memberData[i].bookMarkUrl, memberData[i].bookMarkName, action]);
		}
	});
});

$('#webSiteTab li:eq(0) a').click(function (e){	
	
	$('.inwebtable').dataTable().fnClearTable();
	
	$.ajax({
		url: 'recommendInWeb',
	}).done(function(data){
		console.log(data);
		
		var memberData = JSON.parse(data);
		var action = null;
		
		for(var i=0; i<memberData.length; i++){
			action = "<button id='accept' class='btn btn-small btn btn-primary' onclick='acceptFriend('"+memberData[i].userId+"');'>수락</button>"+
					 "<button id='reject' class='btn btn-small btn btn-danger'  onclick='rejectFriend('"+memberData[i].userId+"');'>거절</button>";
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
			action = "<button id='cancel' class='btn btn-small btn btn-warning' onclick='cancel('"+memberData[i].friendId+"');>요청취소</button>";
			$('.outwebtable').dataTable().fnAddData([memberData[i].friendId, memberData[i].bookMarkUrl, memberData[i].bookMarkName, action]);
		}
	});
});
