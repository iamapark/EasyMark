<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Member" %>    
<% 
	Member m = (Member)request.getAttribute("MEMBERINFO");
	String imgUrl = m.getImgUrl(); 
	String flag = request.getParameter("flag");
	String display = null;
	
	if(imgUrl != null && !imgUrl.equals("")){
		imgUrl = "users/img/" + m.getUserId() + "/" + imgUrl;
	}else{
		imgUrl = "images/settings.png";
	}
	
	if(flag.equals("WindowsOS")){
		display = "inline";
	}else
		display = "none";
%>

<!-- Footer -->
<div id="designMain2Container" style="display:<%=display%>;">
 	<footer id="footer">
		<ul id="footer_menu">
			<li class="homeButton"><a href="#"></a></li>
			
			<!-- Profile Button -->
			<li> <a href="#">${MEMBERID}</a>
				<div class="three_column_layout">
					<div class="col_3">
						<h2>Setting</h2>
					</div>
					
					<div class="clear"></div>
					
					<div class="col_1">
						<a href="#setting" role="button" data-toggle="modal">setting</a>
					</div>
					
					<div class="clear"></div>
					
					<div class="col_1">
						<p class="black_box">This is the black box styling. I use if for</p>
					</div>
					
					<div class="col_2">
						<p>This is the black box styling. I use if forThis is the black box styling. I use if for</p>
					</div>
					
					<div class="clear"></div>
					
					<div class="col_3">
						<h2>My Profile</h2>
					</div>
					
					<div class="clear"></div>
					
					<div class="col_3">
						<p>
							<img src=<%=imgUrl%> class="img_left whiteBorder" style="width:100px;" />
							<div style="margin-top:10px;">
								<a style="font-weight:bold;color:white;">${MEMBERINFO.name}</a>
								<a  href="#">Read more...</a>
							</div>
						</p>
					</div>
				</div>
			</li>
			
			<li><a href="#">Messages</a>
				<div class="one_column_layout">
					<div class="col_1">
						<a class="headerLinks">Messages</a>
						<a class="listLinks" style="font-weight:bold;color:white;">Inbox (5)</a>
						<a class="listLinks">Send</a>
						<a class="listLinks">Trash</a>
						<a class="listLinks">Compose</a>
						
						<a class="headerLinks">Pictures</a>
						<a class="listLinks">Manage Albums</a>
						<a class="listLinks">Manage Photos</a>
						<a class="listLinks">View All</a>
						
						<a class="headerLinks">Information</a>
						<a class="listLinks">Basic Info</a>
						<a class="listLinks">Contact</a>
						<a class="listLinks">Education</a>
						<a class="listLinks">Employment</a>
						<a class="listLinks">Links</a>
						
						<a class="headerLinks">Friends</a>
						<a class="listLinks">All</a>
						<a class="listLinks" style="font-weight:bold;color:white;">Requested(13)</a>
						<a class="listLinks">Pending</a>
					</div>
				</div>
			</li>
			
			<li><a href="#">Friends</a>
				<ul class="dropup">
					<li><a href="#">Group Chat</a></li>
					<li><a href="#">Instant Messenger</a></li>
					<li><a href="#">Meme Generator</a></li>
					<li><a href="#">Forum</a></li>
				</ul>
			</li>
			
			<!-- Logout Button -->
			<li class="right"><a href="#">Log Out</a></li>
		</ul>
		
		<ul id="notifications">
			<li><a href="#" class="notificationIcons">
				<img src="images/Me2Day.png" />
			</a></li>
			<li><a href="#" class="notificationIcons">
				<img src="images/Twitter.png" />
			</a></li>
			<li>
				<a href="#" class="notificationIcons">
					<img src="images/Facebook.png" />					
					<span>
						<img src="images/defaultProfile.jpg" style="float:left;width:24px;margin-right:5px;" />
						<div style="display:inline;color:#CC0000;font-weight:bold;">G.N.Heo</div> GwangNam like your image
						<hr style="border:none;border-bottom:1px solid #777777;" />
						
						<img src="images/defaultProfile.jpg" style="float:left;width:24px;margin-right:5px;" />
						<div style="display:inline;color:#CC0000;font-weight:bold;">J.H.Koo</div> JaHoon like your post
						<hr style="border:none;border-bottom:1px solid #777777;" />
						
						<img src="images/defaultProfile.jpg" style="float:left;width:24px;margin-right:5px;" />
						<div style="display:inline;color:#CC0000;font-weight:bold;">ì´ë§ë</div> ë§ëëì´ ë¹ì ì ì¬ì§ì ì¢ìí©ëë¤.
						<hr style="border:none;border-bottom:1px solid #777777;" />
					</span>
				</a>
			</li>
		</ul>
		
	</footer>
	</div>
<!-- Footer END -->