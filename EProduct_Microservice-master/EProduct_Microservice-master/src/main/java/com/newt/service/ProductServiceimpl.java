package com.newt.service;

import java.util.List;

import org.elasticsearch.common.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Service;

import com.newt.model.Product;
import com.newt.model.ProductElasticSearch;
import com.newt.model.ProductsearchSpecification;
import com.newt.repository.ProductElasticSearchRep;
import com.newt.repository.ProductRepository;

@Service

public class ProductServiceimpl implements ProductService {

	@Autowired
	ProductRepository productRepo;
	
 	@Autowired
    ProductElasticSearchRep ProductElasticSearchRep;
 	
	public List<Product> findByproductIdSearch(String productId) {

		//return productRepo.findByProductId(productId);
		
		
		List<Product> searchList = productRepo.findAll(new ProductsearchSpecification(productId));
		
		
		return searchList;
	}

	public Iterable<Product> findAll() {

		return productRepo.findAll();
	}

	public Product save(Product products) {
		return productRepo.save(products);
	}

 
	/*public Product findByProductName(String productName) {
		 
		return productRepo.findByProductName(productName);
	}*/
	  /* @Inject
       public void setRepository(ProductElasticSearchRep repository) {
       this.ProductElasticSearchRep = repository;
       }*/
	   
	   @Override
		public List< Product> findByProductName(String productName) {
			List<Product> product =productRepo.findByProductName(productName);
			return product;
		}
}
