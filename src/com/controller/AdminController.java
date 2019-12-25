package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.po.Product;
import com.service.ProductService;

@Controller("adminController")
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/login")
	public String login(Model model) {
		List<Product> products = productService.getAll();
		model.addAttribute("productList", products);
		return "main";
	}
	
	@RequestMapping(value="/exit", method=RequestMethod.GET)
	public String exit(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/index/toIndex";
	}
	
	@RequestMapping(value="/refresh",method=RequestMethod.GET)
	public String refresh(Model model) {
		List<Product> products = productService.getAll();
		model.addAttribute("productList", products);
		return "main";
	}
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String detail(String id, Model model) {
		Product product = productService.getProduct(id);
		model.addAttribute("prod",product);
		return "goodsDetail";
	}
	
	@RequestMapping(value="/toAdd", method=RequestMethod.GET)
	public String toAdd(Model model) {
		String[] productType = {"电子数码","电脑平板","家用电器","日用百货","食品饮料","图书杂志","服装服饰","床上用品"};
		model.addAttribute("productType", productType);
		return "addGoods";
	}
	
	@RequestMapping(value="/addproduct", method=RequestMethod.POST)
	public void addProduct(Product product, HttpServletRequest request) {
		String newFileName="";
		String fileName=product.getImage().getOriginalFilename();
		String realpath=request.getServletContext().getRealPath("img");
		System.out.println(fileName);
		System.out.println(realpath);
//		return "forward:/admin/detail?id="+product.getId();
	}
}
