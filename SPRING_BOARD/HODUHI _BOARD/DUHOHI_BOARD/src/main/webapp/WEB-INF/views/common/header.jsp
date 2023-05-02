<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
	 <link href="${pageContext.request.contextPath }/resources/css/style.css" rel="stylesheet"  />
	 <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700;900&display=swap" rel="stylesheet">
</head>
<body>
	<a href="/mvc">		
		<img width="100%" src="${pageContext.request.contextPath }/resources/image/background.png">
	</a>		 
	<div class="header-loginarea">	
		<br>
		<c:if test="${empty loginUser}">
			<a href="login" class="btn btn-color-3">로그인</a>
			<a href="signup" class="btn btn-color-4">회원가입</a>
		</c:if>
		<c:if test="${not empty loginUser}">
			<span class="header-loginarea-text-name">${loginUser.name}</span> <span class="header-loginarea-text">&nbsp;님 반갑습니다.&nbsp;&nbsp;</span>
			<c:if test="${loginUser.authority != '관리자' }">
				<a href="myPage" class="btn btn-color-2">마이페이지</a>
				<a href="logout" class="btn btn-color-1">로그아웃</a>
			</c:if>	
			<c:if test="${loginUser.authority == '관리자' }">
				<span class="header-loginarea-text" style="color: red; border: thick;">[관리자 계정]</span>
				<a href="users" class="btn btn-color-2">회원관리</a>
				<a href="logout" class="btn btn-color-1">로그아웃</a>
			</c:if>
		</c:if>
	</div>
	<hr>
	<script src="https://kit.fontawesome.com/d0d7c038e6.js" crossorigin="anonymous"></script>
	<script>
		let msg = "${msg}";
		if(msg) {
			alert(msg);
		}
	</script>	
</body>
