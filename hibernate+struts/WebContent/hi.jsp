<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/theNamespace/helloAction.action">
	Username: <input name="username" type="text" value="${param.username}"/>
	Password: <input name="password" type="text" value="${param.password}"/>
	
	<input type="submit" value="submit"/>

</form>

</body>
</html>