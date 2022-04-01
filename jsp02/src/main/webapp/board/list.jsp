<%@page import="dto.Board"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	if ('<%=request.getParameter("msg")%>' != 'null' )
		alert('<%=request.getParameter("msg")%>');
</script>
</head>
<body>
	<%
		//findkey null일때 처리
		String findkey = request.getParameter("findkey");
		String findvalue = request.getParameter("findvalue");
		if (findkey == null) findkey = "";
		if (findvalue == null) findvalue = "";
	
	%>
	<!-- 지시자 include를 이용해서 jsp를 삽입 -->
	<%@include file="../header.jsp" %>
	<h2>게시물 리스트</h2>
	<form action="/jsp02_board/list.board">
		<select name="findkey">
			<option value="writer" <%out.print(findkey.equals("writer")?"selected":"");%>>작성자</option>
			<option value="subject" <%out.print(findkey.equals("subject")?"selected":"");%>>제 목</option>
			<option value="content" <%out.print(findkey.equals("content")?"selected":"");%>>내 용</option>
		</select>
		<input type="text" name="findvalue" value="<%=findvalue%>">
		<button>조회</button>
		<button type="button" onclick ="location.href='/jsp02_board/board/list.jsp'">초기화</button>
	</form>
	<hr>
	<!-- 조회리스트 -->
	<form action="/jsp02_board/remove.board">
		<table border = "1">
			<tr>
				<th>순번</th>
				<th>작성자</th>
				<th>제목</th>
				<th>내용</th>
				<th>등록일자</th>
				<th>수정일자</th>
				<th><button>삭제</button></th>
			</tr>
	
		
		<%
			List<Board> blist = (List<Board>)request.getAttribute("blist");
			if (blist != null){
				for(Board board:blist){
		%>			
					<tr>
						<td><%=board.getSeq()%></td>
						<td><%=board.getWriter()%></td>
						<td><a href="/jsp02_board/modiform.board?seq=<%=board.getSeq()%>"><%=board.getSubject()%></a></td>
						<td><pre><%=board.getContent()%></pre></td>
						<td><%=board.getRegidate()%></td>
						<td><%=board.getModidate()%></td>
						<td> <input type="checkbox" name="removes" value="<%=board.getSeq()%>"></td>
					</tr>
		<%		}
			}
		%>
		
		</table>
	</form>
</body>
</html>