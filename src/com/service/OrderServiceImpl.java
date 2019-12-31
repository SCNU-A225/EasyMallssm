package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.OrderDao;
import com.dao.UserDao;
import com.po.Order;
import com.po.OrderInfo;
import com.po.OrderItem;

@Service("orderService")
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private UserDao userDao;

	@Override
	public void addOrder(Order order) {
		orderDao.addOrder(order);
	}

	@Override
	public void addOrderItem(OrderItem orderItem) {
		orderDao.addOrderItem(orderItem);
	}

	@Override
	public List<OrderInfo> findOrders(Integer user_id) {
		List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
		List<Order> orders = orderDao.findOrders(user_id);
		for(int i = 0; i < orders.size(); i++) {
			OrderInfo orderInfo = new OrderInfo();
			Order order = orders.get(i);
			List<OrderItem> orderItems = orderDao.findOrderItems(order.getId());
			orderInfo.setOrder(order);
			orderInfo.setList(orderItems);
			orderInfos.add(orderInfo);
		}
		if(orderInfos.size() > 0) {
			return orderInfos;
		}else {
			return null;
		}
	}
	

	@Override
	public void deleteOrder(String order_id) {
		orderDao.deleteOrderItem(order_id);
		orderDao.deleteOrder(order_id);
	}

	@Override
	public List<OrderInfo> getAllOrderInfo() {
		List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
		List<Order> orders = orderDao.getAllOrders();
		for(int i = 0; i < orders.size(); i++) {
			OrderInfo orderInfo = new OrderInfo();
			Order order = orders.get(i);
			List<OrderItem> orderItems = orderDao.findOrderItems(order.getId());
			String username = userDao.getUserName(order.getUser_id());
			orderInfo.setUsername(username);
			orderInfo.setOrder(order);
			orderInfo.setList(orderItems);
			orderInfos.add(orderInfo);
		}
		if(orderInfos.size() > 0) {
			return orderInfos;
		}else {
			return null;
		}
	}

	@Override
	public List<Order> getAllOrders() {
		return orderDao.getAllOrders();
	}

	@Override
	public void updatePayState(Integer paystate, String id) {
		orderDao.updatePayState(paystate, id);
	}
	

}
