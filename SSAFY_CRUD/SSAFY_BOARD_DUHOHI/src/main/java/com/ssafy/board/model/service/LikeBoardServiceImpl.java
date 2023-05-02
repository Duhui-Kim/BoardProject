package com.ssafy.board.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.board.model.dao.LikeBoardDao;
import com.ssafy.board.model.dto.LikeBoard;

@Service
public class LikeBoardServiceImpl implements LikeBoardService {
	
	@Autowired
	private LikeBoardDao likeBoardDao;

	@Override
	@Transactional
	public void likeBoard(LikeBoard likeBoard) {
		likeBoardDao.insertLike(likeBoard);
	}

	@Override
	@Transactional
	public void dislikeBoard(LikeBoard likeBoard) {
		likeBoardDao.deleteLike(likeBoard);
	}

	@Override
	public int getLikeId(LikeBoard likeBoard) {
		// 좋아요가 없으면 0 반환, 있으면 해당 좋아요의 아이디 반환
		if(likeBoardDao.selectCnt(likeBoard) == 0) {
			return 0;
		} else {
			return likeBoardDao.selectLike(likeBoard);			
		}
	}
}
