package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tools.ant.taskdefs.Exit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.po.OrderInfo;
import com.po.Product;
import com.po.User;
import com.service.OrderService;
import com.service.ProductService;
import com.service.UserService;

@Controller("adminController")
@RequestMapping("/admin")
public class AdminController {
	private Gson gson = new Gson();
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	//进入后台登录页面
	@RequestMapping(value="/tologin", method=RequestMethod.GET)
	public String toLogin() {
		return "admin_login";
	}
	
	//进入后台主页
	@RequestMapping(value = "/index")
	public String index(){
		return "admin_index";
	}
	
	//进入添加商品页面
	@RequestMapping(value="/toprodadd")
	public String toProductUpdate() {
		return "admin_product_add";
	}
	
	//进入欢迎页面
	@RequestMapping(value="/welcome")
	public String welcome(Model model) {
		model.addAttribute("usernum", userService.getUserList().size());
		model.addAttribute("prodnum", productService.getAll().size());
		model.addAttribute("ordernum", orderService.getAllOrders().size());
		return "admin_welcome";
	}
	
	//管理员登录
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(String username, String password,HttpSession session) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		User adminUser = userService.adminlogin(user);
		if(adminUser != null) {
			session.setAttribute("user", adminUser);
			return "redirect:/admin/index";
		}else {
			return "admin_login";
		}
	}
	
	@RequestMapping(value="/exit")
	public String exit(HttpSession session) {
		session.removeAttribute("user");
		return "redirect: tologin";
	}
	
	//获取商品列表
	@RequestMapping(value="/prodlist", method=RequestMethod.GET )
	public String productList(Model model) {
		List<Product> prodlist = productService.getAll();
		String data = gson.toJson(prodlist);
		model.addAttribute("prodlist", data);
		return "admin_product_list";
	}
	
	//获取用户列表
	@RequestMapping(value="/userlist",method=RequestMethod.GET)
	public String userList(Model model) {
		List<User> userlist = userService.getUserList();
		model.addAttribute("userlist", userlist);
		return "admin_user_list";
	}
	
	//获取订单信息列表
	@RequestMapping(value="/orderlist",method=RequestMethod.GET)
	public String orderInfoList(Model model) {
		List<OrderInfo> orderinfolist = orderService.getAllOrderInfo();
		model.addAttribute("orderinfolist", orderinfolist);
		return "admin_order_list";
	}
	
	//上传图片，返回存储路径
	@RequestMapping(value="/uploadimg",method=RequestMethod.POST)
	@ResponseBody
	public String uploadimg(@RequestParam(value="file", required = true) MultipartFile file, HttpServletRequest request) {
		String rootPath = request.getServletContext().getRealPath("/");
		String savePath = "upload/new/";
		String realpath = rootPath + savePath;
		File readlDir = new File(realpath);
		if(!readlDir.exists()) {
			readlDir.mkdirs();
		}
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
		String fileName = UUID.randomUUID().toString()+suffix;
		File targetFile = new File(realpath, fileName);
		while(targetFile.exists()) {
			fileName = UUID.randomUUID().toString()+suffix;
			targetFile = new File(realpath, fileName);
		}
		String jsonString = "{\"code\":400,\"msg\":\"failed\",\"url\":\"img/no_photo.jpg\"}";
		try {
			file.transferTo(targetFile);
			jsonString = "{\"code\":200,\"msg\":\"ok\",\"url\":\""+savePath+fileName+"\"}";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return	jsonString;
	}
	
	//添加商品
	@RequestMapping(value="/addproduct",method=RequestMethod.POST)
	@ResponseBody
	public String addProduct(Product product,HttpServletResponse response) {
		String id = UUID.randomUUID().toString();
		product.setId(id);
		productService.addProduct(product);
		return "{\"code\":200}";
	}
	
	//删除商品
	@RequestMapping(value="/delproduct",method=RequestMethod.POST)
	public void delProduct(String id) {
		productService.delProduct(id);
	}
	
	//更新商品
	@RequestMapping(value="/updateprod",method=RequestMethod.POST)
	public void updateProd(Product product,HttpServletResponse response) {
		productService.updateProd(product);
		try {
			response.getOutputStream().print("ok");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
