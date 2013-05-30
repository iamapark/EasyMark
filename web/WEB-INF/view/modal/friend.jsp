<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<script>
	
		
</script>

<div id="friendInfo" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width:80%; left:10%; right:10%; 	margin-left:0;">	
	<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">X</button>
		<h3 id="settingLabel">친구 관리-${MEMBERID}</h3>
			
	</div>
	<div class="modal-body">
		
		<div class="setting_nav" style="float:left; margin-right:20px;">
			<ul class="nav nav-tabs nav-stacked" id="friendTab">
			  
			  <li><a href="#friend_myFriend" data-toggle="tab">친구 리스트</a></li>
			  <li><a href="#friend_sendRequest" data-toggle="tab">내가 친구 요청한 리스트</a></li>
			  <li><a href="#friend_takeRequest" data-toggle="tab">내가 친구 요청받은 리스트</a></li>
			  <li><a href="#friend_BookMark" data-toggle="tab">추천받은 북마크 리스트</a></li>
			 <%	String me2dayId1 = (String)request.getSession().getAttribute("MEMBERID"); 
	               	String []me2dayId2 = me2dayId1.split("@"); 
	                				
	                String me2dayKey = (String)request.getSession().getAttribute("memberKey");
	                
					if(me2dayKey == "Me2Login"){
	            %>
			     	<li><a href="#friend_me2day" data-toggle="tab" onclick="me2dayConnect('<%=me2dayId2[0]%>')">미투데이 친구 리스트</a><li>		
				<% 	}
	                else {} 
	            %>	
	            
			  </ul>	
			 
		</div>
		
		<div class="setting_content" >
			
			<div class="tab-content" style="border:1px solid #ddd; padding-left:10px;">
			  		
			  	<!-- 친구 리스트 -->				
			  	<div class="tab-pane active" id="friend_myFriend">
			  		<br/>
			  		
			  		<form class="form-search">
			  			<div class="input-append"> 
							<input class="input-medium search-query" style="padding:14px" name="keyword" id="keyword" type="text" placeholder="ID">
							<button type="button" class="btn btn-success" onclick='searchMember("${sessionScope.MEMBERID}");'>회원검색</button>
						</div>	
					</form>
					
					<hr/>
											
			  		<h3>친구 리스트</h3>
			  	
					<div class = "box-content">
					  	
					<table class="table table-striped table-bordered bootstrap-datatable friendtable">
						<thead>
							<tr>
					          <th>UserID</th>
					          <th>UserName</th>
					          <th>E-mail</th>
					          <th>Action</th>                                          
					     	</tr>
						</thead>
						<tbody>
						
						
						</tbody>
						
						
					</table>
					
					</div>
		  		</div>
		  		<!-- 친구 리스트 종료 -->
		  		
		  		<!-- 내가 친구 요청한 리스트 -->
		  		<div class="tab-pane" id="friend_sendRequest">
			  		<h3>내가 친구 요청한 리스트</h3>
			  			<div class = "box-content">
							<table class="table table-striped table-bordered bootstrap-datatable sendfriendtable">
								<thead>
									<tr>
							          <th>UserID</th>
							          <th>Status</th>                                          
							     	</tr>
								</thead>
								<tbody>
								
								
								</tbody>
								
								
							</table>
						</div>	
			  		
		  		</div>
		  		<!-- 내가 친구 요청한 리스트 종료-->
		  		
		  		<!-- 내가 친구 요청받은 리스트 -->
		  		<div class="tab-pane" id="friend_takeRequest">
		  			<h3>내가 친구 요청받은 리스트</h3>
		  				<div class = "box-content">
							<table class="table table-striped table-bordered bootstrap-datatable takefriendtable">
								<thead>
									<tr>
							          <th>UserID</th>
							          <th>Status</th>                                          
							     	</tr>
								</thead>
								<tbody>
								
								
								</tbody>
								
								
							</table>
						</div>	
					
		  		</div>
		  		<!-- 내가 친구 요청받은 리스트 종료 -->
		  		
		  		<!-- 추천받은 북마크 리스트 -->
		  		<div class="tab-pane" id="friend_BookMark">
		  			<h3>추천 북마크 리스트</h3>
		  			<ul class="nav nav-tabs" id="webSiteTab">
					  <li class="active">
					  	<a href="#inWeb" data-toggle="tab">추천 받은 WEB</a></li>
					  <li>
					  	<a href="#outWeb" data-toggle="tab">추천 한 WEB</a></li>
					</ul>
					<div class="tab-content">
					  	<div class="tab-pane active" id="inweb">
						  	<div class = "box-content">
								<table class="table table-striped table-bordered bootstrap-datatable inwebtable">
									<thead>
										<tr>
								          	<th>ID</th>
											<th>URL</th>
											<th>NAME</th>
											<th>RECOMMENDATION</th>                                        
								     	</tr>
									</thead>
									<tbody>
									
									
									</tbody>
									
								</table>
							</div>
				  		</div>
					  
					  	<div class="tab-pane" id="outWeb">
						  	<div class = "box-content">
								<table class="table table-striped table-bordered bootstrap-datatable outwebtable">
									<thead>
										<tr>
								          	<th>ID</th>
											<th>URL</th>
											<th>NAME</th>
											<th>RECOMMENDATION</th>                                        
								     	</tr>
									</thead>
									<tbody>
																		
									</tbody>
									
								</table>
							</div>
					  	</div>
					</div>
		  		</div>
		  		<!-- 추천받은 북마크 리스트 종료 -->
		  		
		  				  		
		  		<!-- 미투데이 친구 리스트 -->
		  		<div class="tab-pane" id="friend_me2day">
		  			<h3>Me2day 친구 리스트</h3>
			  			<div class = "box-content">
							<table class="table table-striped table-bordered bootstrap-datatable me2friendtable">
								<thead>
									<tr>
							          <th>UserID</th>
							          <th>Status</th>                                          
							     	</tr>
								</thead>
								<tbody>
								
								
								</tbody>
								
								
							</table>
						</div>
		  		</div>
		  		<!-- 미투데이 친구 리스트 종료 -->
	  		</div>
		</div>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">닫기</button>
	</div>
	
	
	
</div>
