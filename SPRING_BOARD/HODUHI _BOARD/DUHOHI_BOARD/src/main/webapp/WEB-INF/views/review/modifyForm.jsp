<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<div class="container">
	<br>
	<div class="reivew-write-area">
		<fieldset>
			<form action="modifyReview" method="POST">
				<input type="hidden" name="reviewId" value="${modifyReview.reviewId }">
				<input type="hidden" name="boardId" value="${board.id }">
				<div class="review-write">
					<div class="review-write-header">
						<label for="content" class="form-label review-write-header-content">내용</label>
						<div class="modify-review-btns">
							<input class="btn btn-color-3" type="submit" value="댓글 수정">
							<a href="detail?id=${board.id }&notModify=true" class="btn btn-color-2">취소</a>

						</div>
					</div>
					<textarea class="review-write-content form-control" id="content" name="content" rows="3" maxlength="90">${modifyReview.content }</textarea>
				</div>
			</form>
		</fieldset>
	</div>

	<br><br>
</div>