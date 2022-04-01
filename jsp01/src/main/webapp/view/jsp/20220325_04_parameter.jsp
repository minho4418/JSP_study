<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>요청 파라메터 정보</h2>
	<!-- 	전송 메소드 : get (데이터전송을 url에 담아서)
				  post (request내부 객체에 담아서)
 -->
	<form action="">
		이름 : <input type="text" name="name"><br>
		나이 : <input type="number" name="age"><br>
		<!-- form submit 기능 action값: url -->
		<!-- from안의 name의 정보를 request(get(url에 포함), post(객체))객체에 담아서 보낸다 -->
		<button>전송</button>
	</form>
	
	<%
		//스트립틀릿(servlet)
		String name = request.getParameter("name");
		String age = request.getParameter("age");
	%>
	이름 : <%=name %><br>
	나이 : <%=age  %><br>
</body>
</html>