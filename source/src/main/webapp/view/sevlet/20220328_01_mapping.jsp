<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>서블릿 매핑</h2>
	<!-- contextpath + webservlet의 매핑정보 -->
	<form action="/jsp01/j20220328" method="get">
		이름 : <input type="text" name="name"><br>
		나이 : <input type="number" name="age"><br>
		
		<button>전달</button>
	</form>
</body>
</html>