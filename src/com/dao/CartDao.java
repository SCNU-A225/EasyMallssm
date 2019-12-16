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
	
	//��ȡ�û����ﳵ��Ŀ
	public List<Cart> getCart(Integer uid);
	
	//����id����buyNum
	public void updateByCid(@Param("id") Integer id,@Param("buyNum") Integer buyNum);
	
	//����uid,pid����
	public Cart getCartById(@Param("uid") Integer uid, @Param("pid") String pid);
	
	//����idɾ����Ŀ
	public void deleteById(Integer id);
} 
