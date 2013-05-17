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