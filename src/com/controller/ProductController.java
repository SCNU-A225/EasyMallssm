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
	
	//����������ѯ
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
	
	/**>�ֻ�����</a></li>>����ƽ��</a></li>>���õ���</a></li>������Ʒ</a></li>ʳƷ����</a>ͼ����־</a>��װ����</a>��Ʋ�Ʒ</a></li>
	 */
	@RequestMapping(value = "/getCategory", method=RequestMethod.GET)
	public String getCategory(String c, Model model) {
		int num = Integer.parseInt(c);
		String[] categorys = {"��������","����ƽ��","���õ���","���ðٻ�","ʳƷ����","ͼ����־","��װ����","������Ʒ"};
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
