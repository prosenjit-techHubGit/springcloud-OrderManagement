package io.cts.msa.om.order.request.handler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.cts.msa.om.order.contract.SalesOrderRequest;
import io.cts.msa.om.order.domain.CustomerDetails;
import io.cts.msa.om.order.domain.ItemDetails;
import io.cts.msa.om.order.domain.OrderLineItemDetails;
import io.cts.msa.om.order.domain.SalesOrderDetails;
import io.cts.msa.om.order.service.CustomerService;
import io.cts.msa.om.order.service.ItemService;
import io.cts.msa.om.order.service.SalesOrderService;

@Component
public class OrderRequestHandler {

	private CustomerService customerService;
	private ItemService itemService;
	private SalesOrderService salesOrderService;

	@Autowired
	public OrderRequestHandler(CustomerService customerService, ItemService itemService,
			SalesOrderService salesOrderService) {
		this.customerService = customerService;
		this.itemService = itemService;
		this.salesOrderService = salesOrderService;

	}

	public Long createOrder(@Valid SalesOrderRequest salesOrderRequest) {

		CustomerDetails custDetails = getCustomer(salesOrderRequest.getCustId());
		String[] itemNames = salesOrderRequest.getItemNames();
		Set<ItemDetails> items = new HashSet<ItemDetails>(getItemMulti(Arrays.asList(itemNames)));

		/*
		 * for (String item : itemNames) {
		 * 
		 * items.add(getItem(item));
		 * 
		 * }
		 */

		SalesOrderDetails orderDetails = new SalesOrderDetails();
		orderDetails.setOrderDate(salesOrderRequest.getOrderDate());
		orderDetails.setOrderDesc(salesOrderRequest.getOrderDesc());
		orderDetails.setCustomer(custDetails);

		Set<OrderLineItemDetails> orderLineItems = items.stream().map(i -> {

			OrderLineItemDetails oli = new OrderLineItemDetails();
			oli.setItem_name(i.getName());
			oli.setItem_quantity(3);
			return oli;

		}).collect(Collectors.toSet());

		orderDetails.setLineItems(orderLineItems);
		return salesOrderService.createOrder(orderDetails);

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
