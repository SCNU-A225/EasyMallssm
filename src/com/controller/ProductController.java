package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.po.Product;
import com.service.ProductService;

@Controller("productController")
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	//根据条件查询
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	public String getList(String name, String category, Double minprice, Double maxprice, Model model) {
		if(name == null || "".equals(name)) name = null;
		if(category == null || "".equals(category)) category = null;
		List<Product> list = productService.getList(name, category, minprice, maxprice);
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
	
	/**>手机数码</a></li>>电脑平板</a></li>>家用电器</a></li>汽车用品</a></li>食品饮料</a>图书杂志</a>服装服饰</a>理财产品</a></li>
	 */
	@RequestMapping(value = "/getCategory", method=RequestMethod.GET)
	public String getCategory(String c, Model model) {
		int num = Integer.parseInt(c);
		String[] categorys = {"电子数码","电脑平板","家用电器","日用百货","食品饮料","图书杂志","服装服饰","床上用品"};
		List<Product> list = productService.getCategory(categorys[num]);
		model.addAttribute("list", list);
		return "prod_list";
	}
	
	@RequestMapping(value="/searchByName", method=RequestMethod.POST)
	public String searchByName(String pname, Model model) {
		if(pname == null || "".equals(pname)) pname = null;
		List<Product> list = productService.searchByName(pname);
		model.addAttribute("list", list);
		return "prod_list";
	}
}
