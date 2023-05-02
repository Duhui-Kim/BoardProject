package com.ssafy.board.model.dto;

public class UploadFile {
	private int fileId;
	private int boardId;
	private String fileName;
	private String orgFileName;
	
	
	public UploadFile() {
		super();
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getOrgFileName() {
		return orgFileName;
	}
	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}
	@Override
	public String toString() {
		return "File [fileId=" + fileId + ", boardId=" + boardId + ", fileName=" + fileName + ", orgFileName="
				+ orgFileName + "]";
	}
	
}
