<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- bookMarkInfo -->
<div id="categoryInfo" class="modal hide fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">X</button>
		<h3 id="myModalLabel">BookMarkInfo</h3>
	</div>
	<div class="modal-body">
		
		 <form action="modifyCategory" name="modifyCategoryForm" id="modifyCategoryForm" method="post" enctype="multipart/form-data">
		 <input type="hidden" id="modifyBookmarkId" name="modifyBookmarkId"/>
		 <input type="hidden" id="modifyCategoryId" name="modifyCategoryId"/>
         <div class="control-group">
           <label class="control-label" for="name">name</label>
           <div class="controls">
             <input type="text" id="modifyCategoryName" name="modifyCategoryName" style="width:90%;">
           </div>
         </div>
       	 </form>

	</div>
	<div class="modal-footer">
		<button type="submit" class="btn" id="categoryModify">modify</button>
		<button id="categoryBoxCloseButton" class="btn btn-primary"
			data-dismiss="modal" aria-hidden="true">Close</button>
	</div>
</div>