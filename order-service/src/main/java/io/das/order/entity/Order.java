package io.das.order.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@Entity
@Table(name = "OM_ORDER")
public class Order {

	@Id
	@GeneratedValue
	private Long orderId;

	private String orderType;
	private String status;
	private Long customerId;

	@CreationTimestamp
	private LocalDateTime creationDateTime;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;

	@CreatedBy
	private String createdBy;

	@LastModifiedBy
	private String lastModifiledBy;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch=FetchType.EAGER)

	private Set<LineItem> lineItems;

	public String getOrderType() {
		return orderType;
	}

	public Order(String orderType, String status, Long customerId, LocalDateTime creationDateTime,
			LocalDateTime updateDateTime, String createdBy, String lastModifiledBy, Set<LineItem> lineItems) {
		super();
		this.orderType = orderType;
		this.status = status;
		this.customerId = customerId;
		this.creationDateTime = creationDateTime;
		this.updateDateTime = updateDateTime;
		this.createdBy = createdBy;
		this.lastModifiledBy = lastModifiledBy;
		this.lineItems = lineItems;
	}

	public Order(String orderType, String status, Long customerId, String createdBy) {
		super();
		this.orderType = orderType;
		this.status = status;
		this.customerId = customerId;
		this.createdBy = createdBy;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getOrderId() {
		return orderId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiledBy() {
		return lastModifiledBy;
	}

	public void setLastModifiledBy(String lastModifiledBy) {
		this.lastModifiledBy = lastModifiledBy;
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

	public Set<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(Set<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

}
