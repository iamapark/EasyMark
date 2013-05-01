<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${MEMBERID}/${designType} - EasyMark</title>
	<link rel="stylesheet" href="css/bootstrap/bootstrap.css" type="text/css" >
	
	<!-- design:main -->
	<link href="css/main/MacOS.css" rel="stylesheet" type="text/css" id="designSelectedCss">
</head>
<body>
	<div>
		<a href="#designModal" role="button" class="btn" data-toggle="modal">디자인 변경</a>
 	</div>
 	
 	<!-- design:main2(MacOS) -->
 	<jsp:include page="template/MacOS.jsp"></jsp:include>
 	
 	<!-- design:main2(WindowsOS) -->
 	<jsp:include page="template/WindowsOS.jsp"></jsp:include>
 	
 	
 	
 	
 	<!-- 디자인 유형을 선택하는 modal -->
 	<div>
		<!-- Modal -->
		<div id="designModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    <h3 id="myModalLabel">디자인 선택</h3>
		  </div>
		  <div class="modal-body">
		  	<select id="designSelect" name="style">
				<option value="MacOS">디자인 1: MacOS</option>
				<option value="WindowsOS">디자인 2: WindowsOS</option>
			</select>
		  </div>
		  <div class="modal-footer">
		    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		    <button id="designSelectButton" class="btn btn-primary">Save changes</button>
		  </div>
		</div>
	</div>
	
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap/bootstrap.js"></script>
	<!-- design:main -->
	<script type="text/javascript" src="js/main/MacOS.js" id="designSelectedJs"></script>
	<script>
		$('#designSelect').change(function(){
			var styleSelected = $('#designSelect option:selected').val();
			changeCSS(styleSelected);
			changeElement(styleSelected);
			changeJS(styleSelected);
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
	</script>
</body>
</html>