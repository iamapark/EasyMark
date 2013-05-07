// 배경화면에서 MODAL을 불러오는 a태그를 클릭했을 때 호출
$('a[data-toggle="modal"]').click(function(){
	clearForm();
});

//여러가지 폼들을 초기화한다. 이전 데이터들을 지운다.
var clearForm = function(){
	$('#backgroundImgNoti').text('');
};

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
			alert('회원 정보를 수정하였습니다.');
		});
	}else{
		$("#updateMemberInfoForm").ajaxSubmit({
        	dataType:'html',
        	success:function(data,rst){
        		var imgUrl = JSON.parse(data).imgUrl;
        		var userId = JSON.parse(data).userId;
        		$('#settingImg').attr('src', 'users/img/'+userId+'/'+imgUrl);
        		$('#inputPersonalImg').attr('src', 'users/img/'+userId+'/'+imgUrl);
        		alert('회원 정보를 수정하였습니다.');
			}
    	});
	}
});


$('#designSelect').change(function(){
	var styleSelected = $('#designSelect option:selected').val();
	changeCSS(styleSelected);
	changeElement(styleSelected);
	changeJS(styleSelected);
	
	$.ajax({
		url:'changeDesign',
		dataType:'json',
		data:{
			design:styleSelected
		}
	}).done(function(data){
		$('#designSelectNoti').text('디자인이 변경되었습니다.');
	});
});

$('#designSelectButton').click(function(){
	var styleSelected = $('#designSelect option:selected').val();
});

function changeCSS(fname)
{
    var tg = document.getElementById('designSelectedCss');
    if(tg) { tg.href=('css/main/'+fname+'.css'); }
}

function changeJS(fname){
	var tg = document.getElementById('designSelectedJs');
	if(tg) {tg.src=('js/main/' + fname + '.js');}
}

function changeElement(fname){
	if(fname == 'MacOS'){ //MacOS 선택
		$("#designMainContainer").show();
		$("#designMain2Container").hide();
		
	}else if(fname == 'WindowsOS'){ //WindowsOS 선택
		$("#designMainContainer").hide();
		$("#designMain2Container").show();
	}
}

/**
<!-- setting 메뉴를 클릭했을 때 MODAL을 채울 정보를 가져온다. -->*/
$('a[href="#setting"]').click(function(){
	$.ajax({
		url: 'getMemberInfo',
		dataType:'json'
	}).done(function(data){
		$('#setting_userId').val(data.userId);
		$('#setting_name').val(data.name);
		$('#setting_email').val(data.email);
		if(data.imgUrl != ""){
			$('#inputPersonalImg').attr('src', 'users/img/' + data.userId + "/" + data.imgUrl);
		}
		$('#bgImg').attr('src', data.bgImgUrl);
	});
});

// 배경화면 파일을 선택했을 때 호출
$('#backgroundImgFile').change(function(){
	$("#bgImgForm").ajaxSubmit({
   		dataType:'html',
   		success:function(data,rst){
   			$('#backgroundImgNoti').text('배경 이미지를 교체했습니다.');
   			pData = JSON.parse(data);
   			$('#bgImg').attr('src', pData.bgImgUrl);
   			$('body').css('background-image','url('+ pData.bgImgUrl+ ')');
		}
    });
});