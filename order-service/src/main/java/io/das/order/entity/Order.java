package io.das.order.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "OM_ORDER")
public class Order implements Serializable {

	@Id
	@GeneratedValue
	private Long orderId;

	private String orderType;
	private String status;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)

	private List<LineItem> lineItems;

	/*
	 * public Order() { super(); // TODO Auto-generated constructor stub }
	 */

	/*
	 * public Long getOrderId() { return orderId; }
	 * 
	 * 
	 * public void setOrderId(Long oderId) { this.orderId = orderId; }
	 */

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

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

}
