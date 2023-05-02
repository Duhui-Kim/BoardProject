package com.ssafy.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.board.model.dao.UserDao;
import com.ssafy.board.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public List<User> selectAll() {
		return userDao.selectAll();
	}

	@Override
	public User selectById(String id) {
		return userDao.selectById(id);
	}

	@Override
	@Transactional
	public void insert(User user) {
		userDao.insertUser(user);
	}

	@Override
	@Transactional
	public void delete(String id) {
		userDao.deleteUser(id);
	}

}
