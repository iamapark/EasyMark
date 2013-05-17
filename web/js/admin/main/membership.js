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
		/*$('#setting_userId').val(data.userId);
		$('#setting_name').val(data.name);
		$('#setting_email').val(data.email);
		if(data.imgUrl != ""){
			$('#inputPersonalImg').attr('src', data.imgUrl);
		}
		$('#bgImg').attr('src', data.bgImgUrl);*/
	});
};