package com.service;

import java.util.List;

import com.po.Cart;
import com.po.Product;

public interface CartService {
	
	public void add(Cart cart);
	
	//��ȡ�û����й��ﳵ��Ŀ
	public List<Cart> getCart(Integer uid);
	
	//ͨ��id����buyNum
	public void updateByCid(Integer id, Integer buyNum);
	
	//ͨ��uid,pid��ȡ���ﳵ��Ŀ
	public Cart getCartById(Integer uid, String pid);
	
	//����idɾ����Ŀ
	public void deleteById(Integer id);
}
