package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.dto.Review;

public interface ReviewService {
	public List<Review> getAllReviews(int boardId);

	public Review getReview(int reviewId);
	
	public void removeReview(int reviewId);

	public void registReview(Review review);

	public void modifyReview(Review review);

	public int registCnt(String name);
}
