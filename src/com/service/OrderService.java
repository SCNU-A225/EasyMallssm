package com.service;

import java.util.List;

import com.po.Order;
import com.po.OrderInfo;
import com.po.OrderItem;

public interface OrderService {
	public void addOrder(Order order);
	public void addOrderItem(OrderItem orderItem);
	public List<OrderInfo> findOrders(Integer user_id);
	public void deleteOrder(String order_id);
}
