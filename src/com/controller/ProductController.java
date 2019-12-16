package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.po.Product;
import com.service.ProductService;

@Controller("productController")
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	public String getList(String _name, String _category, Double _minPrice, Double _maxPrice, Model model) {
		List<Product> list = productService.getList(_name, _category, _minPrice, _maxPrice);
		model.addAttribute("list",list);
		return "prod_list";
	}
	
	@RequestMapping(value = "/getAll", method=RequestMethod.GET)
	public String getAll(Model model) {
		List<Product> list = productService.getAll();
		model.addAttribute("list",list);
		return "prod_list";
	}
	
	@RequestMapping(value = "/getProduct", method=RequestMethod.GET)
	public String getProduct(String pid, Model model) {
		Product product = productService.getProduct(pid);
		model.addAttribute("prod", product);
		return "prod_info";
	}
}
