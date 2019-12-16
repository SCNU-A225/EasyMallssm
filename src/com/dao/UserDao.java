package com.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.po.User;
@Repository("userDao")
@Mapper
public interface UserDao {
	
	public User selectUserByUsername(User user);
	
	public void regist(User user); 
	
	public User login(User user);
		
}
