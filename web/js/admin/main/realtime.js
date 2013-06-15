var loginMemberArray = new Array(); // 로그인 중인 사용자 정보를 담는 배열 변수
Array.prototype.removeElement = function(index){
	this.splice(index, 1);
	return this;
};

$(document).ready(function(){
	initRealtime();
	getLoginMemberList();
	getDashboardCount();
	realtimeChart();
});


var initRealtime = function(){
	registerServer(); // admin 계정을 socket io에 connect 하는 함수
};

var registerServer = function(){
	var adminId = 'a';
	
	socket = io.connect('http://localhost:15000/admin', {'sync disconnect on unload' : true});
	socket.emit('adminId', {id:adminId});
	
	
	/*사용자가 로그인 할 때마다
	 * 현재 접속중인 회원 수를 push 받아서 #loginMemberCount에 출력한다.*/
	socket.on('loginMemberCount', function(data){
		console.log('현재 접속중인 회원 수: ' + data.count);	
		$('#loginMemberCount').text(data.count);
	});
	
	/*사용자가 로그인 할 때마다 해당 사용자의 정보를 push 받아서  memberInfoList에 출력한다.*/
	socket.on('loginMemberInfo', function(data){
		var count = 0;
		var flag = true;
		
		console.log('loginMemberInfo');
		
		//data를 arrayList로 받아서 처리한다.
		if(loginMemberArray.indexOf(data) == -1){
			console.log('push: ' + count);
			count++;
			
			for(var i=0; i<loginMemberArray.length; i++){
				if(loginMemberArray[i].name == data.name)
					flag = false;
			}
			
			if(flag)
				loginMemberArray.push(data);
			
			fillLoginMemberInfoTable(); //테이블에 채우는 것은 tipJS를 사용할 것!
		}
	});
	
	/*사용자가 로그아웃 할 때마다
	 * 해당 사용자를 memberInfoList에서 삭제한다.*/
	socket.on('refreshLogoutMember', function(data){
		
		console.log('refreshLogoutMember');
		
		for(var i=0; i<loginMemberArray.length; i++){
			if(loginMemberArray[i].userId == data.userId)
				loginMemberArray.removeElement(i);
		}
		
		//loginMemberArray.pop(data.userId);
		fillLoginMemberInfoTable(); // 테이블을 다시 그리게 한다.
	});
	
	/**사용자가 가입할 때마다
	 * 회원 수 카운트를 dashboard에 push 한다.*/
	socket.on('pushRegisterMemberCount', function(data){
		$('#totalMemberCount').text(data.memberCount);
		$('#todayRegisterCount').text(data.todayRegisterCount);
	});
	
	
	/**서버에서 트래픽이 발생할 경우
	 * 트래픽 카운트를 1 증가시킨다.*/
	socket.on('trafficCount', function(data){
		console.log(data);
		kaka = data;
		traffic++;
	});
};

// 현재 로그인 중인 사용자 리스트를 받아온 후 memberInfoList에 출력한다.
var getLoginMemberList = function(){
	$.ajax({
		url:'getLoginMemberInfoList',
		dataType:'json'
	}).done(function(data){
		console.log('getLoginMemberInfoList');
		kaka = data;
		for(var i=0; i<data.length; i++){
			loginMemberArray.push(data[i]);
		}
		fillLoginMemberInfoTable();
	}).fail(function(e){
		console.log('getLoginMemberInfoList fail');
		kaka = e;
	});
};

//대시보드 상단 네 개 카운트 정보를 채우는 함수.
var getDashboardCount = function(){
	$.ajax({
		url:'getDashboardCount',
		dataType:'json'
	}).done(function(data){
		$('#totalMemberCount').text(data.memberCount);
		$('#todayRegisterCount').text(data.todayRegisterCount);
		$('#loginMemberCount').text(data.loginMemberCount);
		$('#messageCount').text(data.messageCount);
	}).fail(function(e){
		$('#totalMemberCount').text('데이터를 불러올 수 없습니다.');
		$('#todayRegisterCount').text('데이터를 불러올 수 없습니다.');
		$('#loginMemberCount').text('데이터를 불러올 수 없습니다.');
		$('#messageCount').text('데이터를 불러올 수 없습니다.');
	});
};


// 현재 접속 중인 회원 리스트를 채우는 함수
var fillLoginMemberInfoTable = function(){
	
	$('#loginMemberListUl li').remove();
	
	var li = '';
	
	for(var i=0; i<loginMemberArray.length && i<10; i++){
		li += '<li>';
			li += '<a href="#">';
				li += '<span>' + loginMemberArray[i].userId + '</span>';
				li += '<span class="green" style="width:15%;">' + loginMemberArray[i].name + '</span>';
				li += '<span style="width:25%;">' + loginMemberArray[i].email + '</span>';
			li += '</a>';
		li += '</li>';
	}
	
	$('#loginMemberListUl').append(li);
};


var dataCheck = function(){
	ar = new Array();
	
	ar.push((new Date()).getTime(), traffic);
	
	return ar; //[(new Date()).getTime(), Math.random() * 100];
};

var dataCheckA = function(){
	ar = new Array();
	
	ar.push(10, 10);
	
	return ar;
};

var traffic = 0;
var trafficInterval = function(){
	traffic = 0;
};

// 실시간 트래픽 추적 차트
var realtimeChart = function(){
	$(function () {
	    $(document).ready(function() {
	        Highcharts.setOptions({
	            global: {
	                useUTC: false
	            }
	        });
	    
	        var chart;
	        $('#realtimeChart').highcharts({
	            chart: {
	                type: 'spline',
	                animation: Highcharts.svg, // don't animate in old IE
	                marginRight: 10,
	                events: {
	                    load: function() {
	    
	                        // set up the updating of the chart each second
	                        var series = this.series[0];
	                        setInterval(function() {
	                            var x = (new Date()).getTime(), // current time
	                                y = Math.random() * 100;
	                            series.addPoint(dataCheck(), true, true);
	                            trafficInterval();
	                        }, 1000);
	                    }
	                }
	            },
	            title: {
	                text: '실시간 트래픽 현황'
	            },
	            xAxis: {
	                type: 'datetime',
	                tickPixelInterval: 1500
	            },
	            yAxis: {
	                title: {
	                    text: 'Value'
	                },
	                plotLines: [{
	                    value: 0,
	                    width: 1,
	                    color: '#808080'
	                }]
	            },
	            tooltip: {
	                formatter: function() {
	                        return '<b>'+ this.series.name +'</b><br/>'+
	                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
	                        Highcharts.numberFormat(this.y, 2);
	                }
	            },
	            legend: {
	                enabled: false
	            },
	            exporting: {
	                enabled: false
	            },
	            series: [{
	                name: 'Random data',
	                data: (function() {
	                    // generate an array of random data
	                    var data = [],
	                        time = (new Date()).getTime(),
	                        i;
	    
	                    for (i = -19; i <= 0; i++) {
	                        data.push({
	                            x: time + i * 1000,
	                            y: 0//Math.random()
	                        });
	                    }
	                    return data;
	                })()
	            }]
	        });
	    });
	    
	});
};