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
	 * ǰ̨�û���¼Ȩ�޿��ƣ�������ִ��ǰִ�и÷���
	 * @throws UserLoginNoException 
	 */
	@ModelAttribute  
    public void isLogin(HttpSession session) throws UserLoginNoException {      
       if(session.getAttribute("user") == null){  
            throw new UserLoginNoException("û�е�¼");
       }  
    } 
	
	@RequestMapping(value="/addCart", method=RequestMethod.GET)
	public String add(String buyNum, String pid, HttpSession session, Model model) {
		User user = (User)session.getAttribute("user");
		Cart cart = cartService.getCartById(user.getId(), pid);
		//�ж���Ʒ�Ƿ��Ѿ����ڹ��ﳵ
		if(cart != null) {
			cartService.updateByCid(cart.getId(), cart.getBuyNum() + Integer.parseInt(buyNum));
		}else {
			cart = new Cart();
			cart.setUid(user.getId());
			cart.setPid(pid);
			cart.setBuyNum(Integer.parseInt(buyNum));
			cartService.add(cart);
		}
		//��ȡ�û����ﳵ��ת�����ﳵҳ��
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
	
	//cart.jsp �������µ���
	@RequestMapping(value="/updateByCid", method=RequestMethod.POST)
	public void updateByCid(Integer id, Integer buyNum) {
		cartService.updateByCid(id, buyNum);
	}
	
	//����idɾ�����ﳵ��Ŀ
	@RequestMapping(value="/deleteById", method=RequestMethod.POST)
	public void deleteById(Integer id) {
		cartService.deleteById(id);
	}
}
