<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- bookmarkAdd -->  
<div id="bookmarkAdd" class="modal hide fade" tabindex="-1" aria-hidden="true" style="width:90%; margin-left:0;">	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">X</button>
		<h3 id="settingLabel">북마크 추가-${MEMBERID}</h3>
	</div>
	<form id="addMarkForm" name="addMarkForm" action="addMark" method="post" enctype="multipart/form-data">
	<div style="display:inline;">
	<div class="modal-body" style="width:45%; float:left;">
	    <div class="control-group">
	      <label class="control-label" for="addBookMarkUrl">url</label>
	      <div class="controls">
	        <input type="text" name="url" id="addBookMarkUrl" placeholder="Url" style="width:90%;">
	      </div>
	    </div>
	    <div class="control-group">
	    <label class="control-label" for="addBookMarkName">name</label>
	      <div class="controls">
	        <input type="text" name="name" id="addBookMarkName" placeholder="Name" style="width:90%;">
	      </div>
	    </div>
	    <div class="control-group">
	    <label class="textarea">desc</label>
	      <div class="controls">
	         <textarea rows="5" cols="3" id="addBookMarkDescription" name="description" placeholder="Desc" style="width:90%;"></textarea>
	      </div>
	    </div>
	</div>
	<div class="modal-body" style="width:45%; float:right;">
		<div class="control-group">
        	<label class="control-label" for="addBookMarkCategory">Category</label>
         	<div class="controls">
              	<div id="addCategoryOl"></div>
              	<input type="hidden" id="hiddenBookmarkAddCategory" name="category" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="addBookMarkImage">북마크 이미지</label>
         	<div class="controls">
              	<input type="file" id="addBookMarkImage" name="addBookMarkImage">
            </div>
        </div>
	</div>
	</div>
	
	</form>
	<div class="clear"></div>
	<div class="modal-footer">
		<button class="btn btn-primary" id="add">추가</button>
		<button class="btn" data-dismiss="modal" aria-hidden="true">닫기</button>
	</div>
</div>