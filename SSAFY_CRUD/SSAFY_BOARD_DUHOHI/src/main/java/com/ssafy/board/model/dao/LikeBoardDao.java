package com.ssafy.board.model.dao;

import com.ssafy.board.model.dto.LikeBoard;

public interface LikeBoardDao {
	public void insertLike(LikeBoard likeBoard);

	public void deleteLike(LikeBoard likeBoard);

	public int selectLike(LikeBoard likeBoard);
	
	public int selectCnt(LikeBoard likeBoard);
}
