package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.po.Product;

@Repository("productDao")
@Mapper
public interface ProductDao {
	public List<Product> getList(@Param("name") String name,
			@Param("category") String category,
			@Param("minPrice") Double minPrice,
			@Param("maxPrice") Double maxPrice);
	public List<Product> getAll();
	
	public Product getProduct(String pid);
	
	public List<Product> getCategory(String category);
	
	public List<Product> searchByName(String pname);
	
	public void addProduct(Product product);
}
