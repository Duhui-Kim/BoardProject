<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 등록</title>
<%@ include file="../common/bootstrap.jsp" %>
</head>
<body>
	<div class="container">
		<%@ include file="../common/header.jsp" %>
		<h2>글작성</h2>
		<form action="write" method="POST" enctype="multipart/form-data">
			<input type="hidden" name="writer" value="${loginUser.name }">
			<div class="mb-3">
				<label for="title" class="form-label">글제목</label>
				<input type="text" class="form-control" id="title" name="title" maxlength="35">
			</div>
			<div class="mb-3">
				<label for="content" class="form-label">글내용</label>
				<textarea class="form-control"  rows="10" cols="10" id="content" name="content"></textarea>
			</div>
			<div class="mb-1">
				<label for="files">첨부파일</label>
				<input type="file" name="files" id="files" multiple="multiple" class="form-control">
			</div>
			<div class="writeform-btn">
				<button class="regist-btn btn btn-color-3">등록</button>
			</div>
		</form>
	
	</div>
	<br><br>
</body>
</html>