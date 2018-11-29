package io.cts.msa.om.order.contract;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SalesOrderRequest {

	public SalesOrderRequest(Date orderDate, String orderDesc, Long custId, String[] itemNames) {
		super();
		this.orderDate = orderDate;
		this.orderDesc = orderDesc;
		this.custId = custId;
		this.itemNames = itemNames;
	}

	public SalesOrderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	private Date orderDate;
	private String orderDesc;

	@NotNull
	private Long custId;
	@NotNull
	private String[] itemNames;

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

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String[] getItemNames() {
		return itemNames;
	}

	public void setItemNames(String[] itemNames) {
		this.itemNames = itemNames;
	}

}
