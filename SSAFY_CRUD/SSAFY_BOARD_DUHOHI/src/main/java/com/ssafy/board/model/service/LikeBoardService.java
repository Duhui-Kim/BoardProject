package com.ssafy.board.model.service;

import com.ssafy.board.model.dto.LikeBoard;

public interface LikeBoardService {
	public void likeBoard(LikeBoard likeBoard);
	
	public void dislikeBoard(LikeBoard likeBoard);
	
	public int getLikeId(LikeBoard likeBoard);
}
