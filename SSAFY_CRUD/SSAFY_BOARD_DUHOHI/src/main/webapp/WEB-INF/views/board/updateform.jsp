<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<%@ include file="../common/bootstrap.jsp"%>
</head>
<body>
	<div class="container">
		<%@ include file="../common/header.jsp"%>
		<h2>글수정</h2>
		<form action="update" method="POST" enctype="multipart/form-data">
			<input type="hidden" name="writer" value="${loginUser.name }">
			<input type="hidden" name="id" value="${board.id}">
			<div class="mb-3">
				<label for="title" class="form-label">글제목</label> <input type="text"
					class="form-control" id="title" name="title" value="${board.title}">
			</div>
			<div class="mb-3">
				<label for="content" class="form-label">글내용</label>
				<textarea class="form-control" rows="10" cols="10" id="content"
					name="content">${board.content}</textarea>
			</div>
			<label for="files">첨부파일</label>
			<input type="file" class="form-control" name="files" id="files" multiple="multiple"><br>

			<div class="updateform-filelist">
				<c:if test="${not empty files }">
					<c:forEach items="${files }" var="file" varStatus="var">
						<span><a href="deleteFile?fileId=${file.fileId }&boardId=${board.id }&writer=${board.writer}"
							class="btn btn-color-4">삭제</a>&nbsp;${var.count }번 파일 : ${file.orgFileName }</span><br>
						
					</c:forEach>
				</c:if>
			</div>
			<div class="mb-1">
			</div>
			<div class="update-btn">	
				<button class="btn btn-color-3">수정</button>
			</div>
		</form>
	</div>
	<br><br>
</body>
</html>