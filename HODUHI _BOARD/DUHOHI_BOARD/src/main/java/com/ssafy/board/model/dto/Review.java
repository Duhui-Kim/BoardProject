package com.ssafy.board.model.dto;

public class Review {
	private int reviewId;
	private int boardId;
	private String writer;
	private String content;
	private String regDate;

	public Review() {
		super();
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Review [boardId=" + boardId + ", reviewId=" + reviewId + ", writer=" + writer + ", content=" + content
				+ ", regDate=" + regDate + "]";
	}

}
