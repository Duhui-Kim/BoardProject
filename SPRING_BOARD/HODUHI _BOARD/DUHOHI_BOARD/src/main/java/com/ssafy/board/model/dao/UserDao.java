package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.User;

public interface UserDao {
	public List<User> selectAll();
	
	public User selectById(String id);

	public void insertUser(User user);
	
	public void deleteUser(String id);
}
