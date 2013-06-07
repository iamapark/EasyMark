<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
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
             <input type="text" id="modifyBookmarkName" name="modifyBookmarkName" style="width:90%;">
           </div>
         </div>
         <div class="control-group">
           <label class="control-label" for="url">url</label>
           <div class="controls">
             <input type="text" id="modifyBookmarkUrl" name="modifyBookmarkUrl"  style="width:90%;">
           </div>
         </div>
         <div class="control-group">
         <label class="textarea" for="description">desc</label>
           <div class="controls">
              <textarea rows="4" cols="10" id="modifyBookmarkDescription"  name="modifyBookmarkDescription" style="width:90%;"></textarea>
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
</div> --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- bookMarkInfo -->  
<div id="bookMarkInfo" class="modal hide fade" tabindex="-1" aria-hidden="true" style="width:90%; margin-left:0;">	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">X</button>
		<h3 id="settingLabel">BookMarkInfo</h3>
	</div>
	<form id="modifyBookMarkForm" name="modifyBookMarkForm" action="modifyMark" method="post" enctype="multipart/form-data">
	<input type="hidden" id="modifyBookMarkId" name="bookmarkId"/>
	<div style="display:inline;">
	<div class="modal-body" style="width:45%; float:left;">
	    <div class="control-group">
	      <label class="control-label" for="addBookMarkUrl">url</label>
	      <div class="controls">
	        <input type="text" name="modifyBookmarkUrl" id="modifyBookmarkUrl" placeholder="Url" style="width:90%;">
	      </div>
	    </div>
	    <div class="control-group">
	    <label class="control-label" for="addBookMarkName">name</label>
	      <div class="controls">
	        <input type="text" name="modifyBookmarkName" id="modifyBookmarkName" placeholder="Name" style="width:90%;">
	      </div>
	    </div>
	    <div class="control-group">
	    <label class="textarea">desc</label>
	      <div class="controls">
	         <textarea rows="5" cols="3" id="modifyBookmarkDescription" name="modifyBookmarkDescription" placeholder="Desc" style="width:90%;"></textarea>
	      </div>
	    </div>
	</div>
	<div class="modal-body" style="width:45%; float:right;">
        <div class="control-group">
            <label class="control-label" for="addBookMarkImage">북마크 이미지</label>
         	<div class="controls">
         		<img id="bookmarkIconImage" style="width:100px;"><br>
              	<input type="file" id="bookmarkIconImageFile" name="modifyBookmarkImage">
            </div>
        </div>
     	<div class="control-group">
        	<label class="control-label" for="addBookMarkCategory">Category</label>
         	<div class="controls">
              	<div id="modifyCategoryOl"></div>
              	<input type="hidden" id="hiddenBookmarkModifyCategory" name="modifyBookMarkCategory" />
            </div>
        </div>
	</div>
	</div>
	
	</form>
	<div class="clear"></div>
	<div class="modal-footer">
		<button class="btn btn-primary" id="modify">수정</button>
		<button class="btn" data-dismiss="modal" aria-hidden="true">닫기</button>
	</div>
</div>