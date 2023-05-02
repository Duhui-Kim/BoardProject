<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러 발생</title>
<%@ include file="../common/bootstrap.jsp"%>
</head>
<body>
	<div class="container">
		<%@ include file="../common/header.jsp"%>

		<h2>에러가 발생했습니다. 확인 후 다시 시도해주세요.</h2>
		<c:if test="${loginUser.authority == '관리자' }">
			${message }
		</c:if>
		<div class="d-flex justify-content-end">
			<a class="btn btn-outline-primary" href="/mvc">돌아가기</a>
		</div>
	</div>
</body>
</html>