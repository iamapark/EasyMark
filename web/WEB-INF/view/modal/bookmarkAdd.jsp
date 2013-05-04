<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header id="bookmarkAdd" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width:80%; left:10%; right:10%; 	margin-left:0;">	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">X</button>
		<h3 id="settingLabel">북마크 추가-${MEMBERID}</h3>
	</div>
	<div class="modal-body" style="width:45%; float:left;">
		<form class="add" name="addMark" action="addMark" method="post">
            <div class="control-group">
              <label class="control-label" for="inputName">name</label>
              <div class="controls">
                <input type="text" name="name" id="inputName" placeholder="Name" style="width:90%;">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="inputUrl">url</label>
              <div class="controls">
                <input type="text" name="url" id="inputUrl" placeholder="Url" style="width:90%;">
              </div>
            </div>
            <div class="control-group">
            <label class="textarea">desc</label>
              <div class="controls">
                 <textarea rows="5" cols="3" name="description" placeholder="Desc" style="width:90%;"></textarea>
              </div>
              <div>
              	<button class="btn btn-primary" id="add">추가</button>
              </div>
            </div>
          </form>
	</div>
	<div class="modal-body" style="width:45%;">
		<div class="control-group">
        	<label class="control-label" for="category">Category</label>
         	<div class="controls">
              	<select>
              		<option value="sports">스포츠</option>
              		<option value="movie">영화</option>
              		<option value="economy">경제</option>
              	</select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="bookmarkImg">북마크 이미지</label>
         	<div class="controls">
              	<input type="file">
            </div>
        </div>
	</div>
</header>