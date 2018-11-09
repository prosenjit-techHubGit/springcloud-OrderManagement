package io.das.order.domain;

public class LineItem {

	private Long id;
	private Long orderId;
	private String desc;
	private int qty;
	private String productSku;
	private double price;

	public LineItem(Long orderId, String desc, int qty, String productSku, double price) {
		super();
		this.orderId = orderId;
		this.desc = desc;
		this.qty = qty;
		this.productSku = productSku;
		this.price = price;
	}

	public LineItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
