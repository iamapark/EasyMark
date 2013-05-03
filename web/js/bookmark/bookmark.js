var $id;
$(document).ready(function(){
	//좌표 타이밍 맞추기 어렵다 !!!!!!!!! 
	//$('.bookmarkIcon').bind('mouseup',function(e){
		//var up=jQuery.Event('mouseup');
		//up.row=$(this).attr('data-row');
		//up.col=$(this).attr('data-col');
		//console.log('up.row: ' + up.row);
		//console.log('up.col: ' + up.col);
		
//	});
	
	
	
	//오른쪽 상단 아이콘
	//$('.bookmarkIcon').mouseover(bookMarkOver).mouseout(bookMarkOut);
	
	//삭제
	$('.bookmarkIcon').mouseover(bookMarkDelete);
	
	
	 
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
	
};
var bookMarkInfor = function(e){
	alert('좋아');

	
};
var bookMarkDelete = function(e){
	$id=$(this).attr('data-id');
	console.log("id : "+$id);
	
};
//delete 버튼 눌렀을 때
$('#delete').bind('click',function(e){
	var $bookId=$id;
	console.log("delete id :"+$bookId);
	$.ajax({
		url: 'deleteMark', //// javascript same origin policy를 해결하기 위한 프록시
		dataType:'json',
		data: {	
				bookMarkId:$bookId,
				
	   		  }
	}).done(function(data){
		console.log("resetMarkList");
		 location.href ="resetMarkList";
	});
});

//modify 눌렀을 때 북마크 정보 수정
$('#modify').bind('click',function(e){
	var $bookId=$id;
	console.log($bookId);
	$.ajax({
		url: 'modifyMark', //// javascript same origin policy를 해결하기 위한 프록시
		dataType:'json',
		data: {	
				bookMarkId:$bookId,
				bookMarkName:escape($('#name').val()),
				bookMarkUrl:escape($('#url').val()),
				bookMarkDesc:escape($('#description').val())
				
	   		  }
	
	}).done(function(data){
		 console.log("resetMark");
		 location.href ="resetMark";
	});
	console.log("bookId :"+$bookId);
	console.log("bookname :"+$('#name').val());
	console.log("bookurl :"+$('#url').val());
	console.log("bookdesc :"+$('#description').val());
});
//톱니바퀴 눌렀을 때 북마크 정보 가져온다
$('a[data-id]').bind('click',function(e){
	var $bookid=$(this).attr('data-id');
	$.ajax({
		url: 'getBookmarkInfo', //// javascript same origin policy를 해결하기 위한 프록시
		dataType:'json',
		async: false,
		data: {	
				bookMarkId:$bookid,
	   		  }
	}).done(function(data){
		$('#name').attr('value',$(data).attr('bookMarkName'));
	    $('#url').attr('value',$(data).attr('bookMarkUrl'));
	    $('#description').val($(data).attr('bookMarkDescript'));
	    
	    console.log("name"+$(data).attr('bookMarkName'));
		console.log("url"+$(data).attr('bookMarkUrl'));
		console.log("desc"+$(data).attr('bookMarkDescript'));
	});
	console.log($(this));
	e.preventDefault();
});