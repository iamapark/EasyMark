// 회원관리 메뉴에서 Action-View 를 클릭하면 해당 회원에 관한 정보를 받아온다.
var getMemberInfo = function(e){
	var userId = $(e).data('id'); //$(this).data('id');
	console.log(userId);
	$.ajax({
		url: 'getMemberInfo_admin',
		dataType:'json',
		data:{
			userId: userId
		}
	}).done(function(data){
		console.log(data);
		
		$('#info_userId').val(data.userId);
		$('#info_name').val(data.name);
		$('#info_email').val(data.email);
		if(data.imgUrl != "NULL"){
			$('#info_personalImg').attr('src', data.imgUrl);
		}
		if(data.bgImgUrl != "NULL"){
			$('#info_bgImg').attr('src', data.bgImgUrl);
		}
		//$('#bgImg').attr('src', data.bgImgUrl);
		
		$('#info_bookmarkCount').val(data.bookmarkCount);
		$('#info_loginCount').val(data.loginCount);
		$('#info_registerDate').val(data.registerDate);
	});
};

// 회원 테이블에서 전체 선택을 클릭했을 때
$('#memberTableAllSelect').click(function(e){
	if($(this).text() == '전체 선택'){
		$('input[name=memberSelector]').attr('checked', true);
		$(this).text('선택 해제');
	}else{
		$('input[name=memberSelector]').attr('checked', false);
		$(this).text('전체 선택');
	}
});

// 회원 테이블에서 선택 삭제를 클릭했을 때
$('#selectDelete').click(function(e){
	kaka = $('input[name=memberSelector]');
	var data = $('input[name=memberSelector]').serialize();
	
	$.ajax({
		url:'deleteMembers',
		data:{
			members:data
		}
	}).done(function(data){
		
		for(var i=0; i<$('input[name=memberSelector]').length; i++){
			if($('input[name=memberSelector]')[i].checked){
				$('.datatable').dataTable().fnDeleteRow(i);
			}
		}
	});
});

// 회원 테이블에서 각 목록의 삭제 버튼을 클릭했을 때
var memberDelete = function(e){
	var userId = $(e).data('id');
	var count = $(e).data('count');
	
	$.ajax({
		url:'deleteMember',
		data:{
			userId:userId
		}
	}).done(function(data){
		$('.datatable').dataTable().fnDeleteRow(count);
	});
};