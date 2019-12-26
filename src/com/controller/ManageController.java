package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("manageController")
@RequestMapping("/manage")
public class ManageController {
	
	@RequestMapping(value="/tologin", method=RequestMethod.GET)
	public String toLogin() {
		return "mlogin";
	}
	
	
}
