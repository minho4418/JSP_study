<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//msg  : 계열 메세지 
		String name = request.getParameter("name");
		String major = request.getParameter("major");
		System.out.println(name);
		System.out.println(major);
		String prefix = major.substring(0, 1);//문자열 추출
		System.out.println(prefix);
		//만약에 prefix가 "A"라면 공학계열
		// 'B'라면 자연계열
		String msg;
		if (prefix.equals("A")){
			msg = "공학계열";
		}else if (prefix.equals("B")){
			msg = "자연계열";
		}else{
			msg = "무 계열";
		}
		System.out.println(msg);
		//redirect이동
		//자바의 인코딩과  url인코딩이 다르다 
		name = URLEncoder.encode(name, "utf-8");
		response.sendRedirect("20220328_15_result.jsp?name="+name);
	
	%>
</body>
</html>