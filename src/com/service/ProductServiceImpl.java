package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ProductDao;
import com.po.Product;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;
	@Override
	public List<Product> getList(String name, String category, Double minPrice, Double maxPrice) {
		// TODO Auto-generated method stub
		return productDao.getList(name, category, minPrice, maxPrice);
	}
	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		return productDao.getAll();
	}
	@Override
	public Product getProduct(String pid) {
		// TODO Auto-generated method stub
		return productDao.getProduct(pid);
	}
	@Override
	public List<Product> getCategory(String category) {
		return productDao.getCategory(category);
	}
	@Override
	public List<Product> searchByName(String pname) {
		// TODO Auto-generated method stub
		return productDao.searchByName(pname);
	}
	@Override
	public void addProduct(Product product) {
		productDao.addProduct(product);
	}

}
