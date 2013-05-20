<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<script>
		function deleteFriend(friendId){
			
			console.log("friendId: " + friendId);
		}	
			/* var clickedRow = $("#delete").parent().parent();
			
			$.ajax({
				url: 'deleteFriend',
			//	dataType:'json',
				data: {
						friendId: friendId,
				}
			}).done(function(data){

			});  
	         
	        if( clickedRow.find("td:eq(0)").attr("rowspan") ){
	            if( clickedRow.next().hasClass(friendId) ){
	                clickedRow.next().prepend(clickedRow.find("td:eq(0)"));
	            }
	        }
	
	        clickedRow.remove();
	        resizeRowspan(friendId);  
		}
		
		function resizeRowspan(cls){
	        var rowspan = $("."+cls).length;
	        $("."+cls+":first td:eq(0)").attr("rowspan", rowspan);
	    }*/	
		
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
			  <li><a href="#e" data-toggle="tab">미투데이 친구 리스트</a></li>
			</ul>
		</div>
		<div class="setting_content" >
			<div class="tab-content" style="border:1px solid #ddd; padding-left:10px;">
			  	
			  	<!-- 친구 리스트 -->
			  	<div class="tab-pane active" id="friend_myFriend">
			  		<h3>친구 리스트</h3>
			  		<form class="form-search" style="text-align:right; margin-right:24px; float:right;" action="memberList" id="friendForm" method="post">
						<!-- <div class="input-prepend"> -->
						<div class="input-append"> 
							User ID
							<input class="span2 search-query" name="keyword" id="keyword" type="text" placeholder="User ID">
							<button type="submit" class="btn btn-info">Search</button>
						</div>
					</form>
					
					<div class = "box-content">
					<table class="table table-striped table-bordered bootstrap-datatable friendtable">
						<thead>
							<tr>
					          <th>UserID</th>
					          <th>UserName</th>
					          <th>E-mail</th>
					          <th>Delete</th>                                          
					     	</tr>
						</thead>
						<tbody>
						
						
						</tbody>
						
						
					</table>
						
						<%-- <c:forEach items="${requestScope.friendList}" var="friend">
							<tr>
								<td>&nbsp;&nbsp;&nbsp;${friend.userId}&nbsp;&nbsp;&nbsp;</td>
								<td>&nbsp;&nbsp;&nbsp;${friend.firstName}&nbsp;&nbsp;&nbsp;</td>
								<td>&nbsp;&nbsp;&nbsp;${friend.email}&nbsp;&nbsp;&nbsp;</td>
								<td width="50px"><input class="btn btn-small btn btn-danger" type="button" onclick="deleteFriend('${sessionScope.MEMBERID}','${friend.userId}');" value="친구삭제" /></td>					
							</tr>		
						</c:forEach>
						
					 --%>
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
			  		<%--<table class="table table-bordered" width="70%">
						<thead>
						<tr>
							<th>USER ID</th>
							<th>STATUS</th>
						</tr>	
						
						
						 <c:forEach items="${requestScope.sendFriendReqList}" var="sendFriend">
							<tr>
								<td>${sendFriend.friendId}</td>
								<td>&nbsp;&nbsp;&nbsp;${member.firstName}&nbsp;&nbsp;&nbsp;</td>
								<td>
									<button id="accept" class="btn btn-small btn btn-warning" onclick="cancel('${sessionScope.MEMBERID}','${sendFriend.friendId}');">요청취소</button>
								</td>
							</tr>		
						</c:forEach> --%>
						
					<!-- </table> -->
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
					<%-- 
					
		  			<table class="table table-bordered" width="70%">
					<thead>
						<tr>
							<th>USER ID</th>
							<th>STATUS</th>
						</tr>
					</thead>	
					<tbody>
						<c:forEach items="${requestScope.friendStatusList}" var="me2Friend">
							<tr>
								<td>
									<c:choose>
								    	<c:when test="${me2Friend.status eq '친구'}">
								    		<c:choose>
								    			<c:when test="${me2Friend.userId==sessionScope.MEMBERID}">
									     			${me2Friend.friendId}
									   			</c:when>
									 			
									 			<c:when test="${me2Friend.friendId==sessionScope.MEMBERID}">
										     		${me2Friend.userId}
									     		</c:when>						     	
									     	</c:choose>					     			
								    	</c:when>
								    		
								    	<c:when test="${me2Friend.status eq '친구요청'}">
								    		<c:choose>
								    			<c:when test="${me2Friend.userId==sessionScope.MEMBERID}">
									     			${me2Friend.friendId}
									   			</c:when>
									 			
									     		<c:when test="${me2Friend.friendId==sessionScope.MEMBERID}">
										     		${me2Friend.userId}
									     		</c:when>
									   		</c:choose>
								    	</c:when>
								    	<c:when test="${me2Friend.status eq '친구아님'}">
								    		${me2Friend.friendId}
								    	</c:when>
							     	</c:choose>
										
								</td> 
								
								<td>
									<c:choose>
								    	<c:when test="${me2Friend.status eq '친구'}">
								    		<c:choose>
								    			<c:when test="${me2Friend.userId==sessionScope.MEMBERID}">
									     			<input type="text" value="친구" readonly/>
									   			</c:when>
									 			
									 			<c:when test="${me2Friend.friendId==sessionScope.MEMBERID}">
										     		<input type="text" value="친구" readonly/>
									     		</c:when>						     	
									     	</c:choose>					     			
								    	</c:when>
								    		
								    	<c:when test="${me2Friend.status eq '친구요청'}">
								    		<c:choose>
								    			<c:when test="${me2Friend.userId==sessionScope.MEMBERID}">
									     			<button id="accept" class="btn btn-small btn btn-warning" onclick="cancel('${sessionScope.MEMBERID}','${sendFriend.friendId}');">요청취소</button>
									   			</c:when>
									 			
									     		<c:when test="${me2Friend.friendId==sessionScope.MEMBERID}">
										     		<button id="accept" class="btn btn-small btn btn-primary" onclick="acceptFriend('${sessionScope.MEMBERID}','${takeFriend.userId}');">수락</button>
									     			<button id="reject" class="btn btn-small btn btn-danger" data-dismiss="modal" aria-hidden="true" onclick="rejectFriend('${sessionScope.MEMBERID}','${takeFriend.userId}');">거절</button>
									     		</c:when>
									   		</c:choose>
								    	</c:when>
								    	
								    	<c:when test="${me2Friend.status eq '친구아님'}">
								    		<button id="accept" class="btn btn-small btn btn-primary" >친구추가</button>
								    	</c:when>
							     	</c:choose>
										
								</td>
								
								
							</tr>		
						</c:forEach>
					</tbody>
					</table> --%>
		  		</div>
		  		<!-- 내가 친구 요청받은 리스트 종료 -->
		  		
		  		<!-- 추천받은 북마크 리스트 -->
		  		<div class="tab-pane" id="friend_BookMark">
		  			<h3>추천받은 북마크 리스트</h3>
		  			<ul class="nav nav-tabs" id="webSiteTab">
					  <li class="active"><a href="#inWeb" data-toggle="tab">추천 받은 WEB</a></li>
					  <li><a href="#outWeb" data-toggle="tab">추천 한 WEB</a></li>
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
				  		<%-- <div id="memberId" align="right">${sessionScope.MEMBERID}</div> --%>
				 	 	<%-- <table class="table table-bordered">
							<tr>
								<th>ID</th>
								<th>URL</th>
								<th>NAME</th>
								<th>RECOMMENDATION</th>
							</tr>
									
							<!-- <tr id="inMessage">
							
							<tr> -->
							
							<c:forEach items="${requestScope.inWebList}" var="inWeb">
								<tr id="${inWeb.bookMarkId}" class="${inWeb.bookMarkId}">
									<td>
										${inWeb.userId}
									</td>
									
									<td>
										${inWeb.bookMarkUrl}
									</td>
									
									<td>
										${inWeb.bookMarkName}
									</td>
									
									<td>
										<button id="accept" class="btn btn-small btn btn-primary" onclick="acceptWeb('${inWeb.bookMarkId}','${inWeb.bookMarkUrl}','${inWeb.bookMarkName}','${inWeb.bookMarkDescript}','${inWeb.friendId}')">수락</button>
										<button id="cancel" class="btn btn-small btn btn-danger">거절</button>
									</td>
								</tr>
							</c:forEach>
					 		
					 	</table> --%>
				 
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
					  	<!-- <table class="table table-bordered">
							<tr>
								<th>ID</th>
								<th>URL</th>
								<th>NAME</th>
								<th></th>
							</tr>
							
							<tr id="outWebList">
							</tr>
					 	</table> -->	
					 		
					  </div>
					</div>
		  		</div>
		  		<!-- 추천받은 북마크 리스트 종료 -->
		  		
		  		<!-- 미투데이 친구 리스트 -->
		  		<div class="tab-pane" id="e">
		  			미투데이 친구 리스트
		  		</div>
		  		<!-- 미투데이 친구 리스트 종료 -->
	  		</div>
		</div>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">닫기</button>
	</div>
	
</div>