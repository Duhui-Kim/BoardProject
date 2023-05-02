<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<%@ include file="../common/bootstrap.jsp" %>
</head>
<body>
	<div class="container">
		<%@ include file="../common/header.jsp" %>
		<h2>유저 목록</h2>
		<hr>
		
		<div>
			<table class="table text-center">
				<tr>
					<th>ID</th>
					<th>NAME</th>
					<th>CURRICULUM</th>
					<th>AUTHORITY</th>
					<th>PICTURE</th>
					<th>DELETE</th>
				</tr>
				
				<!-- 관리자를 제외하고 보여주기 -->
				<c:forEach items="${userList}" var="user">
					<c:if test="${user.authority != '관리자' }">
						<tr>
							<td>${user.id}</td>
							<td>${user.name}</td>
							<td>${user.curriculumName}</td>
							<td>${user.authority }</td>
							<c:if test="${not empty user.img }">
								<td><img width="250px" src="${pageContext.request.contextPath }/resources/image/${user.img }"></td>
							</c:if>
							<c:if test="${empty user.img }">
								<td>이미지 없음</td>
							</c:if>
							<td><a href="forced?id=${user.id }" class="btn btn-outline-danger">강제탈퇴</a></td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
			
			<div class="d-flex justify-content-end">
				<a class="btn btn-color-2 " href="list">홈으로</a>
			</div>
		</div>
		<br><br>
	</div>
</body>
</html>