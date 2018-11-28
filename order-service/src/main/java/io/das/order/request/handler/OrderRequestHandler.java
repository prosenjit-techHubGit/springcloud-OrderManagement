package io.das.order.request.handler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.das.order.domain.LineItemDetails;
import io.das.order.domain.OrderDetails;
import io.das.order.entity.LineItem;
import io.das.order.entity.Order;
import io.das.order.service.OrderService;

@Component
public class OrderRequestHandler {

	@Autowired
	private OrderService orderService;

	public OrderDetails createOrder(OrderDetails orderRequest) {

		Logger log = LoggerFactory.getLogger(OrderRequestHandler.class);

		log.debug("Started Preparing order Details for " + orderRequest.getCustomerId());

		Order newOrder = new Order(orderRequest.getOrderType(), orderRequest.getStatus(), orderRequest.getCustomerId(),
				"140837");
		List<LineItem> lineItems = new ArrayList<LineItem>();
		List<LineItemDetails> lineItemRequest = orderRequest.getLineItems();

		List<LineItem> lineItemsRequest = lineItemRequest.stream().map(lid -> {

			LineItem li = new LineItem();

			li.setPrice(lid.getPrice());
			li.setProductSku(lid.getProductSku());

			return li;

		}).collect(Collectors.toList());

		for (LineItem lineItem : lineItemsRequest) {
			lineItem.setOrder(newOrder);

		}

		Set<LineItem> lineSet = new HashSet<LineItem>(lineItemsRequest);

		newOrder.setLineItems(lineSet);

		log.debug("Finished preparing order details for " + orderRequest.getCustomerId());
		log.debug("Calling OrderService.createOder()");

		newOrder = orderService.createOrder(newOrder);

		OrderDetails orderDetails = new OrderDetails();

		if (newOrder != null) {

			log.debug("Recieved non-empty order record from service - Starting to map the data");

			orderDetails = mapOrderDetails(newOrder, orderDetails);

			List<LineItemDetails> lineItemDetails = new ArrayList<LineItemDetails>();

			if (newOrder.getLineItems() != null) {

				log.debug("Recieved non-empty Lineitems record from service - Starting to map the data");

				orderDetails.setLineItems(mapLineItemDetails(newOrder.getLineItems(), lineItemDetails));

			}

		}

		return orderDetails;

	}

	public OrderDetails getOrderById(Long id) {

		Order order = orderService.getOrderById(id);
		OrderDetails orderDetails = new OrderDetails();

		if (order != null) {

			orderDetails = mapOrderDetails(order, orderDetails);

			List<LineItemDetails> lineItemDetails = new ArrayList<LineItemDetails>();

			if (order.getLineItems() != null) {

				orderDetails.setLineItems(mapLineItemDetails(order.getLineItems(), lineItemDetails));

			}
		}

		return orderDetails;
	}

	private OrderDetails mapOrderDetails(Order newOrder, OrderDetails orderDetails) {

		orderDetails.setOderId(newOrder.getOrderId());
		orderDetails.setLineItems(newOrder.getLineItems().stream().map(li -> {

			LineItemDetails lid = new LineItemDetails();
			lid.setOrderId(li.getOrder().getOrderId());
			lid.setPrice(li.getPrice());
			lid.setProductSku(li.getProductSku());

			return lid;

		}).collect(Collectors.toList()));

		return orderDetails;

	}

	private List<LineItemDetails> mapLineItemDetails(Set<LineItem> lineItems, List<LineItemDetails> lineItemDetails) {

		return lineItems.stream().map(li -> {

			LineItemDetails lid = new LineItemDetails();
			lid.setOrderId(li.getOrder().getOrderId());
			lid.setPrice(li.getPrice());
			lid.setProductSku(li.getProductSku());

			return lid;

		}).collect(Collectors.toList());

	}

}
