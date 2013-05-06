<%@page import="ch.qos.logback.core.joran.action.IncludeAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:if test="${sessionScope.MEMBERID == null}">
	<%
		response.sendRedirect("/EasyMark/");
	%>
</c:if>
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
<body style="background:url(${MEMBERINFO.bgImgUrl});background-position: 100% 100%;">
 	
 	<jsp:include page="template/MacOS.jsp">
 		<jsp:param value="${designType}" name="flag"/>
	</jsp:include>	
	
	<jsp:include page="template/WindowsOS.jsp">
		<jsp:param value="${designType}" name="flag"/>
	</jsp:include>
	
	
	<!-- BookMark List -->
	<div>
	<div id="gridster" class="gridster">
    	<ul>
			<c:forEach items="${sessionScope.bookMarkList}"	var="bookMark">
			<li style= "vertical-align: middle;border-radius:20px;" data-toggle="tooltip" title="${bookMark.bookMarkName}" data-row="${bookMark.posX}" data-col="${bookMark.posY}" data-id="${bookMark.bookMarkId}" data-sizex="1" data-sizey="1" class="bookmarkIcon">
            	<a id='info' data-id="${bookMark.bookMarkId}" role="button" data-toggle="modal"  class='close' href=#bookMarkInfo  data-dismiss='modal' aria-hidden='true' style="position:absolute; right:4px;top:4px;">
            		<img id='wheel' src='images/wheel.png'>
           		</a>
            	<img id="img" href="http://${bookMark.bookMarkUrl}" src="${bookMark.imgUrl}" style="width:100%; height:100%;border-radius:20px;">
            	<div class="bookmarkIconInfo" style="position:absolute; background-color: #838B8B;
width: 100%;
color: white;
text-align: center;
bottom: 0px;
-webkit-border-bottom-right-radius: 20px;
-webkit-border-bottom-left-radius:20px;
display:none;">${bookMark.bookMarkName}</div>
            </li> 
			</c:forEach>
    	</ul>
    </div>
    </div>
	<!-- BookMark List END -->
	
	

	
	<!-- MODAL -->
	<!-- setting 메뉴를 클릭했을 때 MODAL -->
		<jsp:include page="modal/setting.jsp" />
	<!-- 북마크 추가 메뉴룰 클릭했을 때 MODAL -->
		<jsp:include page="modal/bookmarkAdd.jsp" />
	<!-- 북마크 아이콘 우측 상단의 톱니바퀴를 클릭했을 때 MODAL -->
		<jsp:include page="modal/bookmarkInfo.jsp"></jsp:include>
 	<!-- MODAL END -->
	
	
	<script src="js/jquery.js"></script>
	<script src="js/jquery.form.js"></script>
	<script src="js/bootstrap/bootstrap.js"></script>
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
				$('#bgImg').attr('src', data.bgImgUrl);
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
	  $('img').dblclick(function(){
		 var openwindow = window.open('about:blank');
		 openwindow.location.href = $(this).attr('href');
	  });
	  
	  $('#backgroundImgFile').change(function(){
		 
		 kaka = $(this);
		 
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
	</script>
</body>
</html>