package io.das.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.das.order.domain.OrderDetails;

import io.das.order.request.handler.OrderRequestHandler;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired

	private OrderRequestHandler orderRequestHandler;

	@GetMapping("/getOrder/{id}")

	public OrderDetails getOrderById(@PathVariable Long id) {

		return orderRequestHandler.getOrderById(id);
	}
	/*
	 * @GetMapping("/getCustomerOrders/{custId}") public List<Order>
	 * getOrderByCustomerId(@PathVariable Long custId) {
	 * 
	 * return orderService.getOrdersByCustomerId(custId); }
	 */

	@PostMapping("/create")

	public OrderDetails createOrder(@RequestBody OrderDetails order) {

		return orderRequestHandler.createOrder(order);

	}

}
