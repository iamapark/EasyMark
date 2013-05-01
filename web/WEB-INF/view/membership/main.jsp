<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>디자인 변경 테스트 페이지</title>
	<link rel="stylesheet" href="css/bootstrap/bootstrap.css" type="text/css" >
	
	<!-- design:main -->
	<link href="css/main/main.css" rel="stylesheet" type="text/css" id="designSelectedCss">
</head>
<body>
	<div>
		<a href="#designModal" role="button" class="btn" data-toggle="modal">디자인 변경</a>
 	</div>
 	
 	<!-- design:main(MacOS) -->
 	<div id="designMainContainer">
 	<div id="dock-container">
		<div id="dock">
		<ul>
			<li><span>Home</span><a href="http://android.com"><img src="images/home.png"/></a></li>
			<li><span>Link</span><a href="http://palm.com"><img src="images/link.png"/></a></li>
			<li><span>Music</span><a href="http://apple.com/iphone"><img src="images/music.png"/></a></li>
			<li><span>Portfolio</span><a href="http://microsoft.com/windowsmobile"><img src="images/portfolio.png"/></a></li>
			<li><span>Rss</span><a href="http://blackberry.com"><img src="images/rss.png"/></a></li>
			<li><span>Video</span><a href="http://sonyericsson.com"><img src="images/video.png"/></a></li>
		</ul>
		<div class="base"></div>
		</div>
	</div>
	</div>
 	
 	
 	<div id="designMain2Container" style="display:none;">
 	<!-- design:main2(WindowsOS) -->
 	<footer id="footer">
		<ul id="footer_menu">
			<li class="homeButton"><a href="#"></a></li>
			
			<!-- Profile Button -->
			<li> <a href="#">iamapark89@me2day</a>
				<div class="three_column_layout">
					<div class="col_3">
						<h2>Example of Three Columns</h2>
					</div>
					
					<div class="clear"></div>
					
					<div class="col_1">
						<p>I love tuna so much</p>
					</div>
					<div class="col_1">
						<p>I love tuna so much</p>
					</div>
					<div class="col_1">
						<p>I love tuna so much</p>
					</div>
					
					<div class="clear"></div>
					
					<div class="col_1">
						<p class="black_box">This is the black box styling. I use if for</p>
					</div>
					
					<div class="col_2">
						<p>This is the black box styling. I use if forThis is the black box styling. I use if for</p>
					</div>
					
					<div class="clear"></div>
					
					<div class="col_3">
						<h2>My Profile</h2>
					</div>
					
					<div class="clear"></div>
					
					<div class="col_3">
						<p>
							<img src="http://25.media.tumblr.com/tumblr_m97py1jnbI1rpxdleo1_500.png" class="img_left whiteBorder" />
							<div style="margin-top:10px;">
								<a style="font-weight:bold;color:white;">J.Y.Park</a>
								<a  href="#">Read more...</a>
							</div>
						</p>
					</div>
				</div>
			</li>
			
			<li><a href="#">Messages</a>
				<div class="one_column_layout">
					<div class="col_1">
						<a class="headerLinks">Messages</a>
						<a class="listLinks" style="font-weight:bold;color:white;">Inbox (5)</a>
						<a class="listLinks">Send</a>
						<a class="listLinks">Trash</a>
						<a class="listLinks">Compose</a>
						
						<a class="headerLinks">Pictures</a>
						<a class="listLinks">Manage Albums</a>
						<a class="listLinks">Manage Photos</a>
						<a class="listLinks">View All</a>
						
						<a class="headerLinks">Information</a>
						<a class="listLinks">Basic Info</a>
						<a class="listLinks">Contact</a>
						<a class="listLinks">Education</a>
						<a class="listLinks">Employment</a>
						<a class="listLinks">Links</a>
						
						<a class="headerLinks">Friends</a>
						<a class="listLinks">All</a>
						<a class="listLinks" style="font-weight:bold;color:white;">Requested(13)</a>
						<a class="listLinks">Pending</a>
					</div>
				</div>
			</li>
			
			<li><a href="#">Friends</a>
				<ul class="dropup">
					<li><a href="#">Group Chat</a></li>
					<li><a href="#">Instant Messenger</a></li>
					<li><a href="#">Meme Generator</a></li>
					<li><a href="#">Forum</a></li>
				</ul>
			</li>
			
			<!-- Logout Button -->
			<li class="right"><a href="#">Log Out</a></li>
		</ul>
		
		<ul id="notifications">
			<li><a href="#" class="notificationIcons">
				<img src="images/Me2Day.png" />
			</a></li>
			<li><a href="#" class="notificationIcons">
				<img src="images/Twitter.png" />
			</a></li>
			<li>
				<a href="#" class="notificationIcons">
					<img src="images/Facebook.png" />					
					<span>
						<img src="images/defaultProfile.jpg" style="float:left;width:24px;margin-right:5px;" />
						<div style="display:inline;color:#CC0000;font-weight:bold;">G.N.Heo</div> GwangNam like your image
						<hr style="border:none;border-bottom:1px solid #777777;" />
						
						<img src="images/defaultProfile.jpg" style="float:left;width:24px;margin-right:5px;" />
						<div style="display:inline;color:#CC0000;font-weight:bold;">J.H.Koo</div> JaHoon like your post
						<hr style="border:none;border-bottom:1px solid #777777;" />
						
						<img src="images/defaultProfile.jpg" style="float:left;width:24px;margin-right:5px;" />
						<div style="display:inline;color:#CC0000;font-weight:bold;">이말년</div> 말년님이 당신의 사진을 좋아합니다.
						<hr style="border:none;border-bottom:1px solid #777777;" />
					</span>
				</a>
			</li>
		</ul>
		
	</footer>
	</div>
 	
 	
 	
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
				<option value="main">디자인 1: MacOS</option>
				<option value="main2">디자인 2: WindowsOS</option>
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
	<script type="text/javascript" src="js/main/main.js" id="designSelectedJs"></script>
	<script>
		$('#designSelect').change(function(){
			var styleSelected = $('#designSelect option:selected').val();
			console.log(styleSelected);
			changeCSS(styleSelected);
			changeElement(styleSelected);
			changeJS(styleSelected);
		});
		
		$('#designSelectButton').click(function(){
			var styleSelected = $('#designSelect option:selected').val();
			console.log(styleSelected);
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
			if(fname == 'main'){ //MacOS 선택
				$("#designMainContainer").show();
				$("#designMain2Container").hide();
				
			}else if(fname == 'main2'){ //WindowsOS 선택
				$("#designMainContainer").hide();
				$("#designMain2Container").show();
			}
		}
	</script>
</body>
</html>