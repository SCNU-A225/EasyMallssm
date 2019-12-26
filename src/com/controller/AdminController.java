package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("adminController")
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping(value="/tologin", method=RequestMethod.GET)
	public String toLogin() {
		return "admin_login";
	}
	
	@RequestMapping(value = "/index")
	public String index(){
		return "admin_index";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login() {
		return "redirect:/admin/index";
	}
	
	@RequestMapping(value="/product/list")
	public String productList() {
		return "admin_product_list";
	}
}
