package com.newt.service;

import java.util.List;

import com.newt.model.Product;
import com.newt.model.ProductElasticSearch;

public interface ProductService {
	public List<Product> findByproductIdSearch(String productId);

	public Iterable<Product> findAll();

	public Product save(Product car);

	 public List<Product> findByProductName(String productName);
	//public List<ProductElasticSearch> findByProductName(String productName);
	
	
}
