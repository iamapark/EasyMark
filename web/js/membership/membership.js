$(document).ready(function(){
	
});

$('.bookmarktable').dataTable({
	"sPaginationType": "full_numbers",
	"bJQueryUI": true
});
$('.bookmarktable tbody tr').on('click', function () {
    alert('keke');
});

// 배경화면에서 MODAL을 불러오는 a태그를 클릭했을 때 호출
$('a[data-toggle="modal"]').click(function() {
	clearForm();
});

// 여러가지 폼들을 초기화한다. 이전 데이터들을 지운다.
var clearForm = function() {
	$('#backgroundImgNoti').text('');
	$('#addBookMarkUrl').val('');
	$('#addBookMarkName').val('');
	$('#addBookMarkDescription').val('');
	$('#addBookMarkImage').val('');
	$('#bookmarkIconImageFile').val('');
};

// 회원정보 수정 버튼을 눌렀을 때 호출
$('#updateMemberButton').click(function(e) {
	e.preventDefault();
	console.log('회원정보 수정');

	var filename = $('#personalImg').val();

	if (filename == '') {
		$.ajax({
			url : 'updateMemberInfo',
			dataType : 'json',
			type : 'POST',
			data : {
				userId : $('#setting_userId').val(),
				setting_name : $('#setting_name').val(),
				setting_email : $('#setting_email').val()
			}
		}).done(function(data) {
			alert('회원 정보를 수정하였습니다.');
		});
	} else {
		$("#updateMemberInfoForm").ajaxSubmit({
			dataType : 'html',
			success : function(data, rst) {
				var imgUrl = JSON.parse(data).imgUrl;
				$('#settingImg').attr('src', imgUrl);
				$('#inputPersonalImg').attr('src', imgUrl);
				alert('회원 정보를 수정하였습니다.');
			}
		});
	}
});

$('#designSelect').change(function() {
	var styleSelected = $('#designSelect option:selected').val();
	changeCSS(styleSelected);
	changeElement(styleSelected);
	changeJS(styleSelected);

	$.ajax({
		url : 'changeDesign',
		dataType : 'json',
		data : {
			design : styleSelected
		}
	}).done(function(data) {
		$('#designSelectNoti').text('디자인이 변경되었습니다.');
	});
});

$('#designSelectButton').click(function() {
	var styleSelected = $('#designSelect option:selected').val();
});

function changeCSS(fname) {
	var tg = document.getElementById('designSelectedCss');
	if (tg) {
		tg.href = ('css/main/' + fname + '.css');
	}
}

function changeJS(fname) {
	var tg = document.getElementById('designSelectedJs');
	if (tg) {
		tg.src = ('js/main/' + fname + '.js');
	}
}

function changeElement(fname) {
	if (fname == 'MacOS') { // MacOS 선택
		$("#designMainContainer").show();
		$("#designMain2Container").hide();

	} else if (fname == 'WindowsOS') { // WindowsOS 선택
		$("#designMainContainer").hide();
		$("#designMain2Container").show();
	}
}

/**
 * <!-- setting 메뉴를 클릭했을 때 MODAL을 채울 정보를 가져온다. -->
 */
$('a[href="#setting"]').click(function() {
	$.ajax({
		url : 'getMemberInfo',
		dataType : 'json'
	}).done(function(data) {
		$('#setting_userId').val(data.userId);
		$('#setting_name').val(data.name);
		$('#setting_email').val(data.email);
		if (data.imgUrl != "") {
			$('#inputPersonalImg').attr('src', data.imgUrl);
		}
		$('#bgImg').attr('src', data.bgImgUrl);
	});
});


// setting 메뉴에서 북마크 탭을 클릭했을 때
// 회원의 북마크 리스트를 불러와서 화면에 테이블 형식으로 뿌려준다.
$('a[href="#setting_bookmarkInfo"]').click(function() {
	// 북마크 테이블의 모든 데이터를 지운다.
	$('.bookmarktable').dataTable().fnClearTable();
	
	$.ajax({
		url : 'viewBookMarkList',
		dataType : 'json',
		
	}).done(function(data) {
		for(var i=0; i<data.length; i++){
			bm = data[i];
			var select = "<input type='checkbox' onchange='bookmarkSelect(this)' name='bookmarkSelector' value='" + bm.bookMarkId + "'></input>";
			$('.bookmarktable').dataTable().fnAddData([select, bm.bookMarkId, bm.bookMarkName, bm.bookMarkUrl, bm.bookMarkDescript, bm.frequency]);
		}
		
		/*$("#setting_bookmarkList tbody tr").on('click', function( e ) {
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
		    	$('.bookmarktable').dataTable().$('tr.td_selected').find('td').removeClass('td_selected');
		        $(this).find('td').addClass('td_selected');
		    }
		});*/
	});
});

var bookmarkSelect = function(selected){
	console.log('selec');
	if ( $(selected).parent().parent().hasClass('row_selected') ) {
		$(selected).parent().parent().removeClass('row_selected');
    }
    else {
    	$(selected).parent().parent().addClass('row_selected');
    }
};

//북마크 리스트에서 삭제 버튼을 클릭했을 때
var bookmarkListDelete = function(e){
	
	// 선택된 row를 읽어온다.
	selectedRow = $('.bookmarktable').dataTable().$('tr.row_selected');
	
	var data = $('input[name=bookmarkSelector]').serialize();

	$.ajax({
		url:'deleteBookMarks',
		dataType:'json',
		data:{
			bookmarks:data
		}
	}).done(function(data){
		for(var i=0; i<selectedRow.length; i++){
			// 북마크 리스트에서 해당 row를 삭제한다.
			$('.bookmarktable').dataTable().fnDeleteRow(selectedRow[i]);
			
			// 바탕화면의 북마크 아이콘 리스트에서 해당 아이콘을 삭제한다.
			gridster.remove_widget($('li[data-id="' + $(selectedRow[i]).find('input').attr('value') + '"]'));
		}
	});
};

// 배경화면 파일을 선택했을 때 호출
$('#backgroundImgFile').change(function() {
	$("#bgImgForm").ajaxSubmit({
		dataType : 'html',
		success : function(data, rst) {
			$('#backgroundImgNoti').text('배경 이미지를 교체했습니다.');
			pData = JSON.parse(data);
			$('#bgImg').attr('src', pData.bgImgUrl);
			$('body').css('background-image', 'url(' + pData.bgImgUrl + ')');
		}
	});
});

// 회원 탈퇴 버튼을 클릭했을 때
$('#leaveMembership').click(function(e){
	e.preventDefault();
	var flag = confirm('정말 탈퇴하시겠습니까?');
	
	if(flag){
		location.href = 'leaveMembership';
	}
});

// 로그 아웃 버튼을 클릭했을 때
$('#logoutButton').click(function(e){
	e.preventDefault();
	location.href = 'logout';
});

//북마크 테이블에서 전체 선택을 클릭했을 때
var bookmarkListAllSelection = function(e){
	if($(e).text().trim() == '전체 선택'){
		$('input[name=bookmarkSelector]').attr('checked', true);
		$('input[name=bookmarkSelector]').parent().parent().addClass('row_selected');
		$(e).text('선택 해제');
	}else{
		$('input[name=bookmarkSelector]').attr('checked', false);
		$('input[name=bookmarkSelector]').parent().parent().removeClass('row_selected');
		$(e).text('전체 선택');
	}
};
