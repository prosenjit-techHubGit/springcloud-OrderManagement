package io.das.order.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import io.das.order.entity.Order;

@Repository
public interface OrderRepository {

	public Order findbyOrderID();

	public List<Order> findByCustomerId();

}
