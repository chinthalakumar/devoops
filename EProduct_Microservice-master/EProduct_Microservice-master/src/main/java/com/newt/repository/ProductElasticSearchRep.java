package com.newt.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

 
import com.newt.model.ProductElasticSearch;

public interface ProductElasticSearchRep  extends ElasticsearchRepository<ProductElasticSearch,Integer> {
	public List<ProductElasticSearch> findProductElasticSearchByProductName(String productName);

}
