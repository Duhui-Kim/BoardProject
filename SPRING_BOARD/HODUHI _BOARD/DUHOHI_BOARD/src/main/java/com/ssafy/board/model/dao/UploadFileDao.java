package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.UploadFile;

public interface UploadFileDao {
	public List<UploadFile> select(int boardId);
	
	public UploadFile selectById(int fileId);
	
	public void deleteById(int fileId);
	
	public void insert(UploadFile file);

}
