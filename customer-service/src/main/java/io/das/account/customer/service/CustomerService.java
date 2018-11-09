package io.das.account.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.das.account.customer.domain.Customer;
import io.das.account.customer.domain.Order;

@Service
public class CustomerService {

	@Autowired
	private OrderService orderService;

	public Customer getCustomerDetails(Long custId) {

		List<Order> orders = orderService.getOrdersByCustomerId(custId);
		Customer customer = new Customer(new Long(12), "Acme", "Janai");
		customer.setOrders(orders);

		return customer;

	}

}
