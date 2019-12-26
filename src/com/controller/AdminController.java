package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.po.OrderInfo;
import com.po.Product;
import com.po.User;
import com.service.OrderService;
import com.service.ProductService;
import com.service.UserService;

@Controller("adminController")
@RequestMapping("/admin")
public class AdminController {
	private Gson gson = new Gson();
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	//进入后台登录页面
	@RequestMapping(value="/tologin", method=RequestMethod.GET)
	public String toLogin() {
		return "admin_login";
	}
	
	//进入后台主页
	@RequestMapping(value = "/index")
	public String index(){
		return "admin_index";
	}
	
	//管理员登录
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		User exit = userService.adminlogin(user);
		if(exit != null) {
			return "redirect:/admin/index";
		}else {
			return "admin_login";
		}
	}
	
	//获取商品列表
	@RequestMapping(value="/product/list", method=RequestMethod.GET )
	public String productList(Model model) {
		List<Product> prodlist = productService.getAll();
		String data = gson.toJson(prodlist);
		model.addAttribute("prodlist", data);
		return "admin_product_list";
	}
	
	//获取用户列表
	public String userList(Model model) {
		List<User> userlist = userService.getUserList();
		model.addAttribute("userlist", userlist);
		return "admin_user_list";
	}
	
	//获取订单信息列表
	public String orderInfoList(Model model) {
		List<OrderInfo> orderinfolist = orderService.getAllOrderInfo();
		model.addAttribute("orderinfolist", orderinfolist);
		return "admin_order_list";
	}
}
