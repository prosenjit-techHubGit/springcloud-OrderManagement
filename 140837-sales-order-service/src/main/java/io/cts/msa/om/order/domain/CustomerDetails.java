package io.cts.msa.om.order.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDetails {

	public CustomerDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerDetails(Long custId, String custFirstNmae, String custLastName, String custEmail) {
		super();
		this.custId = custId;
		this.custFirstNmae = custFirstNmae;
		this.custLastName = custLastName;
		this.custEmail = custEmail;
	}
   
	@JsonProperty(value="id")
	private Long custId;
	@JsonProperty(value="firstName")
	private String custFirstNmae;
	@JsonProperty(value="lastName")
	private String custLastName;
	@JsonProperty(value="email")
	private String custEmail;

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
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

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

}
