<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
		if ('${param.msg}' != ''){
			alert('${param.msg}');
		}
	
		//파싱후 db저장
		function pasingSave(e) {
			e.preventDefault(); //기본이벤트 삭제
			frmFinedust.action = '${path}/dbsave.finedust';
			frmFinedust.submit();
		}
		//조회
		//조회
		function listCheck(e) {
			const districtName = frmFinedust.districtName;
			console.log(districtName);
			if(districtName.value != ''){
				e.preventDefault(); //기본이벤트 삭제
				frmFinedust.action = '${path}/detail.finedust';
				frmFinedust.submit();
			}else{
				e.preventDefault(); //기본이벤트 삭제
				frmFinedust.action = '${path}/list.finedust';
				frmFinedust.submit();
			}
			
			
		}
	
	
	</script>
</head>
<body>
	<h2>미세먼지</h2>
	<%-- ${dustlist} --%>
	<form name="frmFinedust"  action="${path}/detail.Finedust">
		지역 <input type="text" name="districtName" value="${param.districtName}">
		<button onclick="listCheck(event)" >조회</button>	
		<button onclick="pasingSave(event)">파싱후db저장</button>	
	</form>
	<table border="1">
		<tr>
			<th>일련번호</th>
			<th>지역명</th>
			<th>권역명</th>
			<th>경보단계</th>
			<th>발령일</th>
		</tr>
		<c:forEach var="finedust" items="${dust}">
			<tr>
				<td>${finedust.sn}</td>
				<td>${finedust.districtName}</td>
				<td>${finedust.moveName}</td>
				<td>${finedust.issueGbn}</td>
				<td>${finedust.dataDate}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>