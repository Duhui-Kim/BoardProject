package com.ssafy.board.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.board.model.dto.Board;
import com.ssafy.board.model.dto.SearchCondition;
import com.ssafy.board.model.dto.UploadFile;

public interface UploadFileService {
	public List<UploadFile> readFile(int boardId); 
	
	public void uploadFile(Board board, MultipartFile file) throws IOException;
	
	public void removeFile(int fileId);
	
	public UploadFile searchById(int fileId);	
}
