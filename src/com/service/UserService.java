package com.service;


import com.po.User;

public interface UserService {
	public User selectUserByUsername(User user);
	public void regist(User user);
	public User login(User user);
}
