<table class="table table-bordered" width="70%">
				<thead>
					<tr>
			          <th>UserID</th>
			          <th>UserName</th>
			          <th>E-mail</th>
			          <th>Delete</th>                                          
			     	</tr>
				</thead>
				<tbody>
				<c:forEach items="${requestScope.friendList}" var="friend">
					<tr id="${friend.userId}">
						<td>&nbsp;&nbsp;&nbsp;${friend.userId}&nbsp;&nbsp;&nbsp;</td>
						<td>&nbsp;&nbsp;&nbsp;${friend.firstName}&nbsp;&nbsp;&nbsp;</td>
						<td>&nbsp;&nbsp;&nbsp;${friend.email}&nbsp;&nbsp;&nbsp;</td>
						<td width="50px"><button id="delete" class="btn btn-small btn btn-danger" type="button" onclick="deleteFriend('${sessionScope.MEMBERID}','${friend.userId}');">친구삭제</button></td>					
					</tr>		
				</c:forEach>
				
				</tbody>
			
				
				
				
			</table>