<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<div class="container">
	<br>
	<div class="review-area">
		<c:if test="${not empty reviews}">
			<c:forEach items="${reviews}" var="review" varStatus="var">
				<p>${review.writer}</p>
				<p>${review.content}</p>
				<div class="review-area-info">
					<span class="text-muted">${review.regDate}</span>
					<c:if test="${loginUser.name == review.writer}">
						<div class="review-area-btns">
							<a href="modifyReview?reviewId=${review.reviewId}&boardId=${board.id}">수정</a>
							<a href="deleteReview?reviewId=${review.reviewId }&boardId=${board.id}">삭제</a>
						</div>
					</c:if>
				</div>
				<hr>
			</c:forEach>
		</c:if>
	</div>
	<br>
	<p class="review-write-title">댓글 등록</p>
	<div class="reivew-write-area">
		<fieldset>
			<form action="registReview" method="POST">
				<input type="hidden" name="boardId" value="${board.id }">
				<div class="review-write">
					<div class="review-write-header">
						<label for="content" class="form-label review-write-header-content">내용</label>
						<input type="submit" class="review-regist-btn btn btn-color-3" value="댓글 등록">
					</div>
					<textarea class="review-write-content form-control" id="content" name="content" placeholder="댓글을 입력해주세요" rows="3" maxlength="90"></textarea>
				</div>
			</form>
		</fieldset>
	</div>
	<br><br>
</div>