<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
   "http://www.w3.org/TR/html4/strict.dtd">

<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>elFinder</title>
	<!--
	<script type='text/javascript' src='http://getfirebug.com/releases/lite/1.2/firebug-lite-compressed.js'></script>
	-->
		<link rel="stylesheet" type="text/css" media="screen" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/themes/smoothness/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="css/elfinder/elfinder.min.css" />

	<script src="js/jquery.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/jquery-ui.min.js"></script>
	<script src="js/elfinder/elfinder.min.js"></script>
	<!--
	<script src="js/i18n/elfinder.ru.js" type="text/javascript" charset="utf-8"></script>
	-->

	<style type="text/css">
		#close, #open, #dock, #undock {
			width: 100px;
			position:relative;
			display: -moz-inline-stack;
			display: inline-block;
			vertical-align: top;
			zoom: 1;
			*display: inline;
			margin:0 3px 3px 0;
			padding:1px 0;
			text-align:center;
			border:1px solid #ccc;
			background-color:#eee;
			margin:1em .5em;
			padding:.3em .7em;
			border-radius:5px; 
			-moz-border-radius:5px; 
			-webkit-border-radius:5px;
			cursor:pointer;
		}
	</style>
	

	<script type="text/javascript" charset="utf-8">
		$().ready(function() {
			console.log('ready');
			var f = $('#finder').elfinder({
				url : 'connector',
				lang : 'en',
				docked : true

				// dialog : {
				// 	title : 'File manager',
				// 	height : 500
				// }

				// Callback example
				//editorCallback : function(url) {
				//	if (window.console && window.console.log) {
				//		window.console.log(url);
				//	} else {
				//		alert(url);
				//	}
				//},
				//closeOnEditorCallback : true
			});
			// window.console.log(f)
			$('#close,#open,#dock,#undock').click(function() {
				$('#finder').elfinder($(this).attr('id'));
			});
			
		});
	</script>

</head>
<body>
	<div id="open">open</div><div id="close">close</div><div id="dock">dock</div><div id="undock">undock</div>
	<div id="finder">finder</div>

	

</body>
</html>