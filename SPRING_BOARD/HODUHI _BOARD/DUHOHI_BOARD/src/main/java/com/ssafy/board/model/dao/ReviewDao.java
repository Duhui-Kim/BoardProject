package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.Review;

public interface ReviewDao {
	public List<Review> selectAll(int boardId);
	
	public Review selectById(int reviewId);

	public void deleteReview(int reviewId);

	public void insertReview(Review review);
	
	public void updateReview(Review review);
	
	public int selectCnt(String name);
}
