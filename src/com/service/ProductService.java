package com.service;

import java.util.List;

import com.po.Product;

public interface ProductService {
	//Ìõ¼þ²éÑ¯
	public List<Product> getList(String name, String category, Double minPrice, Double maxPrice);
	
	public List<Product> getAll();
	public Product getProduct(String pid);
	public List<Product> getCategory(String category);
	public List<Product> searchByName(String pname);
	public void addProduct(Product product);
}
