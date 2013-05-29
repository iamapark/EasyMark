<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Member" %>
<% 
	Member m = (Member)request.getAttribute("MEMBERINFO");
	String imgUrl = m.getImgUrl(); 
	String flag = request.getParameter("flag");
	String display = null;
	
	if(imgUrl != null && !imgUrl.equals("")){
		//imgUrl = "users/img/" + m.getUserId() + "/" + imgUrl;
	}else{
		imgUrl = "images/settings.png";
	}
	
	if(flag.equals("MacOS")){
		display = "inline";
	}else
		display = "none";
		
%>
<!-- design:main(MacOS) -->
	<div id="connect">
 	<div id="designMainContainer" style="display:<%=display%>;">
	 	<div id="dock-container">
			<div id="dock">
			<ul>
				<li><span style="color:white;">${sessionScope.MEMBERID}</span><a href="#setting" role="button" data-toggle="modal"><img src="<%=imgUrl%>" id="settingImg" style="border-radius: 20px;"/></a></li>
				<li><span style="color:white;">북마크 추가</span><a href="#bookmarkAdd" role="button" data-toggle="modal"><img src="images/Bookmark.png"/></a></li>
				<li><span style="color:white;">Friends</span><a href="#friendInfo" role="button" data-toggle="modal"><img src="images/Users.png"/></a></li>
				<li><span style="color:white;">Messages</span><a href="#messages" role="button" data-toggle="modal"><img src="images/Message_Icon.png"/></a></li>
			</ul>
			<div class="base"></div>
			</div>
		</div>
	</div>
	</div>