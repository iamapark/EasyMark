<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="memberInfo" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width:70%; left:15%; right:15%; 	margin-left:0;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">X</button>
		<h3 id="settingLabel">회원 정보</h3>
	</div>
		<div class="modal-body">
			<form class="form-horizontal" action="updateMemberInfo" id="updateMemberInfoForm" method="POST" enctype="multipart/form-data">
			<div class="setting_content" style="width:33%; float:left;">
			    <!-- 회원 개인 정보 -->
			  	
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
			</div>
			<div class="setting_content" style="width: 33%; float:left; margin-left:15%;">
				<div class="control-group">
				    <label class="control-label" for="inputId">북마크 수</label>
				    <div class="controls">
				      <input type="text" id="setting_userId" READONLY>
				    </div>
			  	</div>
			  	<div class="control-group">
				    <label class="control-label" for="inputId">로그인 횟수</label>
				    <div class="controls">
				      <input type="text" id="setting_userId" READONLY>
				    </div>
			  	</div>
			  	<div class="control-group">
				    <label class="control-label" for="inputId">등록일</label>
				    <div class="controls">
				      <input type="text" id="setting_userId" READONLY>
				    </div>
			  	</div>
			  	<div class="control-group">
				    <label class="control-label" for="inputPersonalImg">배경 이미지</label>
				    <div class="controls">
				    	<img id="inputPersonalImg" src="images/defaultProfile.jpg" style="width:100px;">
				    	<div><input type="file" name="file" id="personalImg"></div>
				    </div>
				  </div>
			</div>
			</form>
			
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">닫기</button>
		</div>
</div>