<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Member" %>
<% 
	Member m = (Member)session.getAttribute("MEMBERINFO");
	String imgUrl = m.getImgUrl(); 
	String flag = request.getParameter("flag");
	String display = null;
	
	if(imgUrl != null && !imgUrl.equals("")){
		imgUrl = "users/img/" + m.getUserId() + "/" + imgUrl;
	}else{
		imgUrl = "images/settings.png";
	}
	
	if(flag.equals("MacOS")){
		display = "inline";
	}else
		display = "none";
		
%>
<!-- design:main(MacOS) -->
 	<div id="designMainContainer" style="display:<%=display%>;">
 	<div id="dock-container">
		<div id="dock">
		<ul>
			<li><span style="color:white;">${MEMBERID}</span><a href="#setting" role="button" data-toggle="modal"><img src="<%=imgUrl%>" style="border-radius: 20px;"/></a></li>
			<li><span style="color:white;">북마크 추가</span><a href="#bookmarkAdd" role="button" data-toggle="modal"><img src="images/link.png"/></a></li>
			<li><span style="color:white;">Friends</span><a href="#friends" role="button" data-toggle="modal"><img src="images/music.png"/></a></li>
		</ul>
		<div class="base"></div>
		</div>
	</div>
	</div>