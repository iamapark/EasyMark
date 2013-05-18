$('a[data-toggle="modal"]').click(function(){
	clearForm();
});

/**
<!-- friend 메뉴를 클릭했을 때 MODAL을 채울 정보를 가져온다. -->*/
$('a[href="#friendInfo"]').click(function(){
	$('#friendTable table').remove();
	$.ajax({
		url: 'friend',
		dataType:'json'
	}).done(function(data){
		tipJS.action('EasyMark.fill', data);
		console.log(data);
	});
});

$('#friendTab li:eq(0) a').click(function (e){	
	$.ajax({
		url: 'friend',
		dataType:'json'
	}).done(function(data){
		tipJS.action('EasyMark.fill', data);
		console.log(data);
	});
});

$('#friendTab li:eq(1) a').click(function (e){	
	$.ajax({
		url: 'sendFriendReq',
		dataType:'json'
	}).done(function(data){
		tipJS.action('EasyMark.sendfill', data);
		console.log(data);
	});
});

$('#friendTab li:eq(2) a').click(function (e){	
	$.ajax({
		url: 'takeFriendReq',
		dataType:'json'
	}).done(function(data){
		console.log(data);
	});
});

$('#friendTab li:eq(3) a').click(function (e){	
	$.ajax({
		url: 'recommendInWeb',
		dataType:'json'
	}).done(function(data){
		console.log(data);
	});
});

$('#webSiteTab li:eq(0) a').click(function (e){	
	$.ajax({
		url: 'recommendInWeb',
		dataType:'json'
	}).done(function(data){
		console.log(data);
	});
});

$('#webSiteTab li:eq(1) a').click(function (e){	
	$.ajax({
		url: 'recommendOutWeb',
		dataType:'json'
	}).done(function(data){
		console.log(data);
	});
});
