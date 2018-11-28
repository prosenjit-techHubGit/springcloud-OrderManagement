package io.das.order.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "OM_ODR_LI")
public class LineItem {
	
	 private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "orderId", nullable = false, updatable = false, insertable = true)
	private Order order;

	// private Long orderId;

	private Long qty;
	private String productSku;
	private double price;

	public LineItem(Long qty, String productSku, double price) {
		super();
		// this.orderId = orderId;

		this.qty = qty;
		this.productSku = productSku;
		this.price = price;
	}

	public LineItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * public Long getOrderId() { return orderId; }
	 * 
	 * public void setOrderId(Long orderId) { this.orderId = orderId; }
	 */

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	public String getProductSku() {
		return productSku;
	}

	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
