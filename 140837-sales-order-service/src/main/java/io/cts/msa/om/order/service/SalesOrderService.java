package io.cts.msa.om.order.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.cts.msa.om.order.domain.CustomerDetails;
import io.cts.msa.om.order.domain.OrderLineItemDetails;
import io.cts.msa.om.order.domain.SalesOrderDetails;
import io.cts.msa.om.order.entity.Customer;
import io.cts.msa.om.order.entity.OrderLineItem;
import io.cts.msa.om.order.entity.SalesOrder;
import io.cts.msa.om.order.repository.SalesOrderRepository;

@Service
public class SalesOrderService {
	
	private Logger logger = LoggerFactory.getLogger(SalesOrderService.class);

	private SalesOrderRepository salesOrderRepository;

	@Autowired
	public SalesOrderService(SalesOrderRepository salesOrderRepository) {
		this.salesOrderRepository = salesOrderRepository;

	}

	public Long createOrder(SalesOrderDetails order) {

		CustomerDetails custDetails = order.getCustomer();
		Customer customer = new Customer();
		customer.setCust_email(custDetails.getCustEmail());
		customer.setCust_id(custDetails.getCustId());
		customer.setCustFirstNmae(custDetails.getCustFirstNmae());
		customer.setCustLastName(custDetails.getCustLastName());

		Set<OrderLineItemDetails> items = order.getLineItems();

		Set<OrderLineItem> li = items.stream().map(i -> {
			OrderLineItem lineItem = new OrderLineItem();
			lineItem.setItem_name(i.getItem_name());
			lineItem.setItem_quantity(i.getItem_quantity());

			return lineItem;

		}).collect(Collectors.toSet());

		SalesOrder salesOrder = new SalesOrder();

		salesOrder.setCustomer(customer);
		salesOrder.setLineItems(li);
		salesOrder.setOrderDate(order.getOrderDate());
		salesOrder.setOrderDesc(order.getOrderDesc());
		salesOrder.setTotalPrice(order.getTotalPrice());

		for (OrderLineItem item : li) {
			item.setOrder(salesOrder);

		}

		try {
			salesOrder = salesOrderRepository.save(salesOrder);
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return salesOrder.getId();

	}

	public SalesOrderDetails getOrderById(Long id) {

		SalesOrder salesOrder = null;
		SalesOrderDetails orderDetails = new SalesOrderDetails();

		Optional<SalesOrder> salesOrderOpt = salesOrderRepository.findById(id);

		salesOrderOpt.ifPresent(o -> salesOrderOpt.get());

		orderDetails.setId(salesOrder.getId());
		orderDetails.setOrderDesc(salesOrder.getOrderDesc());

		return orderDetails;

	}

}
