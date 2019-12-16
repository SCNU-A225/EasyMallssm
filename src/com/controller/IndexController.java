package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("indexController")
@RequestMapping("/index")
public class IndexController {
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/register")
	public String register() {
		return "regist";
	}
	
	@RequestMapping(value="/exit", method = RequestMethod.GET)
	public String exit(HttpSession session) {
		session.removeAttribute("user");
		return "forward:/index.jsp";
	}
	
	@RequestMapping(value="/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "forward:/index.jsp";
	}
}
