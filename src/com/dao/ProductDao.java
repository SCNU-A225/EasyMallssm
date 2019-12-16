package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.po.Product;

@Repository("productDao")
@Mapper
public interface ProductDao {
	public List<Product> getList(@Param("name") String _name,
			@Param("category") String _category,
			@Param("minPrice") Double _minPrice,
			@Param("maxPrice") Double _maxPrice);
	public List<Product> getAll();
	
	public Product getProduct(String pid);
}
