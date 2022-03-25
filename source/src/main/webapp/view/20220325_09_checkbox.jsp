<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>사용가능한언어</h2>
	<form action="">
		<fieldset>
			<input type="checkbox" name="language" value="자바"> 자바
			<input type="checkbox" name="language" value="파이썬"> 파이썬
			<input type="checkbox" name="language" value="HTML5"> HTML5
			<input type="checkbox" name="language" value="JAVASCRIPT"> JAVASCRIPT
			<input type="checkbox" name="language" value="JSP"> JSP
		</fieldset>
		<button>선택</button>
	</form>
	당신이 선택한 언어는
	<%
	if(request.getParameterValues("language") != null){
		String[] langs = request.getParameterValues("language");
		for(String lang:langs){
	%>
	 <%=lang %>
	<%		
		}}
	%>
</body>
</html>