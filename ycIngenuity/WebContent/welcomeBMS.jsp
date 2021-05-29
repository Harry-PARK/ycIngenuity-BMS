<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Welcome BMS</title>
<link rel="stylesheet" href="css/commonCSS.css">
<link rel="stylesheet" href="css/welcomeBMS.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>

<body>
<div class="topBanner">	
</div>

<div id="main">

	<section id="switchPanel">
		<ul id="switchList">
		
		<li>
		<div class="switchBox"> <!-- switch box -->
			<img class="switchImg" src="resources/image/off.jpg" alt="bulb image">
			
			<div class="switchInfo">
			<p>#2</p>
			<p>LAB3 (I203)</p>
			<p>[Industry]</p>
			<p class="lastup">last updated : 1 min ago..</p>
			<label class="switch"><input type="checkbox"><span class="slider round"></span></label>
			
			</div>
		</div>
		</li>
		
		
		</ul>
	</section>
	
	<div id="monitor"> <!-- monitor -->
	</div>
</div>
</body>

<script src="js/welcomeBMSLoad.js"></script>
<script src="js/commonJS.js"></script>
</html>