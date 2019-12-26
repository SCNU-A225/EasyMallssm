package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.po.Order;
import com.po.OrderItem;

@Repository("orderDao")
@Mapper
public interface OrderDao {
	public void addOrder(Order order);
	public void addOrderItem(OrderItem orderItem);
	public List<Order> findOrders(Integer user_id);
	public List<OrderItem> findOrderItems(String order_id);
	public void deleteOrderItem(String order_id);
	public void deleteOrder(String order_id);
	public List<Order> getAllOrders();
}
