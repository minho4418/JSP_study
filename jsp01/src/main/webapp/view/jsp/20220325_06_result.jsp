<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>사각형의 넓이 결과창</h2>
	<%
		//형변환:문자열=>정수
		
		String width = request.getParameter("width");
		int width2 = Integer.parseInt(width);
		
		String height = request.getParameter("height");
		int height2 = Integer.parseInt(height);
	%>
	사각형의 넓이 : <%=width2*height2 %>
</body>
</html>