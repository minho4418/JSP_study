<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	if ('<%=request.getParameter("msg")%>' != 'null' )
		alert('<%=request.getParameter("msg")%>');
	
	function addCheck() {
		var content = frmAdd.content;
		console.log(content);
		console.log(content.value);
		if (content.value == ''){
			alert('내용을 입력해 주세요');
			content.focus();
		}
		
	}
	
</script>
</head>
<body>
	<%@include file="../header.jsp" %>
	<h2>게시물 등록</h2>
	<form name ="frmAdd" action="/jsp02_board/add.board" method="post">
		<table>
			<tr>
				<th>작성자</th>
				<td> <input type="text" name="writer"> </td>
			</tr>
			<tr>
				<th>제목</th>
				<td> <input type="text" name="subject"> </td>
			</tr>	
			<tr>
				<th>내용</th>
				<td> <textarea name ="content" rows="5" cols="25"></textarea> </td>
			</tr>	
			<tr>
				<td colspan="2" align="center"> 
					<button onclick="addCheck()">저장</button>
					<button type="reset">취소</button>
				</td>
			</tr>
		</table>
		
	</form>
</body>
</html>