<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>선택완료</h2>
	<%
		request.setCharacterEncoding("utf-8");
		String a = request.getParameter("job");
	%>
	<%= a %>
</body>
</html>