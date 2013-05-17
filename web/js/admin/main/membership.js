var getMemberInfo = function(){
	var userId = $(this).data('id');
	console.log(userId);
	$.ajax({
		url: 'getMemberInfo_admin',
		data:{
			userId: userId
		},
		dataType:'json'
	}).done(function(data){
		console.log(data);
		kaka = data;
		
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

// 회원 테이블에서 전체 삭제를 클릭했을 때
$('#selectDelete').click(function(e){
	kaka = $('input[name=memberSelector]');
	var data = $('input[name=memberSelector]').serialize();
	
	$.ajax({
		url:'deleteMembers',
		data:{
			members:data
		}
	}).done(function(data){
		console.log(data);
	});
	
	
});