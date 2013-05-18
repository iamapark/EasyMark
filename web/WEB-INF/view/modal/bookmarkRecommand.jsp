<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="bookMarkRecommand" class="modal hide fade" tabindex="-1" data-width="450">
		
		
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">BookMark 보내기</h3>
		</div>
		<div class="modal-body">


			<div class="control-group">

			<form class="send" name="sendBookMark" action="" method="post">
				<div class="control-group">
					<label class="control-label" for="inputUrl">FriendList</label>
					<div class="controls">
						<input type="text" id="friendList2" name="friendList">
					</div>
				</div>

=
			<form class="send" name="sendBookMark" action="recommend" method="post">


				<div class="control-group">

					
					<label class="control-label" for="inputId">ID</label>
						<div class="controls">
							<input type="text" name="friendId" id="recommend_friendId" placeholder="FriendList" value=<%= request.getAttribute("friendId") %>>
							<%-- <BUTTON class=btn-small href="#FriendList" data-toggle="modal" onclick="friend('${sessionScope.MEMBERID}')">Friend</BUTTON> --%>
						</div>
				</div>
					
				<div class="control-group">
					<label class="control-label" for="inputUrl">url</label>
					<div class="controls">
						<input type="text" id="recommend_url" name="bookMarkUrl">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputUrl">name</label>
					<div class="controls">
						<input type="text" id="recommend_name" name="bookMarkName">
					</div>
				</div>

				<div class="control-group">
					<label class="textarea">desc</label>
					<div class="controls">
						<textarea rows="4" cols="10" id="recommend_descript" name="bookMarkDescript"></textarea>
					</div>

				</div>
				<div>
					<button class="send" id="sendButton">send</button>
			</div>


		</div>


		<div class="modal-footer">
			<button id="messageBoxCloseButton" class="btn btn-primary"
				data-dismiss="modal" aria-hidden="true">Close</button>
		</div>


	</div>

	
