package io.das.order.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.das.order.domain.Customer;
import io.das.order.entity.Order;

import io.das.order.repository.OrderRepository;

@Service
public class OrderService implements IOrderService {

	Logger log = LoggerFactory.getLogger(OrderService.class);

	private CustomerService customerService;
	private ProductService productService;
	private OrderRepository orderRepository;

	@Autowired
	public OrderService(CustomerService customerService, ProductService productService,
			OrderRepository orderRepository) {
		this.customerService = customerService;
		this.productService = productService;
		this.orderRepository = orderRepository;

	}

	public Order getOrderById(Long id) {

		Order order = null;
		Optional<Order> opt = orderRepository.findById(id);
		if (opt.isPresent()) {

			order = opt.get();
		}

		return order;

	}

	public List<Order> getOrdersByCustomerId(Long custId) {

		Customer customer = new Customer();
		List<Order> orders = null;

		try {
			// customer = customerService.getCustomerById(custId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (customer != null) {
			log.info("Customer Details: " + customer);
			log.info("Customer Verified...Retrieving Orders");

		}
		return orders;
	}

	public Order createOrder(Order newOrder) {

		return orderRepository.save(newOrder);

	}

	public Order getLineItemsByOrderId(Order newOrder) {

		return orderRepository.save(newOrder);

	}

}
