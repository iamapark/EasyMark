<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="bookMarkRecommand" class="modal hide fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
		
		
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">BookMark 보내기</h3>
		</div>
		<div class="modal-body">

			<form name="sendBookMark" id="sendBookMark">


				<div class="control-group">

					
					<label class="control-label" for="inputId">Friend ID</label>
						<div class="controls">
							<input type="text" name="recommend_friendId" id="recommend_friendId" placeholder="FriendList" style="width:90%;" value=<%= request.getAttribute("friendId") %>>
						</div>
				</div>
					
				<div class="control-group">
					<label class="control-label" for="inputUrl">BookMark URL</label>
					<div class="controls">
						<input type="text" name="recommend_url" id="recommend_url" style="width:90%;">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputUrl">BookMark Name</label>
					<div class="controls">
						<input type="text" name="recommend_name" id="recommend_name" style="width:90%;">
					</div>
				</div>

				<div class="control-group">
					<label class="textarea">BookMark Description</label>
					<div class="controls">
						<textarea rows="3" name="recommend_descript" id="recommend_descript" style="width:90%;"></textarea>
					</div>

				</div>
			</form>
		</div>


		<div class="modal-footer">
			<button class="btn btn-info" id="sendButton">send</button>
			<button id="messageBoxCloseButton" class="btn btn-primary"
				data-dismiss="modal" aria-hidden="true">Close</button>
		</div>


</div>
