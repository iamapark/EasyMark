<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="bookMarkRecommand" class="modal hide fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		
		
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">BookMark 보내기</h3>
		</div>
		<div class="modal-body">

			<form class="send" name="sendBookMark" action="" method="post">

				<div class="control-group">
					<label class="control-label" for="inputUrl">url</label>
					<div class="controls">
						<input type="text" id="url2" name="bookMarkUrl">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputUrl">name</label>
					<div class="controls">
						<input type="text" id="name2" name="bookMarkName">
					</div>
				</div>

				<div class="control-group">
					<label class="textarea">desc</label>
					<div class="controls">
						<textarea rows="4" cols="10" id="description2" name="bookMarkDescript"></textarea>
					</div>

				</div>
				<div>
					<button type="submit" class="send" id="send">send</button>
			</div>


			</form>
		</div>


		<div class="modal-footer">
			<button id="messageBoxCloseButton" class="btn btn-primary"
				data-dismiss="modal" aria-hidden="true">Close</button>
		</div>


	</div>