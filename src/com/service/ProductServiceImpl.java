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
	public List<Product> getList(String _name, String _category, Double _minPrice, Double _maxPrice) {
		// TODO Auto-generated method stub
		return productDao.getList(_name, _category, _minPrice, _maxPrice);
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

}
