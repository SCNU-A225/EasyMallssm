package com.service;

import java.util.List;

import com.po.Product;

public interface ProductService {
	public List<Product> getList(String _name, String _category, Double _minPrice, Double _maxPrice);
	public List<Product> getAll();
	public Product getProduct(String pid);
	public List<Product> getCategory(String category);
}
