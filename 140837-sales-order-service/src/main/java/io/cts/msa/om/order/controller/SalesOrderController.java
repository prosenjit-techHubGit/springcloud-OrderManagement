package io.cts.msa.om.order.controller;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.cts.msa.om.order.contract.SalesOrderRequest;
import io.cts.msa.om.order.domain.SalesOrderDetails;
import io.cts.msa.om.order.request.handler.OrderRequestHandler;
import io.cts.msa.om.order.service.SalesOrderService;

@RestController
@RequestMapping("/service")
public class SalesOrderController {

	private SalesOrderService salesOrderService;

	@Autowired
	private OrderRequestHandler orderRequestHandler;

	@Autowired
	public SalesOrderController(SalesOrderService salesOrderService) {
		this.salesOrderService = salesOrderService;

	}

	@PostMapping("/orders")
	public Long createOrder(@Valid @RequestBody SalesOrderRequest salesOrderRequest) {

		return orderRequestHandler.createOrder(salesOrderRequest);

		// return salesOrderService.createOrder(orderDetails);

	}

	@GetMapping("/orders")

	public List<SalesOrderDetails> getAllOrders() {

		return salesOrderService.getAllOrders();

	}

	@GetMapping("/orders/{id}")

	public SalesOrderDetails getOrderById(@PathVariable Long id) {

		return salesOrderService.getOrderById(id);

	}

}
