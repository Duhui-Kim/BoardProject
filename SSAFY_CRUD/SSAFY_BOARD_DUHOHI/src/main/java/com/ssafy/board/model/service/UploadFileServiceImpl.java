package com.ssafy.board.model.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.board.model.dao.UploadFileDao;
import com.ssafy.board.model.dto.Board;
import com.ssafy.board.model.dto.UploadFile;

@Service
public class UploadFileServiceImpl implements UploadFileService {

	@Autowired
	private ResourceLoader resLoader;
	
	@Autowired
	private UploadFileDao uploadFileDao;
	
	@Override
	public List<UploadFile> readFile(int boardId) {
		return uploadFileDao.select(boardId);
	}

	@Override
	@Transactional
	public void uploadFile(Board board, MultipartFile file) throws IOException {
		Resource res = resLoader.getResource("/resources/upload");
		
		if(!res.getFile().exists()) 
			res.getFile().mkdir();
		
		UploadFile uploadFile = new UploadFile();
		
		uploadFile.setBoardId(board.getId());

		// UUID에 확장자만 추가하기
		int idx = file.getOriginalFilename().lastIndexOf('.');
		uploadFile.setFileName(UUID.randomUUID() + "_" + file.getOriginalFilename().substring(idx));
		uploadFile.setOrgFileName(file.getOriginalFilename());
		
		// file을 저장하고, db에 uploadFile도 저장함
		file.transferTo(new File(res.getFile(), uploadFile.getFileName()));		
		uploadFileDao.insert(uploadFile);
	}

	@Override
	@Transactional
	public void removeFile(int fileId) {
		uploadFileDao.deleteById(fileId);
	}

	@Override
	public UploadFile searchById(int fileId) {
		return uploadFileDao.selectById(fileId);
	}
}
