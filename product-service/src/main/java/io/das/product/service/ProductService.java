package io.das.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.das.product.entity.Product;
import io.das.product.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public Product getProductById(Long id) {

		return null;

	}

	public List<Product> getProductByCategory(int categoryId) {

		return productRepository.findByCategory(categoryId);

	}

	public Product addProduct(Product product) {

		return productRepository.save(product);

	}

}
