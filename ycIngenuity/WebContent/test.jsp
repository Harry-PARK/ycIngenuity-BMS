<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="ycIngenuity.bms.resourceUtil.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<p>test jsp</p>

	<%= BMS_Container.getRemoteLightManager().getResource().get(0).getDevice_id() %>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$.ajax({
		type: 'PUT',
		url:"/ycIngenuityBMS/restful/remotelight/lab1",
        data : {
        	"light":"1",
        	"email" : "device_lab1",
        	"pw" : "lab1",
        	"command" : "update"
        },
	}).done(function(data) {
	    alert(data);
	  });
	alert("??");
</script>
</html>