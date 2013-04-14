<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지4</title>
<script type="text/javascript" src="js/jquery.1.1.2.js"></script>
<script type="text/javascript" src="js/interface.js"></script>

<!--[if lt IE 7]>
 <style type="text/css">
 div, img { behavior: url(iepngfix.htc) }
 </style>
<![endif]-->

<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="dock" id="dock">
  <div class="dock-container">
  <a class="dock-item" href="#"><img src="images/home.png" alt="home" /><span>iamapark89@me2day</span></a> 
  <a class="dock-item" href="#"><img src="images/email.png" alt="contact" /><span>Messages</span></a> 
  <a class="dock-item" href="#"><img src="images/portfolio.png" alt="portfolio" /><span>BookMarks</span></a> 
  <a class="dock-item" href="#"><img src="images/music.png" alt="music" /><span>Friends</span></a> 
</div>
</div>
<script type="text/javascript">
	
	$(document).ready(
		function()
		{
			$('#dock').Fisheye(
				{
					maxWidth: 50,
					items: 'a',
					itemsText: 'span',
					container: '.dock-container',
					itemWidth: 40,
					proximity: 90,
					halign : 'center'
				}
			)
		}
	);

</script>
</body>
</html>