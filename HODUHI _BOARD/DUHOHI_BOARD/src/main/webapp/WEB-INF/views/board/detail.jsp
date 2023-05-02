<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
<%@ include file="../common/bootstrap.jsp"%>
</head>
<body>
	<div class="container">
		<%@ include file="../common/header.jsp"%>
		<div class="detail-board-header">
			<div class="detail-board-header-titlebtns">
				<h2>${board.title}</h2>
			</div>
			<div class="detail-board-header-text">
				<span class="col-2 text-muted detail-board-header-text-writer">${board.writer}</span>
				<span class="col-4 text-muted detail-board-header-text-time">
					${board.regDate}
					<br>
					조회 ${board.viewCnt }&nbsp;댓글 ${reviews.size() }
				</span>
			</div>
		</div>
		<hr>	
		<div class="detail-board-content">
			<p class="detail-board-content-content">${board.content}</p>
      <br>

			<div class="detail-board-content-files">
				<c:choose>
				<c:when test="${empty files }">					
				  <i class="fa-regular fa-file"></i><span>&nbsp;첨부파일 없음</span>
				</c:when>
				<c:otherwise>
          <span>< 첨부파일 목록 ></span>
					<c:forEach items="${files }" var="file" varStatus="var">
						<div class="filelist">              
              <span class="filelist-text"><i class="fa-regular fa-file"></i>&nbsp;${var.count }번 파일 : ${file.orgFileName } </span>
              <a href="download?fileId=${file.fileId }" class="filelist-text-downloadbtn btn btn-color-3">다운로드</a> 
            </div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
    <hr>
    <div class="detail-under">		
      <div class="detail-review-cnt">
        &nbsp;<i class="fa-solid fa-comment fa-2x" style="color: #000000;"></i>&nbsp;&nbsp;${reviews.size()} 개
      </div>
			<div class="detail-boardbtns">    
        <c:choose>
          <c:when test="${likeId == 0 }">
				    <a href="likeBoard?boardId=${board.id }"><i class="fa-regular fa-heart fa-2x" style="color: #ff0000;"></i></a>
				  </c:when>
				  <c:otherwise>
				   <a href="dislikeBoard?boardId=${board.id }"><i class="fa-solid fa-heart fa-2x" style="color: #ff0000;"></i></a>
				  </c:otherwise>
			  </c:choose>  
        <c:if test="${loginUser.name == board.writer or loginUser.authority == '관리자'}">
        <a href="delete?id=${board.id}&writer=${board.writer }" class="btn btn-color-1">삭제</a>
        <a href="updateform?id=${board.id}&writer=${board.writer }" class="btn btn-color-2">수정</a>
        </c:if>
        <a href="list" class="btn btn-color-4">목록</a>
			</div>
		</div>
	</div> 
  <!-- 컨테이너 끗! -->
	<c:choose>
		<c:when test="${empty modifyReview }">
			<%@ include file="../review/review.jsp"%>
		</c:when>
		<c:otherwise>
			<%@ include file="../review/modifyForm.jsp"%>
		</c:otherwise>
	</c:choose>
	<br><br>
</body>
</html>