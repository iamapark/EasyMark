<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="messages" class="modal hide fade" tabindex="-1" data-focus-on="input:first" aria-hidden="true">	
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
							<button id="takeMessageAllSelect" class="btn btn-primary btn-small">전체 선택</button>
							<a id="takeMessageDelete" class='btn btn-small'>
								선택삭제
							</a>
							<br/>
							<br/>
							<table id="takeMessageTbl" class="table table-striped table-bordered bootstrap-datatable takemessagetable">
								<thead>
									<tr>
							          <th>선택</th>
							          <th>ID</th>
							          <th>Contents</th>
							          <th>Date</th>                                          
							     	</tr>
								</thead>
								<!-- <tbody>
						
								</tbody> -->
							</table>
							
						</div>
						
				</div>
				<!-- 받은 쪽지 리스트 종료 -->
				
				
				
				<!-- 보낸 쪽지 리스트 -->
				<div class="tab-pane" id="message_sendList">
			  		<h3>보낸 쪽지</h3>
			  			<div class = "box-content">
			  				<button id="sendMessageAllSelect" class="btn btn-primary btn-small">전체 선택</button>
			  				<a id="sendMessageDelete" class='btn btn-small'>
								선택삭제
							</a>
							<br/>
							<br/>
							<table id="sendMessageTbl" class="table table-striped table-bordered bootstrap-datatable sendmessagetable">
								<thead>
									<tr>
							          <th>선택</th>
							          <th>ID</th> 
							          <th>Contents</th> 
							          <th>Date</th>                                          
							     	</tr>
								</thead>
								<!-- <tbody>
								
								
								</tbody> -->
							</table>
						</div>	
						
		  		</div>
		  		<!-- 보낸 쪽지 리스트 종료-->
				
				
				<!-- 쪽지 보내기 -->
				<div class="tab-pane" id="message_sending">
			  		<h3>쪽지 보내기</h3>
			  			<div class = "box-content">
			  				
							<div class="input-prepend">
								<span class="add-on" style="padding:14px">받는 사람</span>
								<input class="span2" id="messageFriendId" style="padding:14px" type="text" name="searchName" placeholder="친구 아이디">
							</div>
							<div>
								<textarea id="messageContents" style="width:90%;" rows="4" cols="20"></textarea>
							</div>
							
							<button id="messageSendButton" class="btn btn-primary" >보내기</button>
							<button id="messageSendingButton" class="btn btn-primary" style="display:none;">보내는 중...</button>
							<br/>
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

<div id="messageView" class="modal hide fade" tabindex="-1" data-focus-on="input:first">
	
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3>Message View</h3>
	  </div>
	
	  <div class="modal-body">
	  
		<div id="messageViewDetail">
			<div class="input-prepend">
				<span class="add-on">I D</span> 
				<input class="span2" id="messageViewFriend" style="width: 80%" type="text" readonly>
			</div>
			<span id="messageViewDate">
			</span>
			<div>
				<textarea id="messageViewText" style="width: 90%" rows="4" cols="20"></textarea>
			</div>
		</div>
		
	  </div>
	
	  <div class="modal-footer">
	    <button type="button" data-dismiss="modal" class="btn">Close</button>
	  </div>
	  
</div>
