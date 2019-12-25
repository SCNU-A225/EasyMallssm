package com.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.po.User;
import com.service.UserService;

@Controller("userController")
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
//	@RequestMapping("/select")
//	public String select(User user, Model model){		
//		List<User> list = userService.selectUserByUsername(user);
//		model.addAttribute("userList",list);
//	//	return "/WEB-INF/jsp/userList.jsp";
//		return "userList";
//	}
	
	@RequestMapping(value="/regist", method = RequestMethod.POST)
	public String regist(@RequestParam String username, 
					     @RequestParam String password,
					     @RequestParam String password2,
					     @RequestParam String nickname,
					     @RequestParam String email,Model model) {
		if(username == null || "".equals(username)){
			model.addAttribute("msg","�û�������Ϊ�գ�");
			return "regist";
		}
		if(password == null || "".equals(password)){
			model.addAttribute("msg","���벻��Ϊ�գ�");
			return "regist";
		}
		if(password2 == null || "".equals(password2)){
			model.addAttribute("msg","ȷ�����벻��Ϊ�գ�");
			return "regist";
		}
		if(!password.equals(password2)){
			model.addAttribute("msg", "�����������벻һ��");
			return "regist";
		}
		if(nickname == null || "".equals(nickname)){
			model.addAttribute("msg","�ǳƲ���Ϊ�գ�");
			return "regist";
		}
		if(email == null || "".equals(email)){
			model.addAttribute("msg","�����ַ����Ϊ�գ�");
			return "regist";
		}
		if(!email.matches("^\\w+@\\w+(\\.\\w+)+$")){
			model.addAttribute("msg", "�����ַ��ʽ����");
			return "regist";
		}
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setNickname(nickname);
		user.setEmail(email);
		
		User exited = userService.selectUserByUsername(user);
		if(exited != null) {
			model.addAttribute("msg", "�û����Ѵ���");
			return "regist";
		}
		
		userService.regist(user);
		model.addAttribute("m", "ע��ɹ���ȥ��¼");
//		return "forward:/index.jsp";
		return "regist";
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
		if(username == null || "".equals(username)){
			model.addAttribute("msg","�û�������Ϊ�գ�");
			return "login";
		}
		if(password == null || "".equals(password)){
			model.addAttribute("msg","���벻��Ϊ�գ�");
			return "login";
		}
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		User result = userService.login(user);
		if(result != null) {
			session.setAttribute("user", result);
			//����ǹ���Ա��¼�������̨
			if(user.getUsername().equals("admin")) return "redirect:/admin/login";
			return "redirect:/index/toIndex";
		}else {
			model.addAttribute("msg","�û������������");
			return "login";
		}
	}
	
	
}
