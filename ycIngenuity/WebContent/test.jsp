<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = 
	"ycIngenuity.bms.resourceUtil.*"
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
AccountResourceManager tempA = BMS_Container.getAccountRsourceManager();
//String a = tempA.getResource().get(0).getEmail(); 
%>
<%tempA.save_resource(); %>

</body>
</html>