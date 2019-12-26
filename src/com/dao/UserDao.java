package com.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.po.User;
@Repository("userDao")
@Mapper
public interface UserDao {
	
	public User selectUserByUsername(User user);
	
	public void regist(User user); 
	
	public User login(User user);
		
	public User adminlogin(User user);
	
	public List<User> getUserList();
}
