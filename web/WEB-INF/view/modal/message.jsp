<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<div id="messages" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" 
	 style="width: 60%; left:10%; right:10%; margin-left:0;">	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">X</button>
		<h3 id="settingLabel">메세지-${MEMBERID}</h3>
	</div>
	<div class="modal-body">
		<div class="setting_nav" style="float:left; margin-right:20px;">
			<ul class="nav nav-tabs nav-stacked" id="messageTab">
			  <li><a href="#message_takeList" data-toggle="tab">받은 쪽지함</a></li>
			  <li><a href="#message_sendList" data-toggle="tab">보낸 쪽지함</a></li>
			  <li><a href="#message_sending" data-toggle="tab">쪽지 보내기</a></li>
			</ul>
		</div>	
		<div class="setting_content" >
			<div class="tab-content" style="border:1px solid #ddd; padding-left:10px;">
				
				<!-- 받은 쪽지 리스트 -->
				<div class="tab-pane active" id="message_takeList">
					<h3>받은 쪽지</h3>
						<div class = "box-content">
							<table id="checkboxTbl" class="table table-striped table-bordered bootstrap-datatable takemessagetable">
								<thead>
									<tr>
							          <th><button class="btn btn-small btn-primary" id="messageTableAllSelect">전체 선택</button></th>
							          <th>ID</th>
							          <th>Contents</th>
							          <th>Date</th>                                          
							     	</tr>
								</thead>
								<tbody>
						
								</tbody>
							</table>
							
						</div>
						
				</div>
				<!-- 받은 쪽지 리스트 종료 -->
				
				<!-- 보낸 쪽지 리스트 -->
				<div class="tab-pane" id="message_sendList">
			  		<h3>보낸 쪽지</h3>
			  			<div class = "box-content">
							<table class="table table-striped table-bordered bootstrap-datatable sendmessagetable">
								<thead>
									<tr>
							          <th></th>
							          <th>ID</th> 
							          <th>Contents</th> 
							          <th>Date</th>                                          
							     	</tr>
								</thead>
								<tbody>
								
								
								</tbody>
							</table>
						</div>	
		  		</div>
		  		<!-- 보낸 쪽지 리스트 종료-->
				
				<!-- 쪽지 보내기 -->
				<div class="tab-pane" id="message_sending">
			  		<h3>쪽지 보내기</h3>
			  			<div class = "box-content">
							<div class="input-prepend">
								<span class="add-on">받는 사람</span>
								  <input id="messageFriendId" type="text" onkeyup="doAutoComplete()" name="searchName" placeholder="친구 아이디">
							</div>
							<div>
								<textarea id="messageContents" style="width:90%;" rows="4" cols="20"></textarea>
							</div>
							
							<button id="messageSendButton" class="btn btn-primary" >보내기</button>
							<button id="messageSendingButton" class="btn btn-primary" style="display:none;">보내는 중...</button>
						</div>	
		  		</div>
		  		<!-- 쪽지 보내기 종료 -->
				
				
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">닫기</button>
	</div>
	
</div>

