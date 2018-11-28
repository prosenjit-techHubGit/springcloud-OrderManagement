package io.cts.msa.om.order.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_LINE_ITEM_140837")
public class OrderLineItem {

	public OrderLineItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderLineItem(Long id, String item_name, int item_quantity, SalesOrder order) {
		super();
		this.id = id;
		this.item_name = item_name;
		this.item_quantity = item_quantity;
		this.order = order;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odr_li_140837_sq")
	private Long id;

	private String item_name;
	private int item_quantity;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orderId", nullable = false, updatable = false, insertable = true)
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
