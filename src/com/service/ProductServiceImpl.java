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
		return productDao.getList(name, category, minPrice, maxPrice);
	}
	@Override
	public List<Product> getAll() {
		return productDao.getAll();
	}
	@Override
	public Product getProduct(String pid) {
		return productDao.getProduct(pid);
	}
	@Override
	public List<Product> getCategory(String category) {
		return productDao.getCategory(category);
	}
	@Override
	public List<Product> searchByName(String pname) {
		return productDao.searchByName(pname);
	}
	@Override
	public void addProduct(Product product) {
		productDao.addProduct(product);
	}
	@Override
	public void delProduct(String id) {
		productDao.delProduct(id);
	}
	@Override
	public void updateProd(Product product) {
		productDao.updateProd(product);
	}

}
