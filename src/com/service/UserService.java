package com.service;


import java.util.List;

import com.po.User;

public interface UserService {
	public User selectUserByUsername(User user);
	public void regist(User user);
	public User login(User user);
	public User adminlogin(User user);
	public List<User> getUserList();
}
