<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<%@ include file="../common/bootstrap.jsp"%>
</head>
<body>	
	<!-- infoarea -->
	<div class="container">
		<%@ include file="../common/header.jsp"%>
		<h2 class="myPage-name">마이페이지</h2>
		<br>
		<br>
		<div class="mypage-infoarea-title">
			▶&nbsp;<span class="myPage-like-username">${loginUser.name}</span> <span class="myPage-like-text">&nbsp;님의 정보</span>
		</div>
		<div class="myPage-info">
			<div class="myPage-memberimg col-2">
				<p class="myPage-memberInfo-category">프로필 사진</p>
				<c:if test="${not empty loginUser.img}">
					<img id="profile"
						src="${pageContext.servletContext.contextPath}/resources/image/${loginUser.img }">
				</c:if>
				<c:if test="${empty loginUser.img}">					 
					<img class="myPage-memberimg-default" src="resources/image/defaultprofile.png">
				</c:if>
			</div>
			<div class="myPage-memberlv col-5">
				<p class="myPage-memberInfo-category">회원 등급</p>
				<div class="card">
					<div class="card-body">
					  <h5 class="card-title">${loginUser.name } 님은 ${loginUser.authority } 입니다.</h5>
					  <!-- <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6> -->
					  <span class="card-text">작성 게시글 수 : ${boardCnt }</span><br>
					  <span class="card-text">작성 덧글 수 : ${reviewCnt }</span><br>
					  <span class="card-text">찜한 게시글 수 : ${likeBoard.size()} </span><br>
					</div>
				  </div>
				
			</div>
			<div class="myPage-memberinfo col-5">				
				<p class="myPage-memberInfo-category">회원 정보</p>
				<table class="myPage-memberinfo-table table table-borderless">
					<tbody>
						<tr>
							<th>아이디</th>
							<td>${loginUser.id }</td>
						</tr>
						<tr>
							<th>이름</th>
							<td>${loginUser.name }</td>
						</tr>
						<tr>
							<th>전공</th>
							<td>${loginUser.curriculumName }</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
			<!-- 찜 목록 -->
		<c:if test="${likeBoard.size() > 0}">
			<hr>
			▶&nbsp;<span class="myPage-like-username">${loginUser.name}</span> <span class="myPage-like-text"> &nbsp;님이 좋아요를 누른 게시글</span>
			<table class="table text-center myPage-likeboardlist">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>글쓰니</th>
					<th>좋아요 취소</th>
				</tr>
				<c:forEach items="${likeBoard}" var="board">
					<tr>
						<td>${board.id}</td>
						<td><a href="detail?id=${board.id}">${board.title}</a></td>
						<td>${board.writer}</td>
						<td><a href="mpDislikeBoard?boardId=${board.id }">좋아요 취소</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>

	<!-- btn -->
	<div class="container myPage-btnarea">
		<a href="withdraw?id=${loginUser.id }" class="myPage-withdrawal btn btn-outline-danger">회원탈퇴</a>
	</div>
</body>
</html>