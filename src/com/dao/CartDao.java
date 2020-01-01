package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.po.Cart;
import com.po.Product;

@Repository("cartDao")
@Mapper
public interface CartDao {
	public void add(Cart cart);
	
	//获取用户购物车条目
	public List<Cart> getCart(Integer uid);
	
	//根据id更新buyNum
	public void updateByCid(@Param("id") Integer id,@Param("buyNum") Integer buyNum);
	
	//根据uid,pid查找
	public Cart getCartById(@Param("uid") Integer uid, @Param("pid") String pid);
	
	//根据id删除条目
	public void deleteById(Integer id);
} 
