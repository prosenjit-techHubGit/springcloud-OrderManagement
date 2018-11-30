package io.cts.msa.om.order.service;

import java.util.List;
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
import io.cts.msa.om.order.exception.OrderSaveToDBException;
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

		logger.debug("Inside CreateOrdermethod");
		logger.debug("SalesOrderDetails object received" + order.toString());

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

		logger.debug("salesOrder object after mapping" + salesOrder.toString());

		for (OrderLineItem item : li) {
			item.setOrder(salesOrder);

		}

		try {
			salesOrder = salesOrderRepository.save(salesOrder);
		} catch (Exception e) {

			logger.error("Error saving order record to db");

			throw new OrderSaveToDBException(e.getMessage());

		}
		logger.debug("salesOrder object after DB save" + salesOrder.toString());
		return salesOrder.getId();

	}

	public SalesOrderDetails getOrderById(Long id) {

		SalesOrder salesOrder = null;
		SalesOrderDetails orderDetails = new SalesOrderDetails();

		Optional<SalesOrder> salesOrderOpt = null;
		try {
			salesOrderOpt = salesOrderRepository.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Erro querying Order Repository");
			throw new OrderSaveToDBException(e.getMessage());
		}

		if (salesOrderOpt.isPresent()) {
			salesOrder = salesOrderOpt.get();
			orderDetails.setId(salesOrder.getId());
			orderDetails.setOrderDate(salesOrder.getOrderDate());
			orderDetails.setOrderDesc(salesOrder.getOrderDesc());
			orderDetails.setTotalPrice(salesOrder.getTotalPrice());

		}

		return orderDetails;

	}

	public List<SalesOrderDetails> getAllOrders() {

		List<SalesOrder> salesOrder = null;
		List<SalesOrderDetails> orderDetails = null;

		try {
			salesOrder = salesOrderRepository.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Erro querying Order Repository");
			throw new OrderSaveToDBException(e.getMessage());
		}

		if (salesOrder != null && salesOrder.size() != 0) {

			orderDetails = salesOrder.stream().map(o -> {
				SalesOrderDetails sod = new SalesOrderDetails();
				sod.setId(o.getId());
				sod.setOrderDate(o.getOrderDate());
				sod.setOrderDesc(o.getOrderDesc());
				sod.setTotalPrice(o.getTotalPrice());
				return sod;

			}).collect(Collectors.toList());

		}

		return orderDetails;

	}

}
