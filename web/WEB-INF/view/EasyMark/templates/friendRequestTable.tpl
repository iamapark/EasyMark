<table class="table table-bordered" width="70%">
				<thead>
				<tr>
					<th>USER ID</th>
					<th>STATUS</th>
				</tr>	
			
				<c:forEach items="${requestScope.sendFriendReqList}" var="sendFriend">
					<tr>
						<td>${sendFriend.friendId}</td>
						<td>
							<button id="cancel" class="btn btn-small btn btn-warning" onclick="cancel('${sessionScope.MEMBERID}','${sendFriend.friendId}');">요청취소</button>
						</td>
					</tr>		
				</c:forEach>
			
			</table>
			
			
			
			<table class="table table-bordered" width="70%">
				<thead>
					<tr>
						<th>USER ID</th>
						<th>STATUS</th>
						<!-- <th>이&nbsp;&nbsp;름</th> -->
					</tr>	
				</thead>
			<c:forEach items="${requestScope.takeFriendReqList}" var="takeFriend">
				<tr id="${takeFriend.userId}">
					<td>${takeFriend.userId}&nbsp;&nbsp;&nbsp;</td>
					<%-- <td>&nbsp;&nbsp;&nbsp;${member.firstName}&nbsp;&nbsp;&nbsp;</td> --%>
					<td width="140px">
						<%-- '${takeFriend.friendshipId}', --%>
						<button id="accept" class="btn btn-small btn btn-primary" onclick="acceptFriend('${sessionScope.MEMBERID}','${takeFriend.userId}');">수락</button>
						<button id="reject" class="btn btn-small btn btn-danger" data-dismiss="modal" aria-hidden="true" onclick="rejectFriend('${sessionScope.MEMBERID}','${takeFriend.userId}');">거절</button>
					</td>
				</tr>		
			</c:forEach>
			
			</table>