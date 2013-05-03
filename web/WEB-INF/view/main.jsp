<%@page import="ch.qos.logback.core.joran.action.IncludeAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${MEMBERID}/${designType} - EasyMark</title>
	<link rel="stylesheet" type="text/css" href="css/bookmark/gridster/jquery.gridster.css">
	<link rel="stylesheet" type="text/css" href="css/bookmark/bookmark.css">
	<link rel="stylesheet" href="css/bootstrap/bootstrap.css" type="text/css" >
	
	<!-- design:main -->
	<%
		String designType = (String)session.getAttribute("designType");
		if(designType.equals("MacOS")){
			%>
			<link href="css/main/MacOS.css" rel="stylesheet" type="text/css" id="designSelectedCss">
			<%
		}else{
			%>
			<link href="css/main/WindowsOS.css" rel="stylesheet" type="text/css" id="designSelectedCss">
			<%
		}
	%>
	
</head>
<body>
 	
 	<jsp:include page="template/MacOS.jsp">
 		<jsp:param value="${designType}" name="flag"/>
	</jsp:include>	
	
	<jsp:include page="template/WindowsOS.jsp">
		<jsp:param value="${designType}" name="flag"/>
	</jsp:include>
	
	
	<!-- BookMark List -->
	<div id="gridster" class="gridster">
    	<ul>
			<li style= "vertical-align: middle; "data-toggle="tooltip" title="TEST" data-row="1" data-col="${bookMark.posY}" data-id="1" data-sizex="1" data-sizey="1"  class="bookmarkIcon">
	           	<a id='info' data-id="${bookMark.bookMarkId}" role="button" data-toggle="modal"  class='close' href=#bookMarkInfo  data-dismiss='modal' aria-hidden='true'><img id='wheel' src='images/wheel.png'></a>
	           	<img id="img" href="http://www.naver.com" src="http://static.naver.net/www/u/2010/0611/nmms_215646753.gif" style="width:80%; height:100%;">
	           	<!--   <p>${bookMark.bookMarkName}</p>-->
        	</li> 
    	</ul>
    </div>
	<!-- BookMark List END -->
	
	

	
	<!-- MODAL -->
	<!-- setting 메뉴를 클릭했을 때 MODAL -->
		<jsp:include page="modal/setting.jsp" />
 	<!-- MODAL END -->
	
	
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap/bootstrap.js"></script>
	<script src="http://jschr.github.io/bootstrap-modal/js/bootstrap-modalmanager.js"></script>
	<script src="js/bookmark/jquery.gridster.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/bookmark/bookmark.js"></script>
	<!-- design:main -->
	<script type="text/javascript" src="js/main/MacOS.js" id="designSelectedJs"></script>
	<script>
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
				kaka = data;
			});
		});
		
	</script>
	<script type="text/javascript">
	  var gridster;
	
	  $(function(){
	
	    gridster = $(".gridster > ul").gridster({
	        widget_margins: [10, 10],
	        widget_base_dimensions: [140, 140],
	        min_cols: 6,
	        avoid_overlapped_widgets: true,
	       serialize_params: function($w, wgd) { return { col: wgd.col, row: wgd.row }; },
	    }).data('gridster');
	
	  });
	</script>
	<script type="text/javascript">
	  var _gaq = _gaq || [];
	  _gaq.push(['_setAccount', 'UA-33489625-1']);
	  _gaq.push(['_trackPageview']);
	
	  (function() {
	    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
	    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	  })();
	  
	  var img;
	  $('img').dblclick(function(){
		 var openwindow = window.open('about:blank');
		 openwindow.location.href = $(this).attr('href');
	  });
	 
	  
	</script>
</body>
</html>