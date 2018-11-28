package io.das.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.das.order.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	//public Order findbyOrderID(Long id);

	public List<Order> findByCustomerId(Long custId);

}
