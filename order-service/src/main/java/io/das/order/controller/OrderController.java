package io.das.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.das.order.domain.Order;
import io.das.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired

	private OrderService orderService;

	@GetMapping("/getOrder/{id}")
	public Order getOrderById(@PathVariable Long id) {

		return orderService.getOrderById(id);
	}

	@GetMapping("/getCustomerOrders/{custId}")
	public List<Order> getOrderByCustomerId(@PathVariable Long custId) {

		return orderService.getOrdersByCustomerId(custId);
	}

	@PostMapping("/create")

	public Order createOrder(@RequestBody Order order) {

		return orderService.createOrder(order);

	}

}
