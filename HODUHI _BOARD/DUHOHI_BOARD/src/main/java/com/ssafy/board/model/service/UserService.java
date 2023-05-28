package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.dto.User;

public interface UserService {
	public List<User> selectAll();
	
	public User selectById(String id);
	
	public void insert(User user);
	
	public void delete(String id);
}
