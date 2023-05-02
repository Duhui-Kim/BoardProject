package com.ssafy.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.board.model.dao.ReviewDao;
import com.ssafy.board.model.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDao reviewDao;
	
	@Override
	public List<Review> getAllReviews(int boardId) {
		return reviewDao.selectAll(boardId);
	}

	@Override
	@Transactional
	public void removeReview(int reviewId) {
		reviewDao.deleteReview(reviewId);
	}

	@Override
	@Transactional
	public void registReview(Review review) {
		reviewDao.insertReview(review);
	}

	@Override
	@Transactional
	public void modifyReview(Review review) {
		reviewDao.updateReview(review);
	}

	@Override
	public Review getReview(int reviewId) {
		return reviewDao.selectById(reviewId);
	}

	@Override
	public int registCnt(String name) {
		return reviewDao.selectCnt(name);
	}

}
