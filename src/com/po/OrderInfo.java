package com.po;

import java.util.List;

public class OrderInfo {
	private Order order;
	private List<OrderItem> list;
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<OrderItem> getList() {
		return list;
	}
	public void setList(List<OrderItem> list) {
		this.list = list;
	}
	
}
