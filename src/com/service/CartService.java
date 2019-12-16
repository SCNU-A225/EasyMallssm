package com.service;

import java.util.List;

import com.po.Cart;
import com.po.Product;

public interface CartService {
	
	public void add(Cart cart);
	
	//获取用户所有购物车条目
	public List<Cart> getCart(Integer uid);
	
	//通过id更新buyNum
	public void updateByCid(Integer id, Integer buyNum);
	
	//通过uid,pid获取购物车条目
	public Cart getCartById(Integer uid, String pid);
	
	//根据id删除条目
	public void deleteById(Integer id);
}
