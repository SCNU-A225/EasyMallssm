package com.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.po.OrderInfo;
import com.po.Product;
import com.po.User;
import com.service.OrderService;
import com.service.ProductService;
import com.service.UserService;

@Controller("adminController")
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	//�����̨��¼ҳ��
	@RequestMapping(value="/tologin", method=RequestMethod.GET)
	public String toLogin() {
		return "admin_login";
	}
	
	//�����̨��ҳ
	@RequestMapping(value = "/index")
	public String index(){
		return "admin_index";
	}
	
	//����Ա��¼
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
	
	//��ȡ��Ʒ�б�
	@RequestMapping(value="/prodlist", method=RequestMethod.GET )
	public String productList(Model model) {
		List<Product> prodlist = productService.getAll();
		model.addAttribute("prodlist", prodlist);
		return "admin_product_list";
	}
	
	//��ȡ�û��б�
	@RequestMapping(value="/userlist",method=RequestMethod.GET)
	public String userList(Model model) {
		List<User> userlist = userService.getUserList();
		model.addAttribute("userlist", userlist);
		return "admin_user_list";
	}
	
	//��ȡ������Ϣ�б�
	@RequestMapping(value="/orderlist",method=RequestMethod.GET)
	public String orderInfoList(Model model) {
		List<OrderInfo> orderinfolist = orderService.getAllOrderInfo();
		model.addAttribute("orderinfolist", orderinfolist);
		return "admin_order_list";
	}
	
	//�ϴ�ͼƬ�����ش洢·��
	@RequestMapping(value="/uploadimg",method=RequestMethod.POST)
	@ResponseBody
	public String uploadimg(@RequestParam(value="img", required = true) MultipartFile img, HttpServletRequest request) {
		String pathString = request.getServletContext().getRealPath("/");
		String realpath = pathString.substring(0, pathString.indexOf(".metadata")) + "EasyMallssm\\WebContent\\upload";
		String fileName = img.getOriginalFilename();
		File targetFile = new File(realpath, fileName);
		if(!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			img.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "upload/"+fileName;
	}
	
	//�����Ʒ
	@RequestMapping(value="/addproduct",method=RequestMethod.POST)
	public void addProduct(Product product) {
		String id = UUID.randomUUID().toString();
		product.setId(id);
		productService.addProduct(product);
	}
	
	//ɾ����Ʒ
	@RequestMapping(value="/delproduct",method=RequestMethod.POST)
	public void delProduct(String id) {
		productService.delProduct(id);
	}
	
	//������Ʒ
	@RequestMapping(value="/updateprod",method=RequestMethod.POST)
	public void updateProd(Product product) {
		productService.updateProd(product);
	}
}
