<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/table.css" />
<title>Display</title>
</head>
<body>

<h3>Select Product Table Result : XXX row(s) selected</h3>
<c:if test="${select !=null}">
<table>
	<thead>
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Price</th>
		<th>Make</th>
		<th>Expire</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="bean" items="${select}">
	<tr>
	<c:url var="path" value="/pages/product.jsp">
		<c:param name="id" value="${bean.id}"></c:param>
		<c:param name="name" value="${bean.name}"></c:param>
		<c:param name="price" value="${bean.price}"></c:param>
		<c:param name="make" value="${bean.make}"></c:param>
		<c:param name="expire" value="${bean.expire}"></c:param>
	</c:url>
	
		<td><a href="${path}">${bean.id}</a></td>
		<td>${bean.name}</td>
		<td>${bean.price}</td>
		<td>${bean.make}</td>
		<td>${bean.expire}</td>
	</tr>
	</c:forEach>
	</tbody>
</table>
</c:if>
<h3><a href="/beans/pages/product.jsp">Product Table</a></h3>
</body>
</html>