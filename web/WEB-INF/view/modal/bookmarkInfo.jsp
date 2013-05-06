<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- bookMarkInfo -->
<div id="bookMarkInfo" class="modal hide fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">X</button>
		<h3 id="myModalLabel">BookMarkInfo</h3>
	</div>
	<div class="modal-body">
		
		 <form action="modifyMark" name="modifyBookMarkForm" id="modifyBookMarkForm" method="post" enctype="multipart/form-data">
		 <input type="hidden" id="modifyBookMarkId" name="bookmarkId"/>
         <div class="control-group">
           <label class="control-label" for="name">name</label>
           <div class="controls">
             <input type="text" id="name" name="modifyBookmarkName" >
           </div>
         </div>
         <div class="control-group">
           <label class="control-label" for="url">url</label>
           <div class="controls">
             <input type="text" id="url" name="modifyBookmarkUrl"  >
           </div>
         </div>
         <div class="control-group">
         <label class="textarea" for="description">desc</label>
           <div class="controls">
              <textarea rows="4" cols="10" id="description"  name="modifyBookmarkDescription"></textarea>
           </div>
         </div>
         <div class="control-group">
         	<label class="textarea" for="bookmarkIconImage">북마크 아이콘 이미지</label>
         	<img id="bookmarkIconImage" style="width:100px;"><br>
         	<input type="file" name="modifyBookmarkImage" id="bookmarkIconImageFile">
         </div>
       	 </form>

	</div>
	<div class="modal-footer">
		<button type="submit" class="btn" id="modify">modify</button>
        <button class="btn" id="delete">delete</button>
		<button id="messageBoxCloseButton" class="btn btn-primary"
			data-dismiss="modal" aria-hidden="true">Close</button>
	</div>
</div>