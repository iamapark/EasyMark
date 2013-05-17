var $id;
var gridster;

$(document).ready(function() {
	init();
});

var init = function() {
	// 북마크 아이콘에 마우스를 올릴 때와 벗어날 때 이벤트 핸들러 등록
	$('.bookmarkIcon').mouseover(bookMarkDelete).mouseout(bookMarkOut);

	// 그리드스터 초기화
	gridsterInitial();

	// 북마크 아이콘을 더블 클릭했을 때 호출
	$('img').dblclick(function() {
		var openwindow = window.open('about:blank');
		openwindow.location.href = $(this).attr('href');
	});

	$('.bookmarkIcon').contextPopup({
		title : '북마크',
		items : [ {
			label : '북마크 변경',
			icon : '',
			action : function() {
				$('.contextMenuPlugin').remove();
				bookmarkUpdate();
			}
		}, {
			label : '북마크 삭제',
			icon : '',
			action : function() {
				$('.contextMenuPlugin').remove();
				$('#invisibleDiv').remove();
				bookmarkDelete();
			}
		}, {
			label : '북마크 추천',
			icon : '',
			action : function() {
				$('.contextMenuPlugin').remove();
				bookmarkRecommand();
			}
		} ]
	});

	$('#addBookMarkUrl').focusout(bookmarkUrlFocusOut);
};
var bookMarkInit = function(newEntry) {
	$(newEntry).mouseover(bookMarkDelete).mouseout(bookMarkOut);
};

var bookMarkArrange = function(e) {

	var wgd = gridster.serialize_changed();
	var location = toJSONString(wgd);

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

var bookMarkOver = function(e) {

	var $li = $(this);
	// var $firstNode = $li.contents().first();
	// var $modify=$("<button id='x' type='button' class='close'
	// data-dismiss='modal' aria-hidden='true'><img id='wheel'
	// src='img/wheel.png'></button>");
	// $modify.insertBefore($firstNode);
	// $('.close').click(bookMarkInform);
	// 아이콘 아래 북마크 이름 출력
	// var $lastNode = $li.contents().last();
	// var $desc=$("<p id='desc'>${bookMark.bookMarkName}</p>");
	// $desc.insertAfter($lastNode);

};
var bookMarkOut = function(e) {
	$('#x').remove();
	// $('#desc').remove();
	$(this).find('.bookmarkIconInfo').hide();

};
var bookMarkInfor = function(e) {
	alert('좋아');

};
var bookMarkDelete = function(e) {
	$id = $(this).attr('data-id');
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
				}).done(
						function(data) {
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

// add 눌렀을 때 북마크 정보 추가
$('#add')
		.bind(
				'click',
				function(e) {
					e.preventDefault();
					var filename = $('#addMarkForm').find('#addBookMarkImage')
							.val();
					var newLi;
					var id, x, y, url;

					dataInfo = {
						name : $('#addBookMarkName').val(),
						url : $('#addBookMarkUrl').val(),
						description : $('#addBookMarkDescription').val(),
						category : $('#addBookMarkCategory').val()
					};
					if (filename == '') {
						$
								.ajax({
									url : 'addMark',
									dataType : 'json',
									type : 'POST',
									data : dataInfo

								})
								.done(
										function(data) {
											kaka = data;
											id = data.id;
											x = data.x;
											y = data.y;
											url = data.url;
											$('#bookmarkAdd').modal('hide');
											alert('북마크가 추가되었습니다!!');
											newLi = '<li data-id="'
													+ id
													+ '" data-toggle="tooltip" title="'
													+ dataInfo.name
													+ '" data-row="'
													+ x
													+ '" data-col="'
													+ y
													+ '" data-sizex="1" data-sizey="1" class="bookmarkIcon gs_w">';
											newLi += '<img id="img" href="'
													+ url
													+ '" src="images/Bookmark.png" style="width:100%; height:100%;border-radius:20px;">';
											newLi += '<div class="bookmarkIconInfo">'
													+ dataInfo.name + '</div>';
											newLi += '</li>';
											gridster.add_widget(newLi, 1, 1);

											kakaka.unbind('contextmenu');
											init();
											kaka = this;
											keke = $(this);
											kiki = newLi;

										});
					} else {
						$("#addMarkForm")
								.ajaxSubmit(
										{
											dataType : 'html',
											success : function(data, rst) {
												id = JSON.parse(data).id;
												x = JSON.parse(data).x;
												y = JSON.parse(data).y;
												alert('북마크가 추가되었습니다.');
												$('#bookmarkAdd').modal('hide');
												newLi = '<li data-id="'
														+ id
														+ '" data-toggle="tooltip" title="'
														+ dataInfo.name
														+ '" data-row="'
														+ x
														+ '" data-col="'
														+ y
														+ '" data-sizex="1" data-sizey="1" class="bookmarkIcon gs_w">';
												newLi += '<img id="img" href="'
														+ dataInfo.url
														+ '" src="'
														+ JSON.parse(data).imgUrl
														+ '" style="width:100%; height:100%;border-radius:20px;">';
												newLi += '<div class="bookmarkIconInfo">'
														+ dataInfo.name
														+ '</div>';
												newLi += '</li>';
												gridster
														.add_widget(newLi, 1, 1);
												init();
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
var bookmarkDelete = function() {
	var $bookId = $(this).attr('$id');

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
		});
	}
};

// 북마크에서 마우스 오른쪽 버튼을 누르고 북마크 추천 탭을 클릭했을 때
var bookmarkRecommand = function() {
	keke = $(this);
	var $bookid = $(this).attr('$id');
	$.ajax({
		url : 'getBookmarkInfo',
		dataType : 'json',
		async : false,
		data : {
			bookMarkId : $bookid
		}
	}).done(function(data) {
	
		console.log("123돌아가나?");
		
		
		$('#recommend_url').val($(data).attr('bookMarkUrl'));
		$('#recommend_name').val($(data).attr('bookMarkName'));
		$('#recommend_descript').val($(data).attr('bookMarkDescript'));
		
	
		

	});
	$('#bookMarkRecommand').modal('show');
};




// 그리드 스터 아이콘 초기화
var gridsterInitial = function() {
	gridster = $(".gridster > ul").gridster({
		widget_margins : [ 10, 10 ],
		widget_base_dimensions : [ 140, 140 ],
		min_cols : 6,
		avoid_overlapped_widgets : true,
		serialize_params : function($w, wgd) {
			return {
				col : wgd.col,
				row : wgd.row,
				id : $w.data('id')
			};
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
// category 버튼 클릭
$('#category')
		.click(
				function() {

					var i;
					var category;

					$
							.ajax({
								url : 'viewCategory',
								dataType : 'json',
							})
							.done(
									function(data) {
										// 모든 아이콘 제거
										gridster
												.remove_widget($('li.bookmarkIcon'));
										console.log(data.length);
										// category 마다 아이콘 만들기
										for (i = 0; i < data.length; i++) {
											category = data[i];
											newLi = '<li data-id="#" data-toggle="tooltip" title="'
													+ category
													+ '" data-row="1" data-col="1" data-sizex="1" data-sizey="1" class="bookmarkIcon gs_w">';
											newLi += '<img id="img" href="#" src="#" style="width:100%; height:100%;border-radius:20px;">';
											newLi += '<div class="bookmarkIconInfo">'
													+ category + '</div>';
											newLi += '</li>';
											gridster.add_widget(newLi, 1, 1);

										}
										$('#setting').modal('hide');
										init();

									});
				});


$('#sendButton').click(function(e){
	
	console.log('회원정보 수정');
	
	
		$.ajax({
			url:'recommend',
			dataType:'json',
			type:'POST',
			data:{
				recommend_friendId:$('#recommend_friendId').val(),
				recommend_url: $('#recommend_url').val(),
				recommend_name:$('#recommend_name').val(),
				recommend_descript:$('#recommend_descript').val()
			}
		}).done(function(data){
			alert('즐겨찾기를 친구에게 추천했습니다.');
		});
	
});