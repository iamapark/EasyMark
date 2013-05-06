<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="setting" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width:70%; left:15%; right:15%; 	margin-left:0;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="settingLabel">Setting-${MEMBERID}</h3>
		</div>
		<div class="modal-body">
			<div class="setting_nav" style="float:left; margin-right:20px;">
				<ul class="nav nav-tabs nav-stacked">
				  <li><a href="#setting_memberInfo" data-toggle="tab">회원정보</a></li>
				  <li><a href="#setting_bookmarkInfo" data-toggle="tab">북마크</a></li>
				  <li><a href="#setting_settings" data-toggle="tab">설정</a></li>
				</ul>
			</div>
			<div class="setting_content" >
				<div class="tab-content" style="border:1px solid #ddd; padding-left:10px;">
				  	<div class="tab-pane active" id="setting_memberInfo">
					    <!-- 회원 개인 정보  & 탈퇴 신청<--></-->
					  	<h4>회원정보</h4>
					  	<form class="form-horizontal" action="updateMemberInfo" id="updateMemberInfoForm" method="POST" enctype="multipart/form-data">
						  <div class="control-group">
						    <label class="control-label" for="inputId">아이디</label>
						    <div class="controls">
						      <input type="text" id="setting_userId" placeholder="아이디" READONLY>
						    </div>
						  </div>
						  <div class="control-group">
						    <label class="control-label" for="inputName">이름</label>
						    <div class="controls">
						      <input type="text" id="setting_name" placeholder="이름" name="setting_name">
						    </div>
						  </div>
						  <div class="control-group">
						    <label class="control-label" for="inputEmail">이메일</label>
						    <div class="controls">
						      <input type="text" id="setting_email" placeholder="이메일 주소" name="setting_email">
						    </div>
						  </div>
						  <div class="control-group">
						    <label class="control-label" for="inputPersonalImg">개인 이미지</label>
						    <div class="controls">
						    	<img id="inputPersonalImg" src="images/defaultProfile.jpg" style="width:100px;">
						    	<div><input type="file" name="file" id="personalImg"></div>
						    </div>
						  </div>
						  <button type="submit" class="btn btn-primary" id="updateMemberButton" >회원정보 수정</button>
						</form>
			  	  	</div>
				  	<div class="tab-pane" id="setting_bookmarkInfo">
				  		<!-- 북마크 리스트 -->
					  	북마크
				  	</div>
					<div class="tab-pane" id="setting_settings">
						<!-- 비밀번호 변경, 디자인 변경 -->
					  	<div>
					  		<!-- 디자인 변경 -->
					  		<h5>디자인 변경</h5>
							<select id="designSelect" name="style">
								<option value="MacOS">디자인 1: MacOS</option>
								<option value="WindowsOS">디자인 2: WindowsOS</option>
							</select>
							<span id="designSelectNoti"></span>
					 	</div>
					 	<div>
					  		<!-- 배경화면 변경 -->
					  		<h5>배경화면 변경</h5>
							<img id="bgImg" src="images/calendar.png" style="width:15%;"/><br>
							<form action="updateBgImg" name="bgImgForm" id="bgImgForm" method="post" enctype="multipart/form-data">
								<input type="file" id="backgroundImgFile" name="backgroundImgFile">
							</form>
							<span id="backgroundImgNoti"></span>
					 	</div>
				  	</div>
				</div>
			</div>
			
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">닫기</button>
		</div>
	</div>