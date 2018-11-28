package io.cts.msa.om.order.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer_sos_140837")
public class Customer {

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Long cust_id, String custFirstNmae, String custLastName, String cust_email) {
		super();
		this.cust_id = cust_id;
		this.custFirstNmae = custFirstNmae;
		this.custLastName = custLastName;
		this.cust_email = cust_email;
	}

	@Id
	private Long cust_id;
	private String custFirstNmae;
	private String custLastName;
	private String cust_email;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)

	private Set<SalesOrder> orders;

	public Long getCust_id() {
		return cust_id;
	}

	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}

	public String getCustFirstNmae() {
		return custFirstNmae;
	}

	public void setCustFirstNmae(String custFirstNmae) {
		this.custFirstNmae = custFirstNmae;
	}

	public String getCustLastName() {
		return custLastName;
	}

	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName;
	}

	public String getCust_email() {
		return cust_email;
	}

	public void setCust_email(String cust_email) {
		this.cust_email = cust_email;
	}

}
