<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- design:main(MacOS) -->
 	<div id="designMainContainer">
 	<div id="dock-container">
		<div id="dock">
		<ul>
			<li><span style="color:white;">${MEMBERID}</span><a href="#setting" role="button" data-toggle="modal"><img src="images/settings.png"/></a></li>
			<li><span style="color:white;">Link</span><a href="#"><img src="images/link.png"/></a></li>
			<li><span style="color:white;">Music</span><a href="#"><img src="images/music.png"/></a></li>
			<li><span style="color:white;">Portfolio</span><a href="#"><img src="images/portfolio.png"/></a></li>
			<li><span style="color:white;">Rss</span><a href="#"><img src="images/rss.png"/></a></li>
			<li><span style="color:white;">Video</span><a href="#"><img src="images/video.png"/></a></li>
		</ul>
		<div class="base"></div>
		</div>
	</div>
	</div>

	<!-- setting 메뉴를 클릭했을 때 MODAL -->
	<div id="setting" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width:70%; left:15%; right:15%; 	margin-left:0;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="settingLabel">Setting-${MEMBERID}</h3>
		</div>
		<div class="modal-body">
			<div class="setting_nav" style="float:left; margin-right:20px;">
				<ul class="nav nav-tabs nav-stacked">
				  <li><a href="#home" data-toggle="tab">회원정보</a></li>
				  <li><a href="#profile" data-toggle="tab">북마크</a></li>
				  <li><a href="#messages" data-toggle="tab">설정</a></li>
				</ul>
			</div>
			<div class="setting_content" >
				<div class="tab-content" style="border:1px solid #ddd;">
				  <div class="tab-pane active" id="home">HOME</div>
				  <div class="tab-pane" id="profile">PROFILE</div>
				  <div class="tab-pane" id="messages">MESSAGES</div>
				</div>
			</div>
			
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
			<button class="btn btn-primary">Save changes</button>
		</div>
	</div>