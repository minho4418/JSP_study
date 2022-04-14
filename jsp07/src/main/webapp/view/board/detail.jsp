<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function removeCheck() {
		const result = confirm('정말 삭제하시겠습니까?');
		if (result){
			location.href = '${path}/board/remove?bnum=${board.bnum}';
		}
	}

</script>
</head>
<body>
	<h2>상세조회</h2>
<%-- 	${board}
	<hr>
	${bflist} --%>
	
	<table border="1">
		<tr>
			<th>번호</th>
			<td>${board.bnum}</td>
		</tr>
		<tr>
			<th>작성자id</th>
			<td>${board.userid}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${board.subject}</td>
		</tr>		
		<tr>
			<th>내용</th>
			<td> <pre>${board.content}</pre> </td>
		</tr>	
		<tr>
			<th>파일</th>
			<td>
				<c:forEach var="boardfile" items="${bflist}">
					${boardfile.filename}<br>
					
				</c:forEach>
			
			</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${board.readcnt}</td>
		</tr>
		<tr>
			<th>등록일자</th>
			<td><fmt:formatDate value="${board.regidate}" pattern="yyyy-MM-dd HH:mm:ss"/>  </td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button onclick="location.href='${path}/board/modiform?bnum=${board.bnum}'">수정폼</button>
				<button onclick="removeCheck()">삭제</button>
				<button 
				onclick="location.href='${path}/view/board/reply.jsp?bnum=${board.bnum}&restep=0&relevel=0'">
				댓글</button>
				<button onclick="location.href='${path}/board/list'">리스트</button>
			</td>
		</tr>	
	</table>
	<hr>
	<%-- ${rlist} --%>
	
	<c:forEach var="reply" items="${rlist}">
		${reply.rnum} <br>
		${reply.content} <br>
		<fmt:formatDate value="${reply.regidate}" pattern="yyyy-MM-dd HH:mm:ss"/>  <br>
		<button onclick="${path}/view/">댓글</button>
		<button onclick="">수정</button>
		<button onclick="">삭제</button>
		<hr>
	</c:forEach>
	
	
	
	
	
</body>
</html>