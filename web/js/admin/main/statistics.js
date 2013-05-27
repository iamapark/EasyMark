var fillStatistics = function(){
	data = [
		{ day: '2013-05-1', count: 15},
		    { day: '2013-05-2', count: 15},
		    { day: '2013-05-3', count: 15},
		    { day: '2013-05-4', count: 15},
		    { day: '2013-05-5', count: 2},
		    { day: '2013-05-6', count: 15},
		    { day: '2013-05-7', count: 3},
		    { day: '2013-05-8', count: 16},
		    { day: '2013-05-9', count: 15},
		    { day: '2013-05-10', count: 15},
		    { day: '2013-05-11', count: 15},
		    { day: '2013-05-12', count: 11},
		    { day: '2013-05-13', count: 15, month2:10 },
		    { day: '2013-05-14', count: 15, month2:10 },
		    { day: '2013-05-15', count: 12, month2:28 },
		    { day: '2013-05-16', count: 15, month2:40 },
		    { day: '2013-05-17', count: 15, month2:30 },
		    { day: '2013-05-18', count: 30, month2:20 },
		    { day: '2013-05-19', count: 15, month2:20 },
		    { day: '2013-05-20', count: 10, month2:10 },
		    { day: '2013-05-21', count: 15, month2:30 },
		    { day: '2013-05-22', count: 15, month2:10 },
		    { day: '2013-05-23', count: 4, month2:20 },
		    { day: '2013-05-24', count: 16, month2:10 },
		    { day: '2013-05-25', count: 15, month2:10 },
		    { day: '2013-05-26', count: 17, month2:10 },
		    { day: '2013-05-27', count: 3, month2:10 },
		    { day: '2013-05-28', count: 15, month2:14 },
		    { day: '2013-05-29', count: 2, month2:12 },
		    { day: '2013-05-30', count: 15, month2:10 },
		    { day: '2013-05-31', count: 15, month2:1 }
	];
	
	
};

$('#monthSelectorForChart').change(function(e){
	// 차트를 먼저 지운다.
	$('#myfirstchart').empty();
	
	var selectedMonth = $(this).find('option:selected').val();
	
	$.ajax({
		url:'getRegisterCount',
		data:{
			month:selectedMonth
		}
	}).done(function(data){
		console.log(data);
		kaka = data;
		
		if(data == '[]'){
			$('#myfirstchart').text('데이터가 없습니다. ㅠ.ㅠ');
		}else{
			new Morris.Line({
				  element: 'myfirstchart',
				  data: JSON.parse(data),
				  xkey: 'basis',
				  xlabels:'일',
				  ykeys: ['countResult'],
				  labels: ['countResult']
				});
		}
	});
});