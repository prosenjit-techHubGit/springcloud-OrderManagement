package io.das.product.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.das.product.entity.Product;
import io.das.product.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {

	Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/{categoryId}")
	public List<Product> getProductbyCategory(HttpServletRequest httpReq, @PathVariable int categoryId) {

		log.info("Recieved Request in getProductByCategory from Client" + httpReq.getRemoteHost());

		List<Product> products = productService.getProductByCategory(categoryId);

		log.info("Returning producs data: Total number of products - " + products.size());

		return products;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/add")
	public Product addProduct(@RequestBody Product product) {

		return productService.addProduct(product);

	}

}
