package io.cts.msa.om.order.domain;

import io.cts.msa.om.order.entity.SalesOrder;

public class OrderLineItemDetails {

	public OrderLineItemDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderLineItemDetails(Long id, String item_name, int item_quantity, SalesOrder order) {
		super();
		this.id = id;
		this.item_name = item_name;
		this.item_quantity = item_quantity;
		this.order = order;
	}

	private Long id;

	private String item_name;
	private int item_quantity;
	private SalesOrder order;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public int getItem_quantity() {
		return item_quantity;
	}

	public void setItem_quantity(int item_quantity) {
		this.item_quantity = item_quantity;
	}

	public SalesOrder getOrder() {
		return order;
	}

	public void setOrder(SalesOrder order) {
		this.order = order;
	}

}
