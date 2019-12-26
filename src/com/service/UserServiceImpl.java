package com.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.po.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public User selectUserByUsername(User user) {	
		return userDao.selectUserByUsername(user);
	}
	@Override
	public void regist(User user) {
		userDao.regist(user);
	}
	@Override
	public User login(User user) {
		return userDao.login(user);
	}
	@Override
	public User adminlogin(User user) {
		return userDao.adminlogin(user);
	}
	@Override
	public List<User> getUserList() {
		return userDao.getUserList();
	}
}
