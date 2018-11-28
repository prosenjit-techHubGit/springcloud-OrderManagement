package io.das.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.das.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByCategory(int categoryId);

}
