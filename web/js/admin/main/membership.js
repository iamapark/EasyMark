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
		$('input[name=memberSelector]').parent().parent().addClass('row_selected');
	}else{
		$('input[name=memberSelector]').attr('checked', false);
		$('input[name=memberSelector]').parent().parent().removeClass('row_selected');
		$(this).text('전체 선택');
	}
});

// 회원 테이블에서 선택 삭제를 클릭했을 때
$('#selectDelete').click(function(e){
	
	selectedRow = $('.datatable').dataTable().$('tr.row_selected');

	var data = $('input[name=memberSelector]').serialize();
	
	$.ajax({
		url:'deleteMembers',
		data:{
			members:data
		}
	}).done(function(data){
		for(var i=0; i<selectedRow.length; i++){
			// 북마크 리스트에서 해당 row를 삭제한다.
			$('.datatable').dataTable().fnDeleteRow(selectedRow[i]);
			// 바탕화면의 북마크 아이콘 리스트에서 해당 아이콘을 삭제한다.
		}
	});
});

// 회원 테이블에서 왼쪽 셀렉트 박스를 선택했을 때 클래스를 각 row에 추가한다.
// 삭제할 때 어떤 row가 선택되었는지 판별하기 위함임.
var memberSelect = function(selected){
	if ( $(selected).parent().parent().hasClass('row_selected') ) {
		$(selected).parent().parent().removeClass('row_selected');
    }
    else {
    	//$('.bookmarktable').dataTable().$('tr.row_selected').removeClass('row_selected');
    	$(selected).parent().parent().addClass('row_selected');
    }
};

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