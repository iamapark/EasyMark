<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="setting" class="modal hide fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	style="width: 70%; left: 15%; right: 15%; margin-left: 0;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">X</button>
		<h3 id="settingLabel">Setting-${MEMBERID}</h3>
	</div>
	<div class="modal-body">
		<div class="setting_nav" style="float: left; margin-right: 20px;">
			<ul class="nav nav-tabs nav-stacked">
				<li><a href="#setting_memberInfo" data-toggle="tab">회원정보</a></li>
				<li><a href="#setting_bookmarkInfo" data-toggle="tab">북마크</a></li>
				<li><a href="#setting_categories" data-toggle="tab">카테고리</a></li>
				<li><a href="#setting_settings" data-toggle="tab">설정</a></li>
			</ul>
		</div>
		<div class="setting_content">
			<div class="tab-content"
				style="border: 1px solid #ddd; padding-left: 10px;">
				<div class="tab-pane active" id="setting_memberInfo">
					<!-- 회원 개인 정보  & 탈퇴 신청<-->
					<h4>회원정보</h4>
					<form class="form-horizontal" action="updateMemberInfo"
						id="updateMemberInfoForm" method="POST"
						enctype="multipart/form-data">
						<div class="control-group">
							<label class="control-label" for="inputId">아이디</label>
							<div class="controls">
								<input type="text" id="setting_userId" placeholder="아이디"
									READONLY>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputName">이름</label>
							<div class="controls">
								<input type="text" id="setting_name" placeholder="이름"
									name="setting_name">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputEmail">이메일</label>
							<div class="controls">
								<input type="text" id="setting_email" placeholder="이메일 주소"
									name="setting_email">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPersonalImg">개인
								이미지</label>
							<div class="controls">
								<img id="inputPersonalImg" src="images/defaultProfile.jpg"
									style="width: 100px;">
								<div>
									<input type="file" name="file" id="personalImg">
								</div>
							</div>
						</div>
						<button type="submit" class="btn btn-primary"
							id="updateMemberButton">회원정보 수정</button>
						<button class="btn btn-danger"
							id="leaveMembership">회원 탈퇴</button>
						<button class="btn btn-inverse"
							id="logoutButton">로그 아웃</button>														
					</form>
				</div>
				<div class="tab-pane" id="setting_settings">
					<!-- 비밀번호 변경, 디자인 변경 -->
					<div>
						<!-- 배경화면 변경 -->
						<h5>배경화면 변경</h5>
						<img id="bgImg" src="images/calendar.png" style="width: 15%;" /><br>
						<form action="updateBgImg" name="bgImgForm" id="bgImgForm"
							method="post" enctype="multipart/form-data">
							<input type="file" id="backgroundImgFile"
								name="backgroundImgFile">
						</form>
			  	  	</div>
			  	  	<div class="tab-pane" id="setting_category">
				  		<h5>카테고리 추가</h5>
				  		<form action="addCategory" name="addCategory" id="addCategory" method="post" enctype="multipart/form-data">
				  			<select id="categorySelect" name="style">
							<!-- categorySelect option appendTo -->
							</select>
				  			<input type="text" id="categoryName" name="categoryName">
					  		<input type="button" id="categoryAddButton" name="category" value="add">
					  	</form>
				  	</div>
		  	  	</div>
				<div class="tab-pane" id="setting_bookmarkInfo">
				  	<!-- 북마크 리스트 -->
				  	<h1>북마크 리스트</h1>
				  	
				  	<div class="row-fluid sortable">		
					<div class="box span12">
					<div class="box-content">
						<table id="setting_bookmarkList" class="table table-striped table-bordered bootstrap-datatable bookmarktable">
						  <thead>
							  <tr>
							  	  <th>선택</th>
							  	  <th>번호</th>
								  <th>이름</th>
								  <th>URL</th>
								  <th>설명</th>
								  <th>접속 횟수</th>
							  </tr>
						  </thead>
						  <!-- <tbody id="MemberDataTable">
							여기에 회원 정보가 들어간다.
						  </tbody> -->
					  </table>    
					</div>
					</div>
					</div>	
					<a onclick='bookmarkListAllSelection(this)' class='btn btn-primary' href='#bookmarkSelectionButton'>
						전체 선택
					</a> 
					<a onclick='bookmarkListDelete(this)' class='btn btn-danger' href='#bookmarkDeleteButton'>
						선택 삭제
					</a> 

				</div>
				<div class="tab-pane" id="setting_categories">
				  	<!-- 북마크 리스트 -->
				  	<h1>카테고리</h1>
				  	<div class="row-fluid sortable">		
					<div class="box span12">
					<div class="box-content">
						<div id="categoryOl"></div>
						
						<div>
							선택된 카테고리: <span style="background-color:rgb(206, 67, 55); color:white;" id="selectedCategory"></span><br>
							<input style="height:30px;" type="text" id="newCategory">
							<button class="btn btn-primary" id="newCategoryButton">카테고리 생성</button><br>
							<button class="btn btn-danger" id="deleteCategoryButton">카테고리 삭제</button>
						</div>
					</div>
					</div>
					</div>	
				</div>
			</div>
		</div>

	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">닫기</button>
	</div>
</div>
