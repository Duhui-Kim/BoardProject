<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<%@ include file="../common/bootstrap.jsp" %>

</head>
<body>
	<div class="container">
		<%@ include file="../common/header.jsp" %>
		<h2>회원가입</h2>
		<form action="signup" method="POST" enctype="multipart/form-data">
			<div class="mb-3">
				<label for="id" class="form-label">ID</label>
				<input type="text" class="form-control" id="id" name="id" maxlength="35">
			</div>
			<div class="mb-3">
				<label for="password" class="form-label">PW</label>
				<input type="password" class="form-control" id="password" name="password" maxlength="15">
			</div>
			<div class="mb-3">
				<label for="name" class="form-label">이름</label>
				<input type="text" class="form-control" id="name" name="name" maxlength="35">
			</div>
			<div class="mb-3">
				<label for="curriculumName" class="form-label">트랙</label>
				<select name="curriculumName" class="form-select" id="curriculumName">
					<option value="Python">Python</option>
					<option value="Java">Java</option>
					<option value="Embedded">Embedded</option>
					<option value="Mobile">Mobile</option>
				</select>
			</div>
			<div class="mb-1">
				<label for="uploadFile">사진 등록</label>
				<input type="file" accept="image/*" name="uploadFile" id="uploadFile" class="form-control">
			</div>
			<div class="signup-btn">
				<button class="btn btn-color-3">회원가입</button>
			</div>
		</form>
	
	</div>
</body>
</html>