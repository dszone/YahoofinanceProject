<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello Page</title>
</head>
<body>
<h1><font color="blue">${ownershipInfo.message}</font></h1>
<table width="200" border="1">
	<tr>
		<th>Stock Name</th>
		<th>Quantity</th>
	</tr>
	<c:forEach var="ownership" items="${ownershipInfo.ownerships}">
		<tr>
			<td>${ownership.stock.stockName}</td>
			<td>${ownership.quantity}</td>
		</tr>
	</c:forEach>
</table>
<a href="<c:url value='/j_spring_security_logout'/>">Logout</a>
</body>
</html>