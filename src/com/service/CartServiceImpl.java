package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CartDao;
import com.po.Cart;
import com.po.Product;

@Service("cartService")
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartDao cartDao;

	@Override
	public void add(Cart cart) {
		cartDao.add(cart);
	}

	@Override
	public List<Cart> getCart(Integer uid) {
		return cartDao.getCart(uid);
	}

	@Override
	public void updateByCid(Integer id, Integer buyNum) {
		cartDao.updateByCid(id, buyNum);
	}

	@Override
	public Cart getCartById(Integer uid, String pid) {
		return cartDao.getCartById(uid, pid);
	}

	@Override
	public void deleteById(Integer id) {
		cartDao.deleteById(id);
	}

	

}
