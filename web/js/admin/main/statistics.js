$('#monthSelectorForChart').change(function(e){
	// 차트를 먼저 지운다.
	$('#registerMemberMonthly').empty();
	
	var selectedMonth = $(this).find('option:selected').val();
	
	$.ajax({
		url:'getRegisterCount',
		data:{
			month:selectedMonth
		}
	}).done(function(data){
		
		if(data == '[]'){
			$('#registerMemberMonthly').text('데이터가 없습니다. ㅠ.ㅠ');
		}else{
			new Morris.Line({
				  element: 'registerMemberMonthly',
				  data: JSON.parse(data),
				  xkey: 'basis',
				  xlabels:'일',
				  ykeys: ['countResult'],
				  labels: ['countResult']
				});
		}
	});
});



// 시간대 별 접속자 통계를 보여주는 함수
var fillLoginCounterHourly = function(){
	$('#loginCounterHourly').empty();
	
	$.ajax({
		url:'getLoginCounterHourly'
	}).done(function(data){
		
		if(data == '[]'){
			$('#loginCounterHourly').text('데이터가 없습니다. ㅠ.ㅠ');
		}else{
			new Morris.Bar({
				  element: 'loginCounterHourly',
				  data: JSON.parse(data),
				  xkey: 'basis',
				  xlabels:'시',
				  ykeys: ['countResult'],
				  labels: ['countResult']
				});
		}
	});
};

var fillTotalStatistics = function(){
	$.ajax({
		url:'getTotalStatistics'
	}).done(function(data){
		var count = JSON.parse(data)[0];
		kaka = count;
		$('#avgBookMarkCount').text(count.avgBookMarkCount);
		$('#totalBookMarkCount').text(count.totalBookMarkCount);
		$('#avgConnection').text(getHMS(count.avgConnection));
		$('#totalConnection').text(getHMS(count.totalConnection));
		
		
	});
};

var getHMS = function(seconds){
	var sec = seconds;
	
	var hour = Math.floor(sec / 3600);
	sec = sec - (hour * 3600);
	
	var minute = Math.floor(seconds / 60);
	sec = sec - minute * 60;
	
	return hour + '시간 ' + minute + '분 ' + sec + '초';
};