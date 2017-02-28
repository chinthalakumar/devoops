package com.newt.repository;



 
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.newt.model.Product;

@Repository 
public interface ProductRepository extends CrudRepository<Product, Integer>,JpaSpecificationExecutor<Product>  {

	public long count();    
 
	public List<Product> findByProductName(String productName);

	public Product findByProductId(int productId);
}
