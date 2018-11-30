package io.cts.msa.om.order.domain;

import java.util.Date;
import java.util.Set;

import io.cts.msa.om.order.entity.Customer;
import io.cts.msa.om.order.entity.OrderLineItem;

public class SalesOrderDetails {

	public SalesOrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SalesOrderDetails(Long id, Date orderDate, String orderDesc, double totalPrice, CustomerDetails customer,
			Set<OrderLineItemDetails> lineItems) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.orderDesc = orderDesc;
		this.totalPrice = totalPrice;
		this.customer = customer;
		this.lineItems = lineItems;
	}

	private Long id;
	private Date orderDate;
	private String orderDesc;
	private double totalPrice;
	private CustomerDetails customer;
	private Set<OrderLineItemDetails> lineItems;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public CustomerDetails getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDetails customer) {
		this.customer = customer;
	}

	public Set<OrderLineItemDetails> getLineItems() {
		return lineItems;
	}

	public void setLineItems(Set<OrderLineItemDetails> lineItems) {
		this.lineItems = lineItems;
	}

	@Override
	public String toString() {
		return "SalesOrderDetails [id=" + id + ", orderDate=" + orderDate + ", orderDesc=" + orderDesc + ", totalPrice="
				+ totalPrice + ", customer=" + customer + ", lineItems=" + lineItems + "]";
	}
	
	

}
