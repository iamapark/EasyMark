<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 북마크 추가 -->
<div data-role="popup" data-theme="b" id="bookmarkAddPage">
	<div data-role="header" data-theme="b">
		<h1>EasyMark 북마크 추가</h1>
	</div>
	<div data-role="content" data-theme="b">
		<!--Register form start-->
		<form id="bookmarkAddForm" action="bookmarkAddForm" method="post" data-ajax="false">

			<div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
	        	<label for="name" class="ui-input-text">URL:</label>
	        	<input type="text" value="" id="bookmark_add_url" name="url" class="ui-input-text ui-body-null ui-corner-all ui-shadow-inset ui-body-c">
	        	<span id="idCheckResult"></span>
			</div>
			
			<div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
	        	<label for="name" class="ui-input-text">이름:</label>
	        	<input type="text" value="" id="bookmark_add_name" name="name" class="ui-input-text ui-body-null ui-corner-all ui-shadow-inset ui-body-c">
			</div>
			
			<div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
	        	<label for="name" class="ui-input-text">설명:</label>
	        	<input type="email" value="" id="bookmark_add_descript" name="descript" class="ui-input-text ui-body-null ui-corner-all ui-shadow-inset ui-body-c">
			</div>

			<fieldset class="ui-grid-a">
				<div class="ui-block-a"><button onclick="bookmarkAdd();" id="bookmarkAddButton" data-theme="b" type="button" class="ui-btn-hidden" aria-disabled="false">추가</button></div>
				<div class="ui-block-b"><button data-theme="b" type="reset" class="ui-btn-hidden" aria-disabled="false">Clear</button></div>
		    </fieldset>

		</form>
			<!-- /Register form end-->
		</div>
	</div>
	<!-- 북마크 추가 페이지 종료 -->