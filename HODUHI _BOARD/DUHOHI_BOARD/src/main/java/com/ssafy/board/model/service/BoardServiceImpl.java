package com.ssafy.board.model.service;

import java.nio.charset.CodingErrorAction;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.board.model.dao.BoardDao;
import com.ssafy.board.model.dao.LikeBoardDao;
import com.ssafy.board.model.dto.Board;
import com.ssafy.board.model.dto.SearchCondition;

@Service
public class BoardServiceImpl implements BoardService {
	
	private BoardDao boardDao;
	@Autowired
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	@Override
	public List<Board> getBoardList() {
		return boardDao.selectAll();
	}

	@Override
	public Board readBoard(int id) {
		boardDao.updateViewCnt(id);
		return boardDao.selectOne(id);
	}
	
	@Transactional
	@Override
	public void writeBoard(Board board) {
		boardDao.insertBoard(board);
	}

	@Transactional
	@Override
	public void removeBoard(int id) {
		boardDao.deleteBoard(id);
	}

	@Transactional
	@Override
	public void modifyBoard(Board board) {
		boardDao.updateBoard(board);
	}

	@Override
	public List<Board> search(SearchCondition searchCondition) {
		return boardDao.search(searchCondition);
	}

	@Override
	public int registCnt(String name) {
		return boardDao.selectCnt(name);
	}

	@Override
	public List<Board> getLikeBoard(String userId) {
		return boardDao.selectLikeBoard(userId);
	}

	@Override
	public List<Board> getBestBoards() {
		return boardDao.selectBestBoard();
	}
}
