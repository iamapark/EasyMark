var $id;
$(document).ready(function(){
	//삭제
	$('.bookmarkIcon').mouseover(bookMarkDelete).mouseout(bookMarkOut);

	$('#modifyBookMarkForm').ajaxForm();
	$('#bgImg').ajaxForm();
	/*$('#addMarkForm').ajaxForm();*/
	
});

var bookMarkArrange = function(e){
	
	var wgd=new Array();
	wgd=gridster.serialize_changed( );
	wgd2=gridster.serialize( );
	console.log('serialize_changed( )'+toJSONString(wgd));
	console.log('serialize( )'+toJSONString(wgd2));
	
	
	function toJSONString(json)
	{
		
	    var ary = new Array();
	    for(var key in json)
	    {
	        var val = json[key];
	        if(typeof(val)=='object') val = toJSONString(val);
	        else if(typeof(val)=='string') val = '"'+val+'"';
	        ary.push(key+':'+val);
	    }
	    return '{'+ary.join()+'}';
	   
		
		
	}
	
	$.ajax({
		url: 'arrange/arrange.jsp', //// javascript same origin policy를 해결하기 위한 프록시
		dataType:'json',
		data: {
				//posX:$x,
				//posY:$y,
				//posX:$(this).attr('data-row'),
				//posY:$(this).attr('data-col'),
				//bookMarkId:$(this).attr('data-id'),
				//wgd:wgd,
	   		  }
	});
	//console.log('wgd'+wgd);
	
	//console.log('data-row: ' + $(this).attr('data-row'));
//	console.log('data-col: ' + $(this).attr('data-col'));
	//console.log('data-id: ' + $(this).attr('data-id'));
	//console.log("/");
	//console.log('x :'+x);
	//console.log('y :'+y);
	//console.log('id :'+id);
	//console.log("/");
	
};
var bookMarkOver = function(e){
	
	var $li = $(this);
	//var $firstNode = $li.contents().first();
	//var $modify=$("<button id='x' type='button' class='close'  data-dismiss='modal' aria-hidden='true'><img id='wheel' src='img/wheel.png'></button>");
	//$modify.insertBefore($firstNode);
	//$('.close').click(bookMarkInform);
	//아이콘 아래 북마크 이름 출력
	//var $lastNode = $li.contents().last();
	//var $desc=$("<p id='desc'>${bookMark.bookMarkName}</p>");
	//$desc.insertAfter($lastNode);
	
	
	
};
var bookMarkOut = function(e){
	$('#x').remove();
	//$('#desc').remove();
	$(this).find('.bookmarkIconInfo').hide();
	
};
var bookMarkInfor = function(e){
	alert('좋아');

	
};
var bookMarkDelete = function(e){
	$id=$(this).attr('data-id');
	$(this).find('.bookmarkIconInfo').show();
};
//delete 버튼 눌렀을 때
$('#delete').bind('click',function(e){
	var $bookId=$id;
	console.log("delete id :"+$bookId);
	$.ajax({
		url: 'deleteMark',
		dataType:'json',
		data: {	
				bookmarkId:$bookId,
				
	   		  }
	}).done(function(data){
		console.log("resetMarkList");
		location.href ="resetMarkList";
	});
});

//modify 눌렀을 때 북마크 정보 수정
$('#modify').bind('click',function(e){
	var filename = $('#modifyBookMarkForm').find('#bookmarkIconImageFile').val();
	
	if(filename == ''){
		$.ajax({
			url:'modifyMark',
			dataType:'json',
			type:'POST',
			data:{
				modifyBookmarkName: $('#modifyBookmarkName').val(),
				modifyBookmarkUrl: escape($('#modifyBookmarkUrl').val()),
				modifyBookmarkDescription: $('#modifyBookmarkDescription').val(),
				bookmarkId: escape($('#modifyBookMarkId').val())
			}
		}).done(function(data){
			alert(data);
		});
	}else{
		$("#modifyBookMarkForm").ajaxSubmit({
        	dataType:'html',
        	success:function(data,rst){alert(data);}
    	});
	}
});

//add 눌렀을 때 북마크 정보 추가
$('#add').bind('click',function(e){
	e.preventDefault();
	console.log('add');
	var filename = $('#addMarkForm').find('#addBookMarkImage').val();
	
	if(filename == ''){
		$.ajax({
			url:'addMark',
			dataType:'json',
			type:'POST',
			data:{
				name:       $('#addBookMarkName').val(),
				url: escape($('#addBookMarkUrl').val()),
				description:$('#addBookMarkDescription').val(),
				category:   $('#addBookMarkCategory').val()
			}
		}).done(function(data){
			alert(data);
		});
	}else{
		$("#addMarkForm").ajaxSubmit({
        	dataType:'html',
        	success:function(data,rst){
        		console.log(data);
			}
    	});
	}
});

//회원정보 수정 버튼을 눌렀을 때 호출
$('#updateMemberButton').click(function(e){
	e.preventDefault();
	console.log('회원정보 수정');
	
	var filename = $('#personalImg').val();
	
	if(filename == ''){
		$.ajax({
			url:'updateMemberInfo',
			dataType:'json',
			type:'POST',
			data:{
				userId:       $('#setting_userId').val(),
				setting_name: $('#setting_name').val(),
				setting_email:$('#setting_email').val()
			}
		}).done(function(data){
			alert(data);
		});
	}else{
		$("#updateMemberInfoForm").ajaxSubmit({
        	dataType:'html',
        	success:function(data,rst){
        		var imgUrl = JSON.parse(data).imgUrl;
        		var userId = JSON.parse(data).userId;
        		$('#settingImg').attr('src', 'users/img/'+userId+'/'+imgUrl);
        		$('#inputPersonalImg').attr('src', 'users/img/'+userId+'/'+imgUrl);
			}
    	});
	}
});

//톱니바퀴 눌렀을 때 북마크 정보 가져온다
$('a[data-id]').bind('click',function(e){
	var $bookid=$(this).attr('data-id');
	$.ajax({
		url: 'getBookmarkInfo',
		dataType:'json',
		async: false,
		data: {
				bookMarkId:$bookid,
	   	}
	}).done(function(data){
		kaka = data;
		$('#modifyBookmarkName').val($(data).attr('bookMarkName'));
	    $('#modifyBookmarkUrl').val($(data).attr('bookMarkUrl'));
	    $('#modifyBookmarkDescription').val($(data).attr('bookMarkDescript'));
	    $('#bookmarkIconImage').attr('src',$(data).attr('imgUrl'));
	    $('#modifyBookMarkId').val($bookid);
	});
	e.preventDefault();
});

// 배경화면에서 MODAL을 불러오는 a태그를 클릭했을 때 호출
$('a[data-toggle="modal"]').click(function(){
	clearForm();
});

//여러가지 폼들을 초기화한다. 이전 데이터들을 지운다.
var clearForm = function(){
	$('#backgroundImgNoti').text('');
};