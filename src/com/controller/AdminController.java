package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("manageController")
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping(value="/tologin", method=RequestMethod.GET)
	public String toLogin() {
		return "admin_login";
	}
	
	@RequestMapping(value = "/admin")
	public String admin(){
		return "admin";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login() {
		return "redirect:/manage/admin";
	}
}

