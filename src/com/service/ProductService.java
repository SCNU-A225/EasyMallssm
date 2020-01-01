package com.service;

import java.util.List;

import com.po.Product;

public interface ProductService {
	//条件查询
	public List<Product> getList(String name, String category, Double minPrice, Double maxPrice);
	
	public List<Product> getAll();
	public Product getProduct(String pid);
	public List<Product> getCategory(String category);
	public List<Product> searchByName(String pname);
	public void addProduct(Product product);
	public void delProduct(String id);
	public void updateProd(Product product);
}
