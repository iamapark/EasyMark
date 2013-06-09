<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
	<div data-role="panel" id="myInfo" data-theme="b">
	<div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
      	 	<label for="myInfo_password" class="ui-input-text">ID:</label>
       	<input readonly type="text" value="${MEMBERINFO.userId}" id="myInfo_userId" name="userId" class="ui-input-text ui-body-null ui-corner-all ui-shadow-inset ui-body-c">
	</div>
	
      <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
        <label for="myInfo_userName" class="ui-input-text">Username:</label>
        <input type="text" value="${MEMBERINFO.name}" id="myInfo_userName" name="userName" class="ui-input-text ui-body-null ui-corner-all ui-shadow-inset ui-body-c">
	</div>
	
	<div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
        <label for="myInfo_email" class="ui-input-text">email:</label>
        <input type="text" value="${MEMBERINFO.email}" id="myInfo_email" name="email" class="ui-input-text ui-body-null ui-corner-all ui-shadow-inset ui-body-c">
	</div>

	<div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
        <label for="myInfo_password" class="ui-input-text">개인 이미지</label>
        <img style="width:75%; " src="../${MEMBERINFO.imgUrl}" id="myInfo_img">
	</div>

	<fieldset class="ui-grid-a">
		<div class="ui-block-a"><button id="loginButton" data-theme="b" type="button" class="ui-btn-hidden" aria-disabled="false">수정</button></div>
    </fieldset>
  </div><!-- /panel -->
</body>
</html>