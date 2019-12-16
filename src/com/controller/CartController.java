package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.exception.UserLoginNoException;
import com.mysql.fabric.xmlrpc.base.Value;
import com.po.Cart;
import com.po.Product;
import com.po.User;
import com.service.CartService;

@Controller("cartController")
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	/**
	 * 前台用户登录权限控制，处理方法执行前执行该方法
	 * @throws UserLoginNoException 
	 */
	@ModelAttribute  
    public void isLogin(HttpSession session) throws UserLoginNoException {      
       if(session.getAttribute("user") == null){  
            throw new UserLoginNoException("没有登录");
       }  
    } 
	
	@RequestMapping(value="/addCart", method=RequestMethod.GET)
	public String add(String buyNum, String pid, HttpSession session, Model model) {
		User user = (User)session.getAttribute("user");
		Cart cart = cartService.getCartById(user.getId(), pid);
		//判断商品是否已经存在购物车
		if(cart != null) {
			cartService.updateByCid(cart.getId(), cart.getBuyNum() + Integer.parseInt(buyNum));
		}else {
			cart = new Cart();
			cart.setUid(user.getId());
			cart.setPid(pid);
			cart.setBuyNum(Integer.parseInt(buyNum));
			cartService.add(cart);
		}
		//获取用户购物车，转到购物车页面
		List<Cart> cartList = cartService.getCart(user.getId());
		model.addAttribute("cartList",cartList);
		return "cart";
	}
	
	@RequestMapping(value="/getCart", method=RequestMethod.GET)
	public String getCart(HttpSession session, Model model) {
		User user = (User)session.getAttribute("user");
		List<Cart> cartList = cartService.getCart(user.getId());
		model.addAttribute("cartList",cartList);
		return "cart";
	}
	
	//cart.jsp 增减更新调用
	@RequestMapping(value="/updateByCid", method=RequestMethod.POST)
	public void updateByCid(Integer id, Integer buyNum) {
		cartService.updateByCid(id, buyNum);
	}
	
	//根据id删除购物车条目
	@RequestMapping(value="/deleteById", method=RequestMethod.POST)
	public void deleteById(Integer id) {
		cartService.deleteById(id);
	}
}
