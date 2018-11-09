package io.das.order.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.das.order.domain.Customer;
import io.das.order.domain.LineItem;
import io.das.order.domain.Order;
import io.das.order.domain.Product;

@Service
public class OrderService implements IOrderService {

	Logger log = LoggerFactory.getLogger(OrderService.class);

	private CustomerService customerService;
	private ProductService productService;

	@Autowired
	public OrderService(CustomerService customerService, ProductService productService) {
		this.customerService = customerService;
		this.productService = productService;

	}

	public Order getOrderById(Long id) {

		Order order = new Order(new Long(12345), "Applicances", "Submitted", new Long(12));
		List<LineItem> lineItems = new ArrayList<LineItem>();
		lineItems.addAll(Arrays.asList(new LineItem[] { new LineItem(new Long(12345), null, 2, "HMRF00233", 45670.67),
				new LineItem(new Long(12346), null, 2, "HMRF00233", 45670.67) }));

		lineItems = lineItems.stream().map(li -> {

			Product product = productService.getProductBySku(li.getProductSku());
			li.setDesc(product.getDescription());
			return li;
		}).collect(Collectors.toList());

		order.setLineItems(lineItems);

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

			orders = Arrays.asList(new Order[] { new Order(new Long(12345), "Applicances", "Submitted", new Long(12)),
					new Order(new Long(12346), "Applicances", "Submitted", new Long(12)) });
		}
		return orders;
	}

	public Order createOrder(Order order) {

		return new Order(order.getOderId(), order.getOrderType(), order.getStatus(), order.getCustomerId());
	}

}
