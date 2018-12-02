package io.cts.msa.om.order.request.handler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.cts.msa.om.order.contract.SalesOrderRequest;
import io.cts.msa.om.order.domain.CustomerDetails;
import io.cts.msa.om.order.domain.ItemDetails;
import io.cts.msa.om.order.domain.OrderLineItemDetails;
import io.cts.msa.om.order.domain.SalesOrderDetails;
import io.cts.msa.om.order.entity.OrderLineItem;
import io.cts.msa.om.order.exception.CustomerRecordNotFoundException;
import io.cts.msa.om.order.exception.InvalidItemException;
import io.cts.msa.om.order.service.CustomerService;
import io.cts.msa.om.order.service.ItemService;
import io.cts.msa.om.order.service.SalesOrderService;

@Component
public class OrderRequestHandler {

	private Logger logger = LoggerFactory.getLogger(OrderRequestHandler.class);

	private CustomerService customerService;
	private ItemService itemService;
	private SalesOrderService salesOrderService;
	private InvalidItemException itemException;

	@Autowired
	public OrderRequestHandler(CustomerService customerService, ItemService itemService,
			SalesOrderService salesOrderService, InvalidItemException itemException) {
		this.customerService = customerService;
		this.itemService = itemService;
		this.salesOrderService = salesOrderService;
		this.itemException = itemException;

	}

	public Long createOrder(@Valid SalesOrderRequest salesOrderRequest) {

		logger.info("OrderDate: " + salesOrderRequest.getOrderDate());

		Long newOrderId = null;
		CustomerDetails custDetails = getCustomer(salesOrderRequest.getCustId());

		if (custDetails == null) {
			logger.error("Customer id does not exist");
			throw new CustomerRecordNotFoundException();

		}

		String[] itemNames = salesOrderRequest.getItemNames();

		List<ItemDetails> itemList = getItemMulti(Arrays.asList(itemNames));

		itemList = itemList.stream().filter(i -> i.getName() != null).collect(Collectors.toList());

		if (itemList != null && itemList.size() == itemNames.length) {

			Set<ItemDetails> items = new HashSet<ItemDetails>(itemList);

			SalesOrderDetails orderDetails = new SalesOrderDetails();
			orderDetails.setOrderDate(salesOrderRequest.getOrderDate());
			orderDetails.setOrderDesc(salesOrderRequest.getOrderDesc());
			orderDetails.setTotalPrice(salesOrderRequest.getTotalPrice());
			orderDetails.setCustomer(custDetails);

			Set<OrderLineItemDetails> orderLineItems = items.stream().map(i -> {

				OrderLineItemDetails oli = new OrderLineItemDetails();
				oli.setItem_name(i.getName());
				oli.setItem_quantity(3);
				return oli;

			}).collect(Collectors.toSet());

			orderDetails.setLineItems(orderLineItems);
			newOrderId = salesOrderService.createOrder(orderDetails);
		} else {
			logger.error("One or more items in the list do not exist");
			throw itemException;
		}

		return newOrderId;

	}

	public CustomerDetails getCustomer(Long custId) {

		CustomerDetails custDetails = null;

		if (custId != null) {

			custDetails = customerService.getCustomerbyId(custId);

		}

		return custDetails;

	}

	public ItemDetails getItem(String itemName) {

		ItemDetails itemDetails = null;

		if (itemName != null) {

			itemDetails = itemService.getItemByName(itemName);

		}

		return itemDetails;

	}

	public List<ItemDetails> getItemMulti(List<String> itemName) {

		List<ItemDetails> itemDetails = null;

		if (itemName != null) {

			itemDetails = itemService.getItemByNameIn(itemName);

		}

		return itemDetails;

	}

}
