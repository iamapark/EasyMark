<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />

  <title>슬라이드 테스트</title>
  <link rel="stylesheet" href="css/superslide/superslide.css">
  <link rel="stylesheet" href="css/superslide/layout.css">
  <style>
  	html{
  	background-image:url('http://www.hdwallpapersbest.com/wp-content/uploads/2013/02/Mountain_wallpaper_005_1024.jpg');
  	}
  	
  </style>

  <script src="js/jquery.js"></script>
 	<script src="js/superslide/jquery.easing.1.3.js"></script>
 	<script src="js/superslide/jquery.superslide.js" type="text/javascript" charset="utf-8"></script>
 	<script src="js/superslide/application.js"></script>
</head>
<body>
  <ul id="connect">
    <li>
      kaka
    </li>
  </ul>

  <div id="slides">
    <ul class="slides-container">
      <li>
        <div class="container">
        	1
        </div>
      </li>
      <li>
        <div class="container">
        	2
        </div>
      </li>
      <li>
        <div class="container">
        	3
        </div>
      </li>
      <li>
        <div class="container">
        	4
        </div>
      </li>
      
    </ul>
    <nav class="slides-navigation">
      <a href="#" class="next">
        &gt;
      </a>
      <a href="#" class="prev">
        &lt;
      </a>
    </nav>
  </div>

</body>
</html>