package com.ssafy.board.model.dto;

public class User {
	private String id;
	private String password;
	private String name;
	private String img;
	private String orgImg;
	private String curriculumName;
	private String authority;
	
	
	public User() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getOrgImg() {
		return orgImg;
	}
	public void setOrgImg(String orgImg) {
		this.orgImg = orgImg;
	}
	public String getCurriculumName() {
		return curriculumName;
	}
	public void setCurriculumName(String curriculumName) {
		this.curriculumName = curriculumName;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", name=" + name + ", img=" + img + ", orgImg=" + orgImg
				+ ", curriculumName=" + curriculumName + ", authority=" + authority + "]";
	}
	
	
}
