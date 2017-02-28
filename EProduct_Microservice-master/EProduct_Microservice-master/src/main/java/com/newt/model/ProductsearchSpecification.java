package com.newt.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.validator.internal.engine.groups.Group;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class ProductsearchSpecification implements Specification<Product>{
	
	private String searchValue ;
	
	
	
	public ProductsearchSpecification(String searchValue) {
		super();
		this.searchValue = searchValue;
	}



	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		  
		  if (searchValue != null && !StringUtils.isEmpty(searchValue)) {
		   Path<Group> group = root.<Group> get("productId");
		   predicates.add(group.in(Arrays.asList(searchValue)));
		  }
		  
		  
		  if (searchValue != null && !StringUtils.isEmpty(searchValue)) {
			   Path<Group> group = root.<Group> get("productName");
			   predicates.add(group.in(Arrays.asList(searchValue)));
			  }
		  
		  if (searchValue != null && !StringUtils.isEmpty(searchValue)) {
			   Path<Group> group = root.<Group> get("productPrice");
			   predicates.add(group.in(Arrays.asList(searchValue)));
			  }
		  if (searchValue != null && !StringUtils.isEmpty(searchValue)) {
			   Path<Group> group = root.<Group> get("productDescription");
			   predicates.add(group.in(Arrays.asList(searchValue)));
			  }
		  
		  System.out.println(predicates);
		return cb.and(predicates.toArray(new Predicate[0]));
	}

}
