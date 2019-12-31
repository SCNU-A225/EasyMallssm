package com.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.po.Cart;
import com.po.Order;
import com.po.OrderInfo;
import com.po.OrderItem;
import com.po.Product;
import com.po.User;
import com.service.CartService;
import com.service.OrderService;
import com.service.ProductService;

@Controller("orderController")
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/addOrder",method=RequestMethod.POST)
	public String addOrder(String receiverinfo, HttpSession session, Model model) {
		String id = UUID.randomUUID().toString();
		double money = 0;
		int paystate = 0;//0表示未支付
		User user = (User)session.getAttribute("user");
		int user_id = user.getId();
		List<Cart> cartList = (List<Cart>)session.getAttribute("cartList");
		for(int i = 0; i < cartList.size(); i++) {
			OrderItem orderItem = new OrderItem();
			Cart cart = cartList.get(i);
			Product product = productService.getProduct(cart.getPid());
			//如果购买数量大于库存，下单失败
			if(cart.getBuyNum() > product.getPnum()) {
				String msg = product.getName() + "库存不足，仅剩余" + product.getPnum() + "件,请重新下单";
				session.setAttribute("orderMsg", msg);
				return "redirect:/cart/getCart";
			}
			orderItem.setOrder_id(id);
			orderItem.setProduct_id(cart.getPid());
			orderItem.setBuynum(cart.getBuyNum());
			money = money + cart.getBuyNum() * cart.getProduct().getPrice();
			orderService.addOrderItem(orderItem);
		}
		for(int i = 0; i < cartList.size(); i++) {
			cartService.deleteById(cartList.get(i).getId());
		}
		Order order = new Order();
		order.setId(id);
		order.setMoney(money);
		order.setReceiverinfo(receiverinfo);
		order.setPaystate(paystate);
		order.setUser_id(user_id);
		orderService.addOrder(order);
		List<OrderInfo> orderInfos = orderService.findOrders(user_id);
		model.addAttribute("orderInfos", orderInfos);
		return "order_list";
	}
	
	@RequestMapping(value="/toOrder", method=RequestMethod.GET)
	public String toOrder(HttpSession session, Model model) {
		User user = (User)session.getAttribute("user");
		Integer user_id = user.getId();
		List<OrderInfo> orderInfos = orderService.findOrders(user_id);
		model.addAttribute("orderInfos", orderInfos);
		return "order_list";
	}
	
	@RequestMapping(value="/deleteOrder", method=RequestMethod.GET)
	public String deleteOrder(String id, HttpSession session, Model model) {
		orderService.deleteOrder(id);
		User user = (User)session.getAttribute("user");
		Integer user_id = user.getId();
		List<OrderInfo> orderInfos = orderService.findOrders(user_id);
		model.addAttribute("orderInfos", orderInfos);
		return "order_list";
	}
}
