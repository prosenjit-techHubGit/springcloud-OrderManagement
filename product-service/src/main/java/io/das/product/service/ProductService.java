package io.das.product.service;

import org.springframework.stereotype.Service;

import io.das.product.domain.Product;
import io.das.product.reference.ProductCategory;

@Service
public class ProductService {

	public Product getProductById(Long id) {

		return new Product(new Long(1), "HMRF678", ProductCategory.HOME_APPLIANCES, "Air Conditioner",
				"Air conditioning device");

	}

}
