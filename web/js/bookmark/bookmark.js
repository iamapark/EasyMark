var $bookmarkId;
var $categoryId;
var gridster;

$(document).ready(function() {
	init();
});

var init = function() {
	// 북마크 아이콘에 마우스를 올릴 때와 벗어날 때 이벤트 핸들러 등록
	$('.bookmarkIcon').mouseover(mouseOverBookmarkInfo).mouseout(bookMarkOut);
	
	// 카테고리 아이콘에 마우스를 올릴 때와 벗어날 때 이벤트 핸들러 등록
	$('.categoryIcon').mouseover(mouseOverBookmarkInfo).mouseout(bookMarkOut);

	// 그리드스터 초기화
	gridsterInitial();

	// 북마크 아이콘을 더블 클릭했을 때 호출
	$('img').dblclick(function() {
		var bookmarkId = $(this).parent().data('id');
		$.ajax({
			url:'increaseFrequency',
			dataType:'json',
			data:{
				bookmarkId: bookmarkId
			}
		});
		if($(this).attr('href')==""){ // url이 없다면 카테고리로 인식
			viewCategory(this);
		}else{ //url이 있다면 북마크로 인식
			var openwindow = window.open('about:blank');
			openwindow.location.href = $(this).attr('href');
		}	
			
	
	});

	$('.bookmarkIcon').contextPopup({
		title : '북마크',
		items : [ {
			label : '북마크 변경',
			icon : 'images/change.png',
			action : function() {
				$('.contextMenuPlugin').remove();
				bookmarkUpdate();
			}
		}, {
			label : '북마크 추천',
			icon : 'images/octo.png',
			action : function() {
				$('.contextMenuPlugin').remove();
				bookmarkRecommand();
			}
		}, {
			label : '북마크 삭제',
			icon : 'images/delete.png',
			action : function() {
				$('.contextMenuPlugin').remove();
				$('#invisibleDiv').remove();
				bookmarkDelete();
			}
		} ]
	});
	
	$('.categoryIcon').contextPopup({
		title : '카테고리',
		items : [ {
			label : '카테고리 변경',
			icon : 'images/change.png',
			action : function() {
				$('.contextMenuPlugin').remove();
				categoryUpdate();
			}
		}, {
			label : '카테고리 삭제',
			icon : 'images/delete.png',
			action : function() {
				$('.contextMenuPlugin').remove();
				$('#invisibleDiv').remove();
				categoryDelete();
			}
		} ]
	});

	$('#addBookMarkUrl').focusout(bookmarkUrlFocusOut);
};
var bookMarkInit = function(newEntry) {
	$(newEntry).mouseover(mouseOverBookmarkInfo).mouseout(bookMarkOut);
};

var bookMarkArrange = function(e) {

	var wgd = gridster.serialize_changed();
	var location = toJSONString(wgd);
	mama = this;
	
	console.log('location: ' + location);

	function toJSONString(json) {
		var ary = new Array();
		for ( var key in json) {
			var val = json[key];
			if (typeof (val) == 'object')
				val = toJSONString(val);
			else if (typeof (val) == 'string')
				val = '"' + val + '"';
			ary.push(key + ':' + val);
		}
		return '{' + ary.join() + '}';
	}

	$.ajax({
		url : 'arrange', // // javascript same origin policy를 해결하기 위한 프록시
		dataType : 'json',
		data : {
			location : location
		}
	}).done(function(data) {
		// console.log(data);
	});
};

var bookMarkOut = function(e) {
	$('#x').remove();
	// $('#desc').remove();
	$(this).find('.bookmarkIconInfo').hide();

};

var mouseOverBookmarkInfo = function(e) {
	$id = $(this).attr('data-id');
	$categoryId = $(this).data('categoryid');
	$(this).find('.bookmarkIconInfo').show();
};

// modify 눌렀을 때 북마크 정보 수정
$('#modify').click(
		function(e) {
			e.preventDefault();
			var filename = $('#bookmarkIconImageFile').val();
			var name = $('#modifyBookmarkName').val();
			var url = $('#modifyBookmarkUrl').val();
			var desc = $('#modifyBookmarkDescription').val();
			var bookmarkId = $('#modifyBookMarkId').val();

			/*
			 * 변경한 url의 앞부분이 http://로 시작할 경우 그 부분을 삭제한다.
			 * if(url.match('^http://')){ url = url.replace('http://', ''); }
			 */

			if (filename == '') {
				$.ajax({
					url : 'modifyMark',
					dataType : 'json',
					type : 'POST',
					data : {
						modifyBookmarkName : name,
						modifyBookmarkUrl : url,
						modifyBookmarkDescription : desc,
						bookmarkId : bookmarkId
					}
				}).done(function(data) {
							$('li[data-id="' + bookmarkId + '"]').find(
									'.bookmarkIconInfo').text(
									$('#modifyBookmarkName').val());
							$('li[data-id="' + bookmarkId + '"]').find('img')
									.attr('href', url);
							alert('북마크 정보를 변경하였습니다.');
							$('#bookMarkInfo').modal('hide');
						});
			} else {
				$("#modifyBookMarkForm").ajaxSubmit(
					{
						dataType : 'html',
						success : function(data, rst) {
							$('li[data-id="' + bookmarkId + '"]').find(
									'.bookmarkIconInfo').text(name);
							$('li[data-id="' + bookmarkId + '"]').find(
									'img').attr('src',
									JSON.parse(data).imgUrl);
							$('li[data-id="' + bookmarkId + '"]').find(
									'img').attr('href', url);
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
	var id, x, y, imgUrl;
	
	$('#hiddenBookmarkAddCategory').val(selectedCategoryId);
	
	dataInfo = {
		name:       $('#addBookMarkName').val(),
		url: 		$('#addBookMarkUrl').val(),
		description:$('#addBookMarkDescription').val(),
		category:   selectedCategoryId
	};
	
	var addWidget = function(data){
		kaka = data;
		if(!data.id){
			data = JSON.parse(data);
		}
		console.log('추가');
		id = data.id; x = data.x; y = data.y; imgUrl = data.imgUrl;
		alert('북마크가 추가되었습니다.');
		$('#bookmarkAdd').modal('hide');
		newLi = '<li data-id="' + id + '" data-toggle="tooltip" title="'+dataInfo.name+'" data-row="'+x+'" data-col="'+y+'" data-sizex="1" data-sizey="1" class="bookmarkIcon gs_w">';
			newLi += '<img id="img" href="'+data.url+'" src="'+imgUrl+'" style="width:100%; height:100%;border-radius:20px;">';
			newLi += '<div class="bookmarkIconInfo">' + dataInfo.name +'</div>';
		newLi += '</li>';
		gridster.add_widget(newLi, 1, 1);
		
		init();
		
		selectedCategoryId = 0;
	};
	
	if(filename == ''){
		$.ajax({
			url:'addMark',
			dataType:'json',
			type:'POST',
			data: dataInfo
			
		}).done(function(data){
			if(currentPageCategoryId == selectedCategoryId){
				addWidget(data);
			}else{
				alert('북마크가 추가되었습니다.');
				$('#bookmarkAdd').modal('hide');
			}
		});
	}else{
		$("#addMarkForm").ajaxSubmit({
        	dataType:'html',
        	success:function(data,rst){
        		if(currentPageCategoryId == selectedCategoryId){
    				addWidget(data);
    			}else{
    				alert('북마크가 추가되었습니다.');
    				$('#bookmarkAdd').modal('hide');
    			}
			}
    	});
	}
});


// 북마크에서 마우스 오른쪽 버튼을 누르고 북마크 변경 탭을 클릭했을 때
var bookmarkUpdate = function() {
	keke = $(this);
	$bookid = $(this).attr('$id');
	$.ajax({
		url : 'getBookmarkInfo',
		dataType : 'json',
		async : false,
		data : {
			bookMarkId : $bookid
		}
	}).done(function(data) {
		kaka = data;
		$('#modifyBookmarkName').val($(data).attr('bookMarkName'));
		$('#modifyBookmarkUrl').val($(data).attr('bookMarkUrl'));
		$('#modifyBookmarkDescription').val($(data).attr('bookMarkDescript'));
		$('#bookmarkIconImage').attr('src', $(data).attr('imgUrl'));
		$('#modifyBookMarkId').val($bookid);
	});
	$('#bookMarkInfo').modal('show');
};

// 북마크에서 마우스 오른쪽 버튼을 누르고 북마크 삭제 탭을 클릭했을 때
var bookmarkDelete = function(id) {
	var $bookId;
	
	if(id == null)
		$bookId = $(this).attr('$id');
	else
		$bookId = id;
	
	var flag = confirm('정말 삭제하시겠습니까??');
	if (flag == true) {
		$.ajax({
			url : 'deleteMark',
			dataType : 'json',
			data : {
				bookmarkId : $bookId,
			}
		}).done(function(data) {
			gridster.remove_widget($('li[data-id="' + $bookId + '"]'));
			// init();
			return true;
		});
	}
};

// 북마크에서 마우스 오른쪽 버튼을 누르고 북마크 추천 탭을 클릭했을 때
var bookmarkRecommand = function() {
	var $bookid = $(this).attr('$id');
	var optionTag = '';
	$('#recommend_friendId').empty();
	
	$.ajax({
		url : 'getRecommendInfo',
		dataType : 'json',
		data : {
			bookMarkId : $bookid
		}
	}).done(function(data) {
		
		console.log(data);
		keke = data;
		
		for(var i=0; i<data[1].length; i++){
			optionTag += "<option value='" + data[1][i].userId +"'>";
				optionTag += data[1][i].userId;
			optionTag += "</option>";
		}
		
		$('#recommend_url').val($(data[0]).attr('bookMarkUrl'));
		$('#recommend_name').val($(data[0]).attr('bookMarkName'));
		$('#recommend_descript').val($(data[0]).attr('bookMarkDescript'));
		
		$('#recommend_friendId').append(optionTag);
		
	}).fail(function(e){
		console.log('fail');
	});
	
	$('#bookMarkRecommand').modal('show');
};

// 그리드 스터 아이콘 초기화
var gridsterInitial = function() {
	
	gridster = $(".gridster > ul").gridster({
		widget_margins : [ 10, 10 ],
		widget_base_dimensions : [ 140, 140 ],
		min_cols : 8,
		avoid_overlapped_widgets : true,
		serialize_params : function($w, wgd) {
			keke = $w;
			kaka = wgd;
			if(wgd){
				return {
					col : wgd.col, //$w.data('col'),
					row : wgd.row, //$w.data('row'),
					id : wgd.id
				};
			}else{
				return {
					col : $w.data('col'),
					row : $w.data('row'),
					id : $w.data('id')
				};
			}
			
		}
	}).data('gridster');
};

// 사용자가 북마크 추가시 URL을 입력하고 다음 input으로 커서를 옮겼을 때
// 입력한 URL 페이지의 title 값을 읽어와서 name input에 삽입한다.
var bookmarkUrlFocusOut = function() {
	var url = this.value;

	$('#addBookMarkName').addClass('addBookmarkNameLoading');
	$('#addBookMarkName').val('       URL 분석 중');

	if (url) {
		$.ajax({
			url : 'getAddBookMarkInfo',
			dataType : 'json',
			data : {
				url : url
			}
		}).done(function(data) {
			// $('#addBookMarkName').css('background-image', '');
			if (data.flag == 'true') {
				$('#addBookMarkName').removeClass('addBookmarkNameLoading');
				$('#addBookMarkName').val(data.title);
			} else {
				$('#addBookMarkName').removeClass('addBookmarkNameLoading');
				$('#addBookMarkName').val('');
			}
		});
	}
};


//카테고리 추가 할때 실시간 카테고리 중복체크 중복이면 버튼없어진다
$('#categoryName').keyup(function(){
	console.log("isExistCategoryName");
	var categoryName=$('#categoryName').val();
		
	$.ajax({
		url:'isExistCategory',
		dataType:'json',
		data:{
			categoryName: categoryName
		}
	}).done(function(data){
		if(data.flag==true){//카터고리 겹치면 비활성화
			alert('카테고리 이름 겹쳐용');
			$('#category').attr('disabled','disabled');
		}else{//카터고리 추가 가능하면
			$('#category').removeAttr('disabled');
		}
		
	});
});

//bookmark add 버튼 눌렀을 시 북마크 옵션 업데이트
$('#mark_button').click(function(){
	
	$.ajax({
		url:'getCategoryTree',
		dataType:'json'
	}).done(function(data){
		kaka = data;
		var ol = makeChildren(data, 'addBookmarkCategoryClick');
		
		$('#addCategoryOl').empty();
		$('#addCategoryOl').append($(ol));
	});
});

// bookmark add 모달에서 카테고리 목록을 클릭할 때 목록 업데이트
//카테고리 폴더 목록에서 디렉토리 하나를 클릭했을 때
var addBookmarkCategoryClick = function(li){
	
	$('#addCategoryOl .tree li label').removeClass('selectedLabel2');
	$(li).addClass('selectedLabel2');

	var categoryName = $(li).text();
	selectedCategoryId = $(li).attr('for').split('category-')[1];
};
