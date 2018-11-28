package io.das.order.domain;

import java.util.List;

public class OrderDetails {

	private Long oderId;
	private String orderType;
	private String status;
	private Long customerId;
	private List<LineItemDetails> lineItems;

	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderDetails(Long oderId, String orderType, String status, Long customerId) {
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

	public List<LineItemDetails> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItemDetails> lineItems) {
		this.lineItems = lineItems;
	}

	@Override
	public String toString() {
		return "OrderDetails [oderId=" + oderId + ", orderType=" + orderType + ", status=" + status + ", customerId="
				+ customerId + ", lineItems=" + lineItems + "]";
	}

	
}
