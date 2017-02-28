package com.newt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.newt.model.Product;
import com.newt.model.ProductElasticSearch;
import com.newt.service.ProductService;
import com.wordnik.swagger.annotations.ApiOperation;
@CrossOrigin
@RestController
@RequestMapping("/products")
//@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.newt.repository")
public class ProductController {

	@Autowired
	ProductService productService;
	
	/*@Autowired
	private ElasticsearchTemplate template;
	@Bean
	public ElasticsearchTemplate elasticsearchTemplate() {
		return new ElasticsearchTemplate(getNodeClient());
	}
	
	IndexQuery indexQuery = new IndexQuery();
	 */
 
	@RequestMapping(value = "search/id/{productId}", method = RequestMethod.GET,  produces = "application/json")
	@ApiOperation(value = "Find By ProductID")
	public Map findbyproductId(@PathVariable String productId) {
		Map responseMap = new HashMap();
		//return productService.findByproductId(productId);
		List<Product> idSearch = productService.findByproductIdSearch(productId);
		responseMap.put("SearchResult", idSearch);
		return responseMap;
	}

	@RequestMapping(value = "search/name/{productName}", method = RequestMethod.GET)
	@ApiOperation(value = "Find By ProductName")
	public List<Product> findbyproductName(@PathVariable String productName) {
		return productService.findByProductName(productName);
	}
		
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Product> findAll() {
		return productService.findAll();
	}

	@ApiOperation(value = "post a product")
	@RequestMapping(method = RequestMethod.POST)
	public Product create(@RequestBody Product car) {
		
		/*boolean createIndex = template.createIndex("products");
		template.getElasticsearchConverter();
		indexQuery.setId(String.valueOf(car.getProductId()));
		indexQuery.setIndexName("Prodcut List");
		 
		indexQuery.setObject(car);
		template.putMapping(ProductElasticSearch.class);
		template.index(indexQuery);
		template.refresh(ProductElasticSearch.class, true);
		String id = indexQuery.getId();*/
		
		return productService.save(car);
	}
	
	/*private static NodeClient getNodeClient() {
		return (NodeClient) nodeBuilder().clusterName(UUID.randomUUID().toString()).local(true).node()
				.client();
	}*/
}
