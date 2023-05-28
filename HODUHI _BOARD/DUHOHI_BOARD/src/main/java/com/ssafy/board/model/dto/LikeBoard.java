package com.ssafy.board.model.dto;

public class LikeBoard {
	private int likeId;
	private int boardId;
	private String userId;

	public LikeBoard() {
	}

	public LikeBoard(int boardId, String userId) {
		this.boardId = boardId;
		this.userId = userId;
	}

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "LikeBoard [likeId=" + likeId + ", boardId=" + boardId + ", userId=" + userId + "]";
	}

}
