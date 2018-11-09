package io.das.order.domain;

import java.util.List;

public class Order {

	private Long oderId;
	private String orderType;
	private String status;
	private Long customerId;
	private List<LineItem> lineItems;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Long oderId, String orderType, String status, Long customerId) {
		super();
		this.oderId = oderId;
		this.orderType = orderType;
		this.status = status;
		this.customerId = customerId;
	}

	public Long getOderId() {
		return oderId;
	}

	public void setOderId(Long oderId) {
		this.oderId = oderId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

}
