var $id;
var gridster;

$(document).ready(function(){
	init();
});

var init = function(){
	//삭제
	$('.bookmarkIcon').mouseover(bookMarkDelete).mouseout(bookMarkOut);
	
	//그리드스터 초기화
	gridsterInitial();
	
	// 북마크 아이콘을 더블 클릭했을 때 호출
	$('img').dblclick(function(){
		var openwindow = window.open('about:blank');
		openwindow.location.href = $(this).attr('href');
	});
	
	$('.bookmarkIcon').contextPopup({
	  	title:'북마크',
	  	items:[
			{label:'북마크 변경', icon:'', action:function(){bookmarkUpdate();}},
			{label:'북마크 삭제', icon:'', action:function(){bookmarkDelete();}}
	  	]
	});
};

var bookMarkArrange = function(e){
	
	wgd=new Array();
	wgd=gridster.serialize_changed( );
	wgd2=gridster.serialize( );
	console.log(toJSONString(wgd));
	console.log($(this).attr('data-id'));
	//console.log('serialize( )'+toJSONString(wgd2));
	
	
	function toJSONString(json){
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
		url: 'arrange', //// javascript same origin policy를 해결하기 위한 프록시
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

//modify 눌렀을 때 북마크 정보 수정
$('#modify').click(function(e){
	e.preventDefault();
	var filename = $('#bookmarkIconImageFile').val();
	var name = $('#modifyBookmarkName').val();
	var url = $('#modifyBookmarkUrl').val();
	var desc = $('#modifyBookmarkDescription').val();
	var bookmarkId = $('#modifyBookMarkId').val();
	
	/*변경한 url의 앞부분이 http://로 시작할 경우 그 부분을 삭제한다.*/
	if(url.match('^http://')){
		url = url.replace('http://', '');
	}
	
	if(filename == ''){
		$.ajax({
			url:'modifyMark',
			dataType:'json',
			type:'POST',
			data:{
				modifyBookmarkName: name,
				modifyBookmarkUrl: url,
				modifyBookmarkDescription: desc,
				bookmarkId: bookmarkId
			}
		}).done(function(data){
			$('li[data-id="'+bookmarkId+'"]').find('.bookmarkIconInfo').text($('#modifyBookmarkName').val());
			$('li[data-id="'+bookmarkId+'"]').find('img').attr('href', 'http://'+url);
			alert('북마크 정보를 변경하였습니다.');
			$('#bookMarkInfo').modal('hide');
		});
	}else{
		$("#modifyBookMarkForm").ajaxSubmit({
        	dataType:'html',
        	success:function(data,rst){
        		$('li[data-id="'+bookmarkId+'"]').find('.bookmarkIconInfo').text(name);
        		$('li[data-id="'+bookmarkId+'"]').find('img').attr('src', JSON.parse(data).imgUrl);
        		$('li[data-id="'+bookmarkId+'"]').find('img').attr('href', 'http://'+url);
        		alert('북마크 정보를 변경하였습니다.');
        		$('#bookMarkInfo').modal('hide');
    		}
    	});
	}
});

//add 눌렀을 때 북마크 정보 추가
$('#add').bind('click',function(e){
	e.preventDefault();
	var filename = $('#addMarkForm').find('#addBookMarkImage').val();
	var newLi;
	var id, x, y;
	
	dataInfo = {
		name:       $('#addBookMarkName').val(),
		url: escape($('#addBookMarkUrl').val()),
		description:$('#addBookMarkDescription').val(),
		category:   $('#addBookMarkCategory').val()
	};
	if(filename == ''){
		$.ajax({
			url:'addMark',
			dataType:'json',
			type:'POST',
			data: dataInfo
			
		}).done(function(data){
			kaka = data;
			id = data.id; x = data.x; y = data.y;
			$('#bookmarkAdd').modal('hide');
			alert('북마크가 추가되었습니다!!');
			newLi = '<li data-id="' + id + '" data-toggle="tooltip" title="'+dataInfo.name+'" data-row="'+x+'" data-col="'+y+'" data-sizex="1" data-sizey="1" class="bookmarkIcon gs_w">';
				newLi += '<img id="img" href="http://'+dataInfo.url+'" src="images/Bookmark.png" style="width:100%; height:100%;border-radius:20px;">';
				newLi += '<div class="bookmarkIconInfo">' + dataInfo.name +'</div>';
			newLi += '</li>';
			gridster.add_widget(newLi, 1, 1);
			init();
		});
	}else{
		$("#addMarkForm").ajaxSubmit({
        	dataType:'html',
        	success:function(data,rst){
        		id = JSON.parse(data).id; x = JSON.parse(data).x; y = JSON.parse(data).y;
        		alert('북마크가 추가되었습니다.');
        		$('#bookmarkAdd').modal('hide');
        		newLi = '<li data-id="' + id + '" data-toggle="tooltip" title="'+dataInfo.name+'" data-row="'+x+'" data-col="'+y+'" data-sizex="1" data-sizey="1" class="bookmarkIcon gs_w">';
					newLi += '<img id="img" href="http://'+dataInfo.url+'" src="'+JSON.parse(data).imgUrl+'" style="width:100%; height:100%;border-radius:20px;">';
					newLi += '<div class="bookmarkIconInfo">' + dataInfo.name +'</div>';
				newLi += '</li>';
				gridster.add_widget(newLi, 1, 1);
				init();
			}
    	});
	}
	
	
	
	
	/*<li data-id="${bookMark.bookMarkId}" data-toggle="tooltip" title="${bookMark.bookMarkName}" data-row="${bookMark.posX}" data-col="${bookMark.posY}" data-id="${bookMark.bookMarkId}" data-sizex="1" data-sizey="1" class="bookmarkIcon">
    	<img id="img" href="http://${bookMark.bookMarkUrl}" src="${bookMark.imgUrl}" style="width:100%; height:100%;border-radius:20px;">
    	<div class="bookmarkIconInfo">${bookMark.bookMarkName}</div>
    </li>*/
	
	
});

//북마크에서 마우스 오른쪽 버튼을 누르고 북마크 변경 탭을 클릭했을 때
var bookmarkUpdate = function(){
	  keke = $(this);
	  $bookid = $(this).attr('$id');
	  $.ajax({
			url: 'getBookmarkInfo',
			dataType:'json',
			async: false,
			data: {
					bookMarkId: $bookid
		   	}
		}).done(function(data){
			kaka = data;
			$('#modifyBookmarkName').val($(data).attr('bookMarkName'));
		    $('#modifyBookmarkUrl').val($(data).attr('bookMarkUrl'));
		    $('#modifyBookmarkDescription').val($(data).attr('bookMarkDescript'));
		    $('#bookmarkIconImage').attr('src',$(data).attr('imgUrl'));
		    $('#modifyBookMarkId').val($bookid);
		});
	  $('#bookMarkInfo').modal('show');  
};
  
//북마크에서 마우스 오른쪽 버튼을 누르고 북마크 삭제 탭을 클릭했을 때
var bookmarkDelete = function(){
  	var $bookId = $(this).attr('$id');
  	console.log($bookId);
	
	var flag = confirm('정말 삭제하시겠습니까??');
	if(flag == true){
		$.ajax({
			url: 'deleteMark',
			dataType:'json',
			data: {	
					bookmarkId:$bookId,
					
		   		  }
		}).done(function(data){
			gridster.remove_widget($('li[data-id="'+ $bookId +'"]'));
			init();
		});
	}
};

// 그리드 스터 아이콘 초기화
var gridsterInitial = function(){
  gridster = $(".gridster > ul").gridster({
        widget_margins: [10, 10],
        widget_base_dimensions: [140, 140],
        min_cols: 6,
        avoid_overlapped_widgets: true,
        serialize_params: function($w, wgd) {
       		//console.log('col: ' + wgd.col + ', row: ' + wgd.row); 
       		return { col: wgd.col, row: wgd.row }; 
   		}
    }).data('gridster');
};