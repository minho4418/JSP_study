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
			frmCountry.action = '${path}/dbsave.country';
			frmCountry.submit();
		}
		
		//조회
		function listCheck(e) {
			e.preventDefault(); //기본이벤트 삭제
			frmCountry.action = '${path}/list.country';
			frmCountry.submit();
		}
	
	
	</script>	 
</head>
<body>
	<%-- ${isolist} --%>
	

	<h2>외교부_국가.지역별 최신안전소식(코로나관련)</h2>
	<form name="frmCountry">
		국가
		<select name="iso">
			<c:forEach var="iso" items="${isolist}">
				<option value="${iso.CODE}" ${param.iso==iso.CODE?'selected':''}>${iso.NAME}</option>
			</c:forEach>
		</select>
		<button onclick="listCheck(event)" >조회</button>
		<button onclick="pasingSave(event)">파싱후db저장</button>
	</form>
	<%-- ${clist} --%>
	<table border="1">
		<tr>
			<th>안전공지ID</th>
			<th>대륙코드</th>
			<th>대륙명</th>
			<th>ISO</th>
			<th>한글국가명</th>
			<th>제목</th>
			<th>파일갯수</th>
			<th>작성일</th>
		</tr>
		<c:forEach var="country" items="${clist}">
			<tr>
				<td>${country.sfty_notice_id}</td>
				<td>${country.continent_cd}</td>
				<td>${country.continent_nm}</td>
				<td>${country.country_iso_alp2}</td>
				<td>${country.country_nm}</td>
				<td><a href="${path}/detail.country?sfty_notice_id=${country.sfty_notice_id}">${country.title}</a></td>
				<td>${country.file_cnt}</td>
				<td>${country.wrt_dt}</td>
			</tr>
		</c:forEach>
	</table>
	

</body>
</html>